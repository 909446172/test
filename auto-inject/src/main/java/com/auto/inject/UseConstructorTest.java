package com.auto.inject;

import org.springframework.stereotype.Component;

@Component
public class UseConstructorTest {

    ConstructorTest  constructorTest ;
    UseConstructorTest(ConstructorTest  constructorTest){
        this.constructorTest = constructorTest;
    }



}
