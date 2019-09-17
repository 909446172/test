package com.demo.event.demoevent.controller;

import com.demo.event.demoevent.server.Event;
import com.demo.event.demoevent.server.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
public class Test1Controller {

    Test1Controller() {
        System.out.println("testController()");
    }

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;


    @RequestMapping("test")
    public String test( HttpServletRequest httpServletRequest){
        applicationEventPublisher.publishEvent(new Event("aaaaaaaa"));
        System.out.println(httpServletRequest.getClass());

        return "test";
    }
}
