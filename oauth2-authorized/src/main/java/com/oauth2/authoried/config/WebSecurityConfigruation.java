package com.oauth2.authoried.config;

import com.oauth2.authoried.handler.LoginFailedHandler;
import com.oauth2.authoried.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 拦截配置
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    LoginFailedHandler loginFailedHandler;

    @Autowired
    MyDetailService myDetailService ;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myDetailService);
    }

    /**
     * 配置拦截路径的配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().formLogin().and().authorizeRequests()
//                .anyRequest().authenticated();

        http.csrf().disable()
                .authorizeRequests().anyRequest()
                .access("@rbacService.hasPermission(request)");
    }

    /**
     * 配置访问角色
     * @param authenticationConfiguration
     */
    @Override
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        super.setAuthenticationConfiguration(authenticationConfiguration);


    }
}
