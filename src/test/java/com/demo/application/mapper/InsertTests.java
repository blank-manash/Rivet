package com.demo.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.application.mappers.UserMapper;
import com.demo.application.models.User;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class InsertTests {
	@Autowired
	private UserMapper mapper;

	@Test
	void insertUser() {
		User user = User.builder().firstName("Manash").lastName("Baul").password("24124afafs124")
				.cityName("Brooklynmouth").phoneNumber(9435L).build();
		mapper.save(user);
		String id = String.valueOf(user.getRivetId());
		List<User> exists = mapper.getUsers(Maps.newHashMap("rivet_id", id));
		
		assertThat(exists).singleElement().isEqualTo(user);
	}

	@Test
	void blockUserDuplicate() {
		assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> {
			mapper.blockUser(1L, 10L);
		});
	}
	@Test
	void blockUser() {
		assertThatNoException().isThrownBy(() -> {
			mapper.blockUser(1L, 29L);
		});
	}
	@Test
	void addFriendDuplicate() {
		assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> {
			mapper.addFriend(1L, 10L);
		});
	}
	@Test
	void addFriend() {
		assertThatNoException().isThrownBy(() -> {
			mapper.addFriend(1L, 29L);
		});
	}

}
