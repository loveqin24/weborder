package com.epicorweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	
	//@RequestMapping指明地址
	@RequestMapping({"/hello","/"})
	public String Hello(){
		System.out.println("Hello");
		return "hello";
	}
	
	
	
}
