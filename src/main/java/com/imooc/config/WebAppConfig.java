package com.imooc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 20:58 2018/4/3
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory configFactory = new MultipartConfigFactory();
//        configFactory.setMaxFileSize("2MB");
//        configFactory.setMaxRequestSize("10MB");
//        return configFactory.createMultipartConfig();
//    }
}
