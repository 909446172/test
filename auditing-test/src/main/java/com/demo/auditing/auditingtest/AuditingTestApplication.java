package com.demo.auditing.auditingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AuditingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditingTestApplication.class, args);
    }

}
