package com.demo.application.queries;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.apache.ibatis.jdbc.SQL;

import com.demo.application.models.User;

import lombok.extern.slf4j.Slf4j;

public class QueryProviders {
	public String findAllUserSql() {
		SQL sql = new SQL();
		return sql.SELECT_DISTINCT("*").FROM("users").toString();
	}

	private String userSql(UnaryOperator<SQL> transform) {
		SQL sql = new SQL();

		sql.SELECT_DISTINCT(
				"users.first_name, users.last_name, users.rivet_id, users.password, users.phone_number, location.city_name")
				.FROM("users").INNER_JOIN("location ON users.location_id=location.location_id");

		return transform.apply(sql).ORDER_BY("users.first_name, users.last_name, location.city_name").toString();
	}

	public String findUserByFilter(Map<String, Object> params) {

		@SuppressWarnings("unchecked")
		Map<String, String> filters = (Map<String, String>) params.get("filters");

		return userSql(sql -> {
			filters.forEach((key, value) -> sql.WHERE(String.format("%s = '%s'", key, value)).AND());
			return sql.WHERE("1=1");
		});
	}

	public String insertUser(User user) {
		SQL sql = new SQL();
		sql.INSERT_INTO("users").VALUES("first_name", "#{user.firstName}").VALUES("last_name", "#{user.lastName}")
				.VALUES("password", "#{user.password}").VALUES("phone_number", "#{user.phoneNumber}")
				.VALUES("location_id", getLocationId(user.getCityName()));

		return sql.toString();
	}

	public String getLocationId(String cityName) {
		SQL sql = new SQL();
		String ret = sql.SELECT("location_id").FROM("location").WHERE(String.format("city_name = \"%s\"", cityName))
				.toString();
		return "(" + ret + ")";
	}

	private SQL userRelation(Long idA, Long idB, String tableName) {
		SQL sql = new SQL();
		sql.INSERT_INTO(tableName).VALUES("id_a", Long.toString(idA)).VALUES("id_b", Long.toString(idB));

		return sql;
	}

	public String blockUser(Long idA, Long idB) {
		return userRelation(idA, idB, "blocked_users").toString();
	}

	public String addFriend(Long idA, Long idB) {
		return userRelation(idA, idB, "friends").toString();
	}

	public String findFriends(Long id) {
		return String.format(
				"with bu as (select id_b from blocked_users b where id_a = %d), rivet_filters as (select distinct t2.id_b from friends t1 inner join friends t2 on t1.id_b = t2.id_a order by t2.id_b), cu"
						+ "rrent_friends AS (select id_b from friends where id_a = %d)  select * from users where (users.rivet_id not in (select * from bu)) AND (users.rivet_id in (select * from rivet_filters))"
						+ "AND (users.rivet_id not in (select * from current_friends)) AND (users.rivet_id != %d);",
				id, id, id);

	}

	public String searchByTag(Map<String, Object> tagIds) {
		@SuppressWarnings("unchecked")
		List<String> tIds = (List<String>) tagIds.get("tagIds");

		String sql = "select * from users where (users.rivet_id in (select ut.rivet_id from user_tags ut inner join tags t on t.tag_id = ut.tag_id where %s))";

		String arg = tIds.stream().map(tag -> String.format("t.tag = \"\\\"%s\\\"\"", tag))
				.collect(Collectors.joining(" OR ", "(", ")"));

		return String.format(sql, arg);
	}
}
