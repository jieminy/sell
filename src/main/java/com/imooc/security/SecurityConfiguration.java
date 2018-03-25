package com.imooc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 14:13 2018/3/25
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable();

//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin();
    }

}
