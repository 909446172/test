package com.oauth2.authoried.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class MyDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        // TODO: 2019/9/1  判断是否存在哦用户 , 并且判断是否登录成功;
        // TODO Auto-generated method stub
        String username = "123";
        String password = "123";
        String role = "admin";

        System.out.println("-------------------loadUserByUsername--------------------");
        User user = new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList(role));
        
        
        return user;
    }
}
