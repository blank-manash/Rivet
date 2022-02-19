package com.demo.application.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.application.models.User;

@Service
public class UserService {

	public User findUser(Map<String, String> filters) {
		return new User();
	}

	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
