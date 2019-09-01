package com.oauth2.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.oauth2.common.api")
@EnableDiscoveryClient
public class ResourceApp {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApp.class, args);


    }
}
