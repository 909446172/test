package com.leve.swagger2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description="作用于类中")
@RestController
@RequestMapping("/admin")
public class TeacherAdminController {



	@ApiOperation(value = "getMapper方法")
	@GetMapping
	public String getMapper(){
		return "getMapper";
	}


}