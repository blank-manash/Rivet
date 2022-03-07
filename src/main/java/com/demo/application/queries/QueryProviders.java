package com.demo.application.queries;

import java.util.Map;
import java.util.function.Function;

import org.apache.ibatis.jdbc.SQL;

public class QueryProviders {
	public String findAllUserSql() {
		SQL sql = new SQL();
		return sql.SELECT("*").FROM("users").toString();
	}

	private String UserSql(Function<SQL, SQL> transform) {
		SQL sql = new SQL();
		sql.SELECT(
				"users.first_name, users.last_name, users.rivet_id, users.password, users.phone_number, location.city_name")
				.FROM("users").INNER_JOIN("location ON users.location_id=location.location_id");

		return transform.apply(sql).ORDER_BY("users.first_name, users.last_name, location.city_name").toString();
	}

	public String findUserByFilter(Map<String, Object> params) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		StringBuilder whereClause = new StringBuilder();
		filters.forEach((key, value) -> {
			String clause = String.format("%s = \"%s\" AND ", key, value);
			whereClause.append(clause);
		});
		whereClause.append("1=1");

		return UserSql(sql -> {
			return sql.WHERE(whereClause.toString());
		});
	}

}
