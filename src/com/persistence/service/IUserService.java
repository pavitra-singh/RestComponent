package com.persistence.service;

import com.persistence.model.UserModel;
import com.request.UserRequest;

public interface IUserService {

	public UserModel addUser(UserRequest userRequest);
	
}
