package com.encore.petandbe.controller.oauth.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.controller.oauth.responses.LoginOauthResponse;
import com.encore.petandbe.controller.oauth.responses.LogoutResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.oauth.OauthCompany;
import com.encore.petandbe.service.oauth.OauthService;

@Controller
@RequestMapping("oauth")
public class OauthController {

	private final OauthService oauthService;

	public OauthController(OauthService oauthService) {
		this.oauthService = oauthService;
	}

	@GetMapping("/{company-name}")
	public String goToOauthPage(@PathVariable("company-name") String companyName) {
		return "redirect:" + oauthService.findOauthPageByCompanyName(OauthCompany.findEnumByString(companyName));
	}

	@ResponseBody
	@GetMapping("/login/kakao")
	public ResponseEntity<LoginOauthResponse> login(@RequestParam String code,
		HttpServletResponse httpServletResponse) throws
		IOException,
		InterruptedException {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(oauthService.loginByOauth(code, OauthCompany.KAKAO, httpServletResponse));
	}

	@Permission(role = Role.USER)
	@GetMapping("/test")
	public ResponseEntity<String> test(HttpServletRequest request) {
		return ResponseEntity.ok().body("login success");
	}

	@Permission(role = Role.USER)
	@GetMapping("/logout/{id}")
	public ResponseEntity<LogoutResponse> logout(@PathVariable("id") Long id, HttpServletResponse response) {
		return ResponseEntity.ok().body(oauthService.logoutByUserId(id, response));
	}

}
