package com.demo.mongodb.entity;

import lombok.Data;

class  Contact {}

@Data
public class Person extends Contact  {
    private String id;
    private String name;
    private int age;
    private Contact _value ;



    public Person(){};

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this._value =this;
    }

}
