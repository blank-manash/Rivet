package com.demo.application.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import com.demo.application.models.User;
import com.demo.application.queries.QueryProviders;

@Mapper
public interface UserMapper {

	@SelectProvider(type = QueryProviders.class, method = "findUserByFilter")
	List<User> getUsers(@Param("filters") Map<String, String> filters);

	@InsertProvider(type = QueryProviders.class, method = "insertUser")
	@Options(useGeneratedKeys = true, keyProperty = "rivetId", keyColumn = "rivet_id")
	Long save(@Param("user") User user);

	@InsertProvider(type = QueryProviders.class, method = "addFriend")
	void addFriend(@Param("idA") Long idA, @Param("idB") Long idB);

	@InsertProvider(type = QueryProviders.class, method = "blockUser")
	void blockUser(@Param("idA") Long idA, @Param("idB") Long idB);

	@SelectProvider(type = QueryProviders.class, method = "findFriends")
	List<User> getFriends(@Param("id") Long id);

	@SelectProvider(type = QueryProviders.class, method = "searchByTag")
	List<User> searchByTag(@Param("tagIds") List<String> tagIds);
}
