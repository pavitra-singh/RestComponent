package com.myValuePack.persistence.service;

import com.myValuePack.persistence.model.UserModel;
import com.myValuePack.request.UserRequest;

public interface IUserService {

	public UserModel addUser(UserRequest userRequest);
	
}
