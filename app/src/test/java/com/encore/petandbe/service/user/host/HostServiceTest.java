package com.encore.petandbe.service.user.host;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
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
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;

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
			ScriptUtils.executeSqlScript(conn, new ClassPathResource("/data.sql"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("regist host - success")
	void createHostSuccess() {
		//given
		String hostName = "신연주";
		String registrationNumber = "8048800448";
		String openDate = "20160519";
		Long userId = 1L;

		HostRegistrationRequest hostRegistrationRequest = new HostRegistrationRequest(registrationNumber, hostName,
			openDate);

		//when
		Long result = hostService.createHost(hostRegistrationRequest, userId);
		//then
		assertEquals(1L, result);
	}

	@Test
	@DisplayName("regist host - fail")
	void createHostFail() {
		//given
		String hostName = "정정일";
		String registrationNumber = "1111111111";
		String openDate = "19980428";
		Long userId = 2L;

		HostRegistrationRequest hostRegistrationRequest = new HostRegistrationRequest(registrationNumber, hostName,
			openDate);

		//when
		Assertions.assertThrows(WrongRequestException.class, () -> {
			//then
			hostService.createHost(hostRegistrationRequest, userId);
		});
	}

	@Test
	@DisplayName("regist host - user does not exist fail")
	void createUserNotExistFail() {
		//given
		String hostName = "정정일";
		String registrationNumber = "1111111111";
		String openDate = "19980428";
		Long userId = 170L;

		HostRegistrationRequest hostRegistrationRequest = new HostRegistrationRequest(registrationNumber, hostName,
			openDate);

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			hostService.createHost(hostRegistrationRequest, userId);
		});
	}
}