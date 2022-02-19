package com.demo.application.queries;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueryProviderTests {
	
	@Test
	void findAllUserSQLTest() {
		QueryProviders provider = new QueryProviders();
		String sql = provider.findAllUserSql();
		assertThat(sql).contains("SELECT", "*", "FROM", "users");
	}
}
