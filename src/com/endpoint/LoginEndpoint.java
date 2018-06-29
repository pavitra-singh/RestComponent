package com.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.request.LoginRequest;
import com.dto.response.LoginResponse;
import com.persistence.service.IUserService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginEndpoint {

	@Autowired
	private IUserService  userService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(@RequestBody LoginRequest loginRequest){		
		return userService.loginExistingUser(loginRequest);
		
	}
	
}
