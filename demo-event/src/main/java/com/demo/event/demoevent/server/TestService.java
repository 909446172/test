package com.demo.event.demoevent.server;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class TestService {

    @PostConstruct
    public String post(){
        System.out.println("-----------------");
        return "aaaa";
    }

    @EventListener
    public void tes(Event event){
        System.out.println("test()");
    }


}
