package com.demo.application.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.application.mappers.UserMapper;
import com.demo.application.models.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {
	private final UserMapper mapper;
	
	public List<User> getUsersWith(Map<String, String> params) {
		log.info("Service Call Get Users with params: {}", params);
		return mapper.getUsers(params);
	}
}
