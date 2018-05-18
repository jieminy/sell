package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:02 2018/4/25
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class PayConfig {
    private String appId;
    private String appSecret;
    private final String title = "公寓鲜支付";
    private final String notifyUrl;
    private final String tradeType = "JSAPI";
    private final String apiKey = "51VH03mx78j0gindrijxJYs6qJY76q8Y";
    private final String mchId = "1502953181";
}
