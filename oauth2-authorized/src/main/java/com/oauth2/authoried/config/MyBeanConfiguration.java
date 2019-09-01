package com.oauth2.authoried.config;

import com.oauth2.common.util.JwtOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfiguration {

    @Bean
    public JwtOperator jwtOperator(){
        return new JwtOperator();
    }

}
