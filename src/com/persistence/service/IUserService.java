package com.persistence.service;

import java.util.List;

import com.dto.request.LoginRequest;
import com.dto.request.UserRequest;
import com.dto.response.LoginResponse;
import com.dto.response.UserResponse;

public interface IUserService {

	public UserResponse addUser(UserRequest userRequest);
	public List<UserResponse> listOfUsers();
	public LoginResponse loginExistingUser(LoginRequest loginRequest);
	
	
}
