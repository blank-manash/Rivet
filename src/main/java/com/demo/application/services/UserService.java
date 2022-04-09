package com.demo.application.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.application.exceptions.ServerException;
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

	public List<User> getFriends(Long id) throws ServerException {
		if (id == null)
			throw ServerException.with("Current User Id Not Provided");
		
		log.info("Service Call to Get Friends of User {}", id);
		return (mapper.getFriends(id));
	}

	public User registerUser(User user) {
		log.info("Service Call to register User");
		mapper.save(user);
		return user;
	}

	public boolean addFriend(Long idA, Long idB) throws ServerException {
		List<Long> blockList = mapper.blockedUsersOf(idA);
		if (blockList.contains(idB))
			throw ServerException.with("Blocked User Cannot Be Added as a Friend.");
		log.info("Service Call to add Friend {} -> {}", idA, idB);
		int rows = mapper.addFriend(idA, idB);
		return rows == 1;

	}

	public boolean removeFriend(Long idA, Long idB) {
		log.info("Service Call to Remove Friend {} -> {}", idA, idB);
		int rows = mapper.removeFriend(idA, idB);
		return rows == 1;
	}

	public boolean addTags(Long rivetId, String tag) {
		int rows = mapper.addTags(rivetId, tag);
		return rows == 1;
	}

	public boolean blockUser(Long idA, Long idB) {
		boolean removed = mapper.blockUser(idA, idB) == 1;
		if (removed)
			this.removeFriend(idA, idB);
		return removed;
	}

	public boolean unblockUser(Long idA, Long idB) {
		return mapper.unblockUser(idA, idB) == 1;
	}

	public boolean removeTags(Long rid, String tag) {
		return mapper.removeTags(rid, tag) == 1;
	}

	public boolean updateUser(User user) {
		return mapper.updateUser(user) == 1;
	}

	public List<User> searchByTag(List<String> tags, Long id) {
		return mapper.searchByTag(tags, id);
	}

	public List<User> listFriends(Long id) {
		return mapper.listFriends(id);
	}
	public List<String> showTags(Long id) {
		return mapper.showTags(id); 
	}
	public List<User> listBlockedUsers(Long id) {
		return mapper.listBlockedUsers(id);
	}
}
