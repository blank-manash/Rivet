package com.demo.application.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.demo.application.models.User;
import com.demo.application.queries.QueryProviders;

@Mapper
public interface UserMapper {
	
	@SelectProvider(type = QueryProviders.class, method = "findUserByFilter")
	List<User> getUsers(@Param("filters") Map<String, String> filters);
}
