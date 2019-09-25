package com.example111.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zyy
 * @Date 2019/9/20 11:39
 * @Version 1.0
 */
@Service
public class a {

    @Autowired
    b bb;

    public void aa() {
        System.out.println("aaaaaaaa");
    }

}
