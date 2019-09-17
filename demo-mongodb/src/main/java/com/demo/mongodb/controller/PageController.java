package com.demo.mongodb.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

//@RestController
public class PageController {


//    @RequestMapping("test")
    public String pageTest(@PageableDefault(size = 10)Pageable pageable) {

        System.out.println(pageable.getClass());


        return pageable.toString();
    }

}
