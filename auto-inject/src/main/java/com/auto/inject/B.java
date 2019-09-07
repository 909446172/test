package com.auto.inject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class B {

    A a;
    @Override
    public String toString() {
        return a.toString();
    }
}
