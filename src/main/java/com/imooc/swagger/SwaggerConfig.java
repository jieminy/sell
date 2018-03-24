package com.imooc.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 14:12 2018/3/24
 */
@Configuration
@EnableSwagger2
//@ComponentScan({"com.imooc.seller.controller"})
public class SwaggerConfig {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.seller.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInf(){
        return new ApiInfoBuilder()
                .title("公寓鲜商城接口")
                .description("springboot swagger2")
                .termsOfServiceUrl("")
                .contact(new Contact("JieMin", "", "540959120@qq.com"))
                .build();

    }
}
