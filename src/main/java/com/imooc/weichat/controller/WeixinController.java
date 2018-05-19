package com.imooc.weichat.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.config.PayConfig;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by JieMin
 * 2017-07-03 00:50
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @Autowired
    private PayConfig payConfig;

    @GetMapping("/auth")
    public ResultVO auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + payConfig.getAppId() + "&secret=" + payConfig.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        StringMap stringMap = Json.decode(response);
        String openid = stringMap.get("openid").toString();
        if (StringUtils.isNullOrEmpty(openid)) {
            //todo
        }
        log.info("response={}", response);
        return ResultVOUtil.success(openid);
    }
}
