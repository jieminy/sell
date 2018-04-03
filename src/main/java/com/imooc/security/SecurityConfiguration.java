package com.imooc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    @Bean
    SellerUserServiceImpl sellerUserService() {
        return new SellerUserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sellerUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf
        http
                .csrf()
                .disable();

        //anMatcher permit
        http
                .authorizeRequests()
                .antMatchers("/sell/css/**", "/tkreport/images/**", "/sell/favicon.ico", "/sell/login", "/sell/js/**")
                .permitAll();

        //authentication
        http
                .formLogin()
                .loginPage("/sell/login")
                .defaultSuccessUrl("/sell/index")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        //logout
        http
                .logout()
                .deleteCookies("JESSIONID")
                .invalidateHttpSession(true)
                .permitAll();

        //sessionManagement
//		http
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//				.sessionFixation().changeSessionId()
//				.invalidSessionUrl("/tkboss/login")
//				.sessionFixation();

        //login
//		http
//			.requiresChannel()
//				.antMatchers("/login")
//				.requiresSecure()
//				.and();

    }

}
