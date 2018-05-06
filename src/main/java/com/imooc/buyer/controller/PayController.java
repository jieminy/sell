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
        //得到小程序传过来的价格，注意这里的价格必须为整数，1代表1分，所以传过来的值必须*100；
        int fee = (int) (orderDTO.getOrderAmount().floatValue() * 100);

        //时间戳
        String times = System.currentTimeMillis() + "";
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", payConfig.getAppId());
        packageParams.put("mch_id", "1502953181");
        packageParams.put("nonce_str", times);//时间戳
        packageParams.put("body", payConfig.getTitle());//支付主体
        packageParams.put("out_trade_no", orderDTO.getOrderId());//订单编号
        packageParams.put("total_fee", fee);//价格
        // packageParams.put("spbill_create_ip", getIp2(request));这里之前加了ip，但是总是获取sign失败，原因不明，之后就注释掉了
        packageParams.put("notify_url", payConfig.getNotifyUrl() + "/notify");//支付返回地址，不用纠结这个东西，我就是随便写了一个接口，内容什么都没有
        packageParams.put("trade_type", payConfig.getTradeType());//这个api有，固定的
        packageParams.put("openid", openid);//openid
        //获取sign
        String sign = PayUtil.createSign("UTF-8", packageParams, payConfig.getApiKey());//最后这个是自己设置的32位密钥
        packageParams.put("sign", sign);
        //转成XML
        String requestXML = PayUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
        //得到含有prepay_id的XML
        String resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
        System.out.println(resXml);
        //解析XML存入Map
        Map map;
        try {
            map = XMLUtil.doXMLParse(resXml);
            System.out.println(map);
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
//        Gson gson = new Gson();
//        String json = gson.toJson(packageP);
//        PrintWriter pw = response.getWriter();
//        System.out.println(json);
//        pw.write(json);
//        pw.close();
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
}
