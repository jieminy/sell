package com.imooc.buyer.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.dto.OrderDTO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.HttpUtil;
import com.imooc.common.utils.PayUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.common.utils.XMLUtil;
import com.imooc.config.PayConfig;
import com.imooc.exception.SellException;
import com.imooc.seller.service.OrderService;
import com.imooc.seller.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 支付
 * Created by JieMin
 * 2017-07-04 00:49
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private PayConfig payConfig;

    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    @GetMapping("/create")
    public ResultVO pay(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId, HttpServletRequest request) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        String times = System.currentTimeMillis() + "";
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        packageParams.put("appid", payConfig.getAppId());
        packageParams.put("mch_id", payConfig.getMchId());
        packageParams.put("nonce_str", times);//时间戳
        packageParams.put("body", payConfig.getTitle());//支付主体
        packageParams.put("out_trade_no", orderDTO.getOrderId());//订单编号
        packageParams.put("total_fee", (int) (orderDTO.getOrderAmount().floatValue() * 100));//价格 得到小程序传过来的价格，注意这里的价格必须为整数，1代表1分，所以传过来的值必须*100；
        // packageParams.put("spbill_create_ip", getIp2(request));这里之前加了ip，但是总是获取sign失败，原因不明，之后就注释掉了
        packageParams.put("notify_url", payConfig.getNotifyUrl() + "/notify");//支付返回地址，不用纠结这个东西，我就是随便写了一个接口，内容什么都没有
        packageParams.put("trade_type", payConfig.getTradeType());//这个api有，固定的
        packageParams.put("openid", openid);//openid
        //获取sign
        String sign = PayUtil.createSign("UTF-8", packageParams, payConfig.getApiKey());//最后这个是自己设置的32位密钥
        packageParams.put("sign", sign);
        //转成XML
        String requestXML = PayUtil.getRequestXml(packageParams);
        log.info("requestXML:" + requestXML);
        //得到含有prepay_id的XML
        String resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
        log.info("responseXml:" + resXml);
        //解析XML存入Map
        Map map;
        try {
            map = XMLUtil.doXMLParse(resXml);
            log.info("xml to map:" + map.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        // String return_code = (String) map.get("return_code");
        //得到prepay_id
        String prepay_id = (String) map.get("prepay_id");
        SortedMap<Object, Object> packageP = new TreeMap<>();
        packageP.put("appId", payConfig.getAppId());//！！！注意，这里是appId,上面是appid，真怀疑写这个东西的人。。。
        packageP.put("nonceStr", times);//时间戳
        packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
        packageP.put("signType", "MD5");//paySign加密
        packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
        //得到paySign
        String paySign = PayUtil.createSign("UTF-8", packageP, payConfig.getApiKey());
        packageP.put("paySign", paySign);
        //将packageP数据返回给小程序
        return ResultVOUtil.success(packageP);
    }

    /**
     * IpUtils工具类方法
     * 获取真实的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

//    @RequestMapping(value = "/refund", method = RequestMethod.GET)
//    public @ResponseBody Map<String, Object> refund(String id,String user) {
//        Map<String,Object> result = new HashMap<String,Object>();
//        String currTime = PayUtil.getCurrTime();
//        String strTime = currTime.substring(8, currTime.length());
//        String strRandom = PayUtil.buildRandom(4) + "";
//        String nonceStr = strTime + strRandom;
//        String outRefundNo = "wx@re@"+PayUtil.getTimeStamp();
//        String outTradeNo = "";
//
//        DecimalFormat df = new DecimalFormat("######0");
//        String fee = String.valueOf(df.format((Double.valueOf(payLog.getTotalFee())*100)));
//        SortedMap<String, String> packageParams = new TreeMap<String, String>();
//        packageParams.put("appid", appId);
//        packageParams.put("mch_id", mchId);//微信支付分配的商户号
//        packageParams.put("nonce_str", nonceStr);//随机字符串，不长于32位
//        packageParams.put("op_user_id", mchId);//操作员帐号, 默认为商户号
//        //out_refund_no只能含有数字、字母和字符_-|*@
//        packageParams.put("out_refund_no", outRefundNo);//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
//        packageParams.put("out_trade_no", outTradeNo);//商户侧传给微信的订单号32位
//        packageParams.put("refund_fee", fee);
//        packageParams.put("total_fee", fee);
//        packageParams.put("transaction_id", payLog.getTransactionId());//微信生成的订单号，在支付通知中有返回
//        String sign = PayUtil.createSign(packageParams,key);
//
//        String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
//        String xmlParam="<xml>"+
//                "<appid>"+appId+"</appid>"+
//                "<mch_id>"+mchId+"</mch_id>"+
//                "<nonce_str>"+nonceStr+"</nonce_str>"+
//                "<op_user_id>"+mchId+"</op_user_id>"+
//                "<out_refund_no>"+outRefundNo+"</out_refund_no>"+
//                "<out_trade_no>"+outTradeNo+"</out_trade_no>"+
//                "<refund_fee>"+fee+"</refund_fee>"+
//                "<total_fee>"+fee+"</total_fee>"+
//                "<transaction_id>"+payLog.getTransactionId()+"</transaction_id>"+
//                "<sign>"+sign+"</sign>"+
//                "</xml>";
//        String resultStr = PayUtil.post(refundUrl, xmlParam);
//        //解析结果
//        try {
//            Map map =  doXMLParse(resultStr);
//            String returnCode = map.get("return_code").toString();
//            if(returnCode.equals("SUCCESS")){
//                String resultCode = map.get("result_code").toString();
//                if(resultCode.equals("SUCCESS")){
//                    ProfPayLog profPayLog = new ProfPayLog();
//                    profPayLog.setCreatedAt(new Date());
//                    profPayLog.setSource(payLog.getSource());
//                    profPayLog.setTotalFee(payLog.getTotalFee());
//                    profPayLog.setTradeNo(payLog.getTradeNo());
//                    profPayLog.setTransactionId(map.get("refund_id").toString());
//                    profPayLog.setUserId(user);
//                    profPayLog.setType(ProfPayLog.Type.Refund);
//                    profPayLog = wxappOrderService.save(profPayLog);
//                    result.put("status", "success");
//                }else{
//                    result.put("status", "fail");
//                }
//            }else{
//                result.put("status", "fail");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("status", "fail");
//        }
//        return result;
//    }
//
//    public static String post(String url, String xmlParam){
//        StringBuilder sb = new StringBuilder();
//        try {
//            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
//            FileInputStream instream = new FileInputStream(new File(""));
//            try {
//                keyStore.load(instream, "商户id".toCharArray());
//            } finally {
//                instream.close();
//            }
//
//            // 证书
//            SSLContext sslcontext = SSLContexts.custom()
//                    .loadKeyMaterial(keyStore, "商户id".toCharArray())
//                    .build();
//            // 只允许TLSv1协议
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext,
//                    new String[] { "TLSv1" },
//                    null,
//                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//            //创建基于证书的httpClient,后面要用到
//            CloseableHttpClient client = HttpClients.custom()
//                    .setSSLSocketFactory(sslsf)
//                    .build();
//
//            HttpPost httpPost = new HttpPost(url);//退款接口
//            StringEntity reqEntity  = new StringEntity(xmlParam);
//            // 设置类型
//            reqEntity.setContentType("application/x-www-form-urlencoded");
//            httpPost.setEntity(reqEntity);
//            CloseableHttpResponse response;
//            response = client.execute(httpPost);
//            try {
//                HttpEntity entity = response.getEntity();
//                System.out.println(response.getStatusLine());
//                if (entity != null) {
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
//                    String text="";
//                    while ((text = bufferedReader.readLine()) != null) {
//                        sb.append(text);
//                    }
//                }
//                EntityUtils.consume(entity);
//            } catch(Exception e){
//                e.printStackTrace();
//            }finally {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
}
