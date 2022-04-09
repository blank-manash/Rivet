package com.demo.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.application.mappers.UserMapper;
import com.demo.application.models.User;
import com.google.common.collect.Maps;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FindUserMapperTest {

	@Autowired
	private UserMapper mapper;
	private static final String DEMO_PASSWORD = "2cc2e8e92575630b5136b980cf3dbefea769b176";

	@Test
	void findUserByRivetId() {
		User user = User.builder().firstName("Eda").lastName("Ward").rivetId(5L).phoneNumber(4362L)
				.cityName("Brooklynmouth").password(DEMO_PASSWORD).build();

		Map<String, String> filters = Maps.newHashMap();
		filters.put("rivet_id", "5");

		List<User> response = mapper.getUsers(filters);

		assertThat(response).as("There should be one user per Rivet Id").hasExactlyElementsOfTypes(User.class).first()
				.isEqualTo(user);
	}

	@Test
	void findUserByCityName() {
		Map<String, String> filters = Maps.newHashMap();
		filters.put("city_name", "North Kenny");

		List<User> response = mapper.getUsers(filters);

		assertThat(response).as("There should be more responses per Rivet Id").hasOnlyElementsOfType(User.class)
				.allMatch(user -> user.getCityName().equals("North Kenny"), "Matching CityName");
	}

	@Test
	void findFriends() {
		List<User> friends = mapper.getFriends(1L);
		assertThat(friends).isNotEmpty().allSatisfy(user -> {
			assertNotNull(user.getFirstName());
			assertNotNull(user.getLastName());
			assertNotNull(user.getPassword());
			assertNotNull(user.getRivetId());
			assertNotNull(user.getCityName());
			assertNotNull(user.getPhoneNumber());
		});
	}

	@Test
	void searchByTags() {
		List<String> tagIds = Lists.newArrayList();
		tagIds.add("Music");
		tagIds.add("Sports");
		Long id = 1L;
		List<User> users = mapper.searchByTag(tagIds, 1L);
		List<Long> blockList = mapper.blockedUsersOf(id);
		assertThat(users).isNotEmpty().allSatisfy(user -> {
			assertNotNull(user.getFirstName());
			assertNotNull(user.getLastName());
			assertNotNull(user.getPassword());
			assertNotNull(user.getRivetId());
			assertNotNull(user.getCityName());
			assertNotNull(user.getPhoneNumber());
			assertThat(user.getRivetId()).isNotIn(blockList);
		});
	}

	@Test
	void listFriends() {
		List<User> users = mapper.listFriends(1L);
		assertThat(users).hasSize(5)
		.map(User::getRivetId).
		containsAnyElementsOf(Arrays.asList(2L, 10L, 13L, 15L, 18L));
	}
	
	@Test
	void showTags() {
		List<String> tags = mapper.showTags(1L);
		assertThat(tags).containsExactly("Movies", "Academics");
	}
	@Test
	void listBlockedUsers() {
		List<User> blockedUsers = mapper.listBlockedUsers(1L);
		assertThat(blockedUsers).map(User :: getRivetId).contains(10L, 16L, 24L, 26L);
	}
}
