package com.persistence.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.dao.IUserRepository;
import com.persistence.model.UserModel;

import dto.request.UserRequest;
import dto.response.UserResponse;

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

	@Override
	public List<UserResponse> listOfUsers() {
		
		List<UserResponse> userResponseList = new ArrayList<>();
		
		userRepository.findAll().stream().forEach(model ->{
			
			UserResponse userResponse = new UserResponse();
			
			userResponse.setName(model.getName());
			userResponse.setEmailId(model.getEmailId());
			userResponse.setMobileNumber(model.getMobileNumber());
			
			userResponseList.add(userResponse);
		});
		
		
		return userResponseList;
	}
	
}
