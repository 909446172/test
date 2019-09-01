package com.oauth2.authoried;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthorizedApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizedApp.class, args);

    }

}
