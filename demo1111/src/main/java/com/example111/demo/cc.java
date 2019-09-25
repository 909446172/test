package com.example111.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zyy
 * @Date 2019/9/20 11:39
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
public class cc {

    a aa;
    b bb;
    c cc;

    @RequestMapping("test")
    public String test(Parent parent , HttpServletRequest request) {
        System.out.println(parent.getClass());
        System.out.println(parent);
            aa.aa();
            bb.bb();
            String code = request.getMethod();

        return parent.toString()+code ;
    }


}
