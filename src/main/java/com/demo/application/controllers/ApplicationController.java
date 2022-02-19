package com.demo.application.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.application.models.User;
import com.demo.application.services.UserService;
import com.google.common.base.Preconditions;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApplicationController {

	
	private UserService userService;
	
	@GetMapping("/find-user")
	public User findUser(@RequestParam Map<String, String> filters) {
		return userService.findUser(filters);
	}

	@PostMapping
	public User registerUser(@RequestBody User user){
		Preconditions.checkNotNull(user, "User is NULL");
		return userService.save(user);
	}
	@PostMapping("/block")
	public ResponseEntity<String> blockUser(@RequestParam Long user1, @RequestParam Long user2) {
		return ResponseEntity.ok("Contact Successfully Blocked");
	}
}
