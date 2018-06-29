package com.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.request.UserRequest;
import com.dto.response.UserResponse;
import com.persistence.service.IUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserEndpoint {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<UserResponse> listUser(){
		return userService.listOfUsers();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public UserResponse addUser(@RequestBody UserRequest userRequest){
		return userService.addUser(userRequest);
	}
	
}
