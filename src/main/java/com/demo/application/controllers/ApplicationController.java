package com.demo.application.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.application.models.User;
import com.demo.application.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class ApplicationController {

	
	private UserService userService;
	
	@GetMapping("/find-user")
	public List<User> getUsersWith(@RequestParam Map<String, String> params) {
		log.info("Got find-user Request in Contorller, Delegating to Service");
		return userService.getUsersWith(params);
	}
};
	
