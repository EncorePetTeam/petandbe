package com.encore.petandbe.service.user.host;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HostServiceTest {

	@Autowired
	private HostService hostService;

	@Autowired
	DataSource dataSource;

	@BeforeAll
	public void init() {
		try (Connection conn = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(conn, new ClassPathResource("/testdb/data.sql"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("regist host - success")
	void createHost() {
		String hostName = "신연주";
		String registrationNumber = "8048800448";
		String openDate = "20160519";
		Long userId = 1L;

		HostRegistrationRequest hostRegistrationRequest = new HostRegistrationRequest(registrationNumber, hostName,
			openDate, userId);

		Long result = hostService.createHost(hostRegistrationRequest);

		assertEquals(1L, result);
	}
}