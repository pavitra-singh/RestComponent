package com.persistence.service;

import java.util.List;

import com.persistence.model.UserModel;

import dto.request.UserRequest;
import dto.response.UserResponse;

public interface IUserService {

	public UserModel addUser(UserRequest userRequest);
	public List<UserResponse> listOfUsers();
	
	
}
