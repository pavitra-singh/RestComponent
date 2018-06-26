package com.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.persistence.service.IUserService;

import dto.request.UserRequest;
import dto.response.UserResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserEndpoint {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<UserResponse> listUser(){
		return userService.listOfUsers();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addUser(@RequestBody UserRequest userRequest){
		userService.addUser(userRequest);
	}
	
	
}
