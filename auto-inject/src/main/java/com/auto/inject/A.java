package com.auto.inject;

import org.springframework.stereotype.Service;

@Service
public class A {

    @Override
    public String toString() {
        return "that is a method ";
    }
}
