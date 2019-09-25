package com.demo.event.demoevent.controller;

import com.demo.event.demoevent.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
//@Validated
public class Test2Controller {

//    Test2Controller(){
//        System.out.println("Test2Controller()");
//    }


    @RequestMapping("/test11")
    public String test1(@Valid User user){
        System.out.println(user);
        return user.getName()==null? "":user.getName();
    }

}
