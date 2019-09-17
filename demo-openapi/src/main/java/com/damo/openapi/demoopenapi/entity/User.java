package com.damo.openapi.demoopenapi.entity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "这是user类的 schema注解 name属性")
public class User {
    @Schema(title = "name值的Schema注解的title属性" ,name = "name值的Schema注解的name属性")
    String name;
}
