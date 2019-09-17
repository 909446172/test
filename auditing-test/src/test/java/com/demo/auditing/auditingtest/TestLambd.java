package com.demo.auditing.auditingtest;

import lombok.var;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class TestLambd {


    public static void main(String[] args) {
        List<String > ghosts = Arrays.asList(new  String []{"1","2","3"});
        ghosts.stream().map(a -> a).collect(toList()).forEach(System.out::println);


    }

}
