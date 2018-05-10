package com.myValuePack.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myValuePack.persistence.service.IUserService;
import com.myValuePack.request.UserRequest;

@RestController
@RequestMapping("/api/v1/user")
public class UserEndpoint {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public boolean listUser(){
		return true;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addUser(@RequestBody UserRequest userRequest){
		userService.addUser(userRequest);
	}
	
	
}
