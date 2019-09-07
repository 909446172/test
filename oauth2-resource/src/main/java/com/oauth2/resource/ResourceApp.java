package com.oauth2.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableFeignClients("com.oauth2.common.api")
@EnableDiscoveryClient
@ComponentScan({"com.oauth2.resource","com.oauth2.resource.service.serviceImpl","com.oauth2.common.api"})
public class ResourceApp {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApp.class, args);


    }
}
