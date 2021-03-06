package com.demo.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

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
		assertThat(id).isNotBlank();
		assertThat(exists).singleElement().isEqualTo(user);
	}

	@Test
	void blockUserDuplicate() {
		assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> {
			mapper.blockUser(1L, 10L);
		});
	}

	@Test
	void blockUserAndUnblock() {
		Long idA = 1L;
		Long idB = 29L;

		int blocked = mapper.blockUser(idA, idB);
		int unblocked = mapper.unblockUser(idA, idB);

		assertThat(blocked).as("One Record Should be inserted").isEqualTo(1);
		assertThat(unblocked).as("One Record should be removed").isEqualTo(1);
	}

	@Test
	void addFriendDuplicate() {
		assertThatExceptionOfType(DuplicateKeyException.class).isThrownBy(() -> {
			mapper.addFriend(1L, 10L);
		});
	}

	@Test
	void addAndRemoveFriend() {
		Long idA = 1L;
		Long idB = 29L;
		int added = mapper.addFriend(idA, idB);
		int removed = mapper.removeFriend(idA, idB);

		assertThat(added).as("One Recored Should be Inserted").isEqualTo(1);
		assertThat(removed).as("One Record should be Deleted").isEqualTo(1);

	}

	@Test
	void addAndRemoveTags() {
		Long id = 1L;
		String tag = "Sports";
		
		int added = mapper.addTags(id, tag);
		int removed = mapper.removeTags(id, tag);
		
		assertThat(added).as("One Recored Should be Inserted").isEqualTo(1);
		assertThat(removed).as("One Record should be Deleted").isEqualTo(1);

	}
	
	@Test
	void updateUser() {
		User user = User.builder()
						.firstName("Manash")
						.lastName("Baul")
						.phoneNumber(9435L)
						.cityName("Port Jannie")
						.rivetId(1L)
						.password("foobar@g25k.cse.cc.i3")
						.build();
					
		int rows = mapper.updateUser(user);
		assertThat(rows).isEqualTo(1);
		
		List<User> users = mapper.getUsers(Maps.newHashMap("rivet_id", "1"));
		assertThat(users).singleElement().isEqualTo(user);
	}

}
