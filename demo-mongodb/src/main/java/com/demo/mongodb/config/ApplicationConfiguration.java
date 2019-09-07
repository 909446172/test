package com.demo.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EnableJpaRepositories("com.demo.mongodb")
public  class ApplicationConfiguration {

  @Bean
  EntityManagerFactory entityManagerFactory() {
    // …
    return null;
  }


}