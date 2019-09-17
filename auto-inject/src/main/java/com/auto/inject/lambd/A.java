package com.auto.inject.lambd;


import java.util.function.Function;


class B {

    public Object  cc(Object aa){
        System.out.println("222222222");
        return aa;
    }


}


public class A {
    private Function na;

    public A() {

    }

    public void  aa(Object na) {
        B b = (B) na;
        b.cc("----------");
    }

    public static void main(String[] args) {



    }


}
