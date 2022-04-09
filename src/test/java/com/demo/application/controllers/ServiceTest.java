package com.demo.application.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.demo.application.exceptions.ServerException;
import com.demo.application.models.User;
import com.demo.application.services.UserService;

@SpringBootTest
@Transactional
class ServiceTest {
	
	@Autowired
	private UserService service;

	@Test
	void blockUser() {
		Long a = 1L;
		Long b = 3L;
		assertDoesNotThrow(() -> service.addFriend(a, b));
		List<User> friends = service.listFriends(a);
		assertThat(friends).map(User :: getRivetId).contains(b);
		boolean removed = service.blockUser(a, b);
		assertThat(removed).isEqualTo(Boolean.TRUE);
		friends = service.listFriends(a);
		assertThat(friends).map(User :: getRivetId).doesNotContain(b);
	}
	
	@Test
	void addFriend_WhenBlocked() {
		Long a = 1L;
		Long b = 3L;
		service.blockUser(a, b);
		assertThrows(ServerException.class, 
				() -> {
					service.addFriend(a, b);
				});
	}

}
