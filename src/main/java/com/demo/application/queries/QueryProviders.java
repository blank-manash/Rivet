package com.demo.application.queries;

import org.apache.ibatis.jdbc.SQL;

public class QueryProviders {
	public String findAllUserSql() {
		SQL sql = new SQL();
		return sql
				.SELECT("*")
				.FROM("users")
				.toString();
	}
}
