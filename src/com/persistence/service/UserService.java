package com.persistence.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.dao.IUserRepository;
import com.persistence.model.UserModel;
import com.request.UserRequest;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserModel addUser(UserRequest userRequest) {
		
		UserModel userModel = new UserModel();
		
		userModel.setName(userRequest.getName());
		userModel.setMobileNumber(userRequest.getMobileNumber());
		userModel.setEmailId(userRequest.getEmailId());
		userModel.setCreateDate(LocalDateTime.now());
		userModel.setTimestamp(LocalDateTime.now());
	
		userModel = userRepository.save(userModel);
		
		return userModel;
	}
	
}
