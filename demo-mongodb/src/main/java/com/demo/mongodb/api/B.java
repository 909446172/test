package com.demo.mongodb.api;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;


//@Configuration
//@Data
public class B {

//    @Value("${hello.dev}")
    String hello;

    public void b(){
        System.out.println("b");
    }

}
