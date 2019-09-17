package com.damo.openapi.demoopenapi.controller;

import com.damo.openapi.demoopenapi.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition
@Tag(name="这是一个测试的openapi 文档" ,description = "description 的字符串")
@Tags({@Tag(name="aa"),@Tag(name = "bb")})
public class TestController {


    @GetMapping("/talk")
    @Operation(description = "这是 operation 的 description属性")
     @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public User test1(@Parameter(name = "parameter注解的name属性",description = "parameter注解的description属性" ) String name  ){
            User user = new User();
            user.setName(name);
         int i = 1/0;
        return user;
    }

    @GetMapping("/tell")
    @Operation(description = "这是 operation 的 description属性")
    @ApiResponse(responseCode = "333",description = "dddddddddddddddddddd")
    public User tell(@Parameter(name = "parameter注解的name属性",description = "parameter注解的description属性" ) String name  ){
        User user = new User();
        user.setName(name);
        return user;
    }


}
