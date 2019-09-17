package com.auto.inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.Optional;

//@Configuration
//@EnableMongoAuditing(auditorAwareRef = "jpaAuditorAware")//使用mongo，也可以使用其他，如jpa（mysql）
//public class JpaAuditorAware implements AuditorAware<String> {
//  @Override
//  public Optional<String> getCurrentAuditor() {
//    Optional<String> system = new Optional<>("system");
//    return system;
//  }
//}
