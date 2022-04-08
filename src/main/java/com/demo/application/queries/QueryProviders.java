package com.demo.application.queries;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.apache.ibatis.jdbc.SQL;

import com.demo.application.models.User;

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

	private String userRelation(Long idA, Long idB, String tableName) {
		SQL sql = new SQL();
		sql.INSERT_INTO(tableName).VALUES("id_a", Long.toString(idA)).VALUES("id_b", Long.toString(idB));

		return sql.toString();
	}

	private String userRelationRemove(Long idA, Long idB, String tableName) {
		SQL sql = new SQL();
		return sql.DELETE_FROM(tableName).WHERE("id_a = " + Long.toString(idA)).AND()
				.WHERE("id_b = " + Long.toString(idB)).toString();
	}

	public String removeFriend(Long idA, Long idB) {
		return userRelationRemove(idA, idB, "friends");
	}

	public String unblockUser(Long idA, Long idB) {
		return userRelationRemove(idA, idB, "blocked_users");
	}

	public String blockUser(Long idA, Long idB) {
		return userRelation(idA, idB, "blocked_users");
	}

	public String addFriend(Long idA, Long idB) {
		return userRelation(idA, idB, "friends");
	}

	public String findFriends(Long id) {

		String condition1 = String.format("users.rivet_id not in (select id_b from blocked_users b where id_a = %d)",
				id);
		String condition2 = "users.rivet_id in (select distinct t2.id_b from friends t1 inner join friends t2 on t1.id_b = t2.id_a order by t2.id_b)";
		String condition3 = String.format("users.rivet_id not in (select id_b from friends where id_a = %d)", id);
		String condition4 = String.format("users.rivet_id != %d", id);

		return userSql(
				SQL -> SQL.WHERE(condition1).AND().WHERE(condition2).AND().WHERE(condition3).AND().WHERE(condition4));

	}

	public String listFriends(Long id) {
		String condition = String.format("users.rivet_id in (select id_b from friends where id_a = %d)", id);
		return userSql(SQL -> SQL.WHERE(condition));
	}

	public String searchByTag(Map<String, Object> tagIds) {
		@SuppressWarnings("unchecked")
		List<String> tIds = (List<String>) tagIds.get("tagIds");

		String sql = userSql(SQL -> SQL.WHERE(
				"users.rivet_id in (select ut.rivet_id from user_tags ut inner join tags t on t.tag_id = ut.tag_id where %s)"));

		String arg = tIds.stream().map(tag -> String.format("t.tag = '%s'", tag))
				.collect(Collectors.joining(" OR ", "(", ")"));

		return String.format(sql, arg);
	}

	public String addTags(Map<String, Object> param) {
		Long id = (Long) param.get("rivetId");
		String tag = (String) param.get("tag");
		String fmt = "insert into user_tags value(%d, (select tag_id from tags where tag = '%s'))";
		return String.format(fmt, id, tag);
	}

	public String showTags(Long id) {
		return String.format(
				"select tags.tag from tags inner join user_tags on user_tags.tag_id = tags.tag_id where user_tags.rivet_id = %d",
				id);
	}
	
	public String listBlockedUsers(Long id) {
		String condition = String.format("users.rivet_id in (select id_b from blocked_users where id_a = %d)", id);
		return userSql(SQL -> SQL.WHERE(condition));
	}

	public String removeTags(Map<String, Object> param) {
		Long id = (Long) param.get("rivetId");
		String tag = (String) param.get("tag");
		String fmt = "delete from user_tags where (rivet_id = %d) AND (tag_id in (SELECT tag_id from tags where tags.tag = '%s'))";

		return String.format(fmt, id, tag);
	}

	public String updateUser(User user) {
		SQL sql = new SQL();
		sql.UPDATE("users").SET("first_name = #{user.firstName}").SET("last_name = #{user.lastName}")
				.SET("password = #{user.password}").SET("phone_number = #{user.phoneNumber}")
				.SET("location_id = " + getLocationId(user.getCityName())).WHERE("rivet_id = #{user.rivetId}");

		return sql.toString();
	}

}
