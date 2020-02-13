package com.bjc.boot.demo;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boot")
public class HelloController {
	@Resource
	private Environment env;
	
	@RequestMapping("/hello")
	public String hello(){
		return "hellow world!  " + env.getProperty("url");
	}
	
	@RequestMapping("/hello1")
	public String hello1(){
		return "hellow world! " + env.getProperty("url");
	}
}
