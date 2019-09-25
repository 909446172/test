package com.demo.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Author zyy
 * @Date 2019/9/20 20:03
 * @Version 1.0
 */
@Data
public class Person {

    @Id
    String id;

    String name;

    Integer age;

}
