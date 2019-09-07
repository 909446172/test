package com.demo.mongodb.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConfig {



    public @Bean
    MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(), "database");
    }

}
