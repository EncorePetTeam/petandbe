package com.encore.petandbe.controller.user.host.api;

import com.encore.petandbe.controller.user.host.request.BecomeHostRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@WebMvcTest(controllers = HostController.class)
@AutoConfigureRestDocs
class HostControllerTest {

	@Test
	@DisplayName("Buisiness Registation Number Check - Success")
	void becomeHost() throws Exception {
		//given
		String business_registation_number = "1234567899";
		String host_name = "박철련";
		String business_open_date = "20221111";

		// when & then
		URL url = new URL(
			"https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=%2FUSr%2Fec%2F4ywJMNg1N9o%2B2cn%2FWo0MfbowBcc5vnFaY0S9jl3UlGVoJaASiHDTTEu%2Fdyb5iN%2BcHMIBOnYfdgb34A%3D%3D");
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("accept", "application/json");
		httpConn.setRequestProperty("Authorization",
			"%2FUSr%2Fec%2F4ywJMNg1N9o%2B2cn%2FWo0MfbowBcc5vnFaY0S9jl3UlGVoJaASiHDTTEu%2Fdyb5iN%2BcHMIBOnYfdgb34A%3D%3D");
		httpConn.setRequestProperty("Content-Type", "application/json");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write("{  \"businesses\": [    {      \"b_no\": \"" + business_registation_number
			+ "\",      \"start_dt\": \"" + business_open_date + "\",      \"p_nm\": \"" + host_name
			+ "\",      \"p_nm2\": \"" + "" + "\",      \"b_nm\": \"" + "" + "\",      \"corp_no\": \"" + ""
			+ "\",      \"b_sector\": \"\",      \"b_type\": \"\"    }  ]}");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
			? httpConn.getInputStream()
			: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";

		ObjectMapper obj = new ObjectMapper();
		Map<String, Object> result
			= obj.readValue(response, new TypeReference<Map<String, Object>>() {
		});
		List<Map<String, Object>> a = (List<Map<String, Object>>)result.get("data");
		System.out.println(a.get(0).get("valid_msg"));
		if (a.get(0).get("valid_msg").equals("확인할 수 없습니다.")) {
			System.out.println("사업자 등록번호를 확인해주세요");
		}
	}
}