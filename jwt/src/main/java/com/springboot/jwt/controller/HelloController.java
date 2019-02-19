package com.springboot.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.entity.ROLE_NAME;
import com.springboot.jwt.entity.Role;
import com.springboot.jwt.repository.RoleRepository;

@RestController
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	
	@GetMapping("/")
	public String index(){
		return "Hello";
	}
}
