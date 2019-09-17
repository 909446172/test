package com.demo.mongodb;

import com.demo.mongodb.api.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class MongoDbApp  {


    public static void main(String[] args) {
        SpringApplication.run(MongoDbApp.class, args);

    }

}
