package com.persistence.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.request.LoginRequest;
import com.dto.request.UserRequest;
import com.dto.response.LoginResponse;
import com.dto.response.UserResponse;
import com.persistence.dao.IUserLoginTokenRepository;
import com.persistence.dao.IUserRepository;
import com.persistence.model.UserLoginTokenModel;
import com.persistence.model.UserModel;
import com.util.BasicUtil;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserLoginTokenRepository userLoginTokenRepository;
	
	@Override
	public UserResponse addUser(UserRequest userRequest) {
		
		String name = userRequest.getName();
		String emailId = userRequest.getEmailId();
		String mobileNumber = userRequest.getMobileNumber();
		String password = userRequest.getPassword();
		
		
		UserModel userModel = userRepository.findByEmailId(emailId);
		
		if(!BasicUtil.isNull(userModel)){
			
			System.out.println("............User already exist with email id......: " + emailId);
			return null;
			
		}else{
			
			userModel = new UserModel();
			
			userModel.setName(userRequest.getName());
			userModel.setMobileNumber(userRequest.getMobileNumber());
			userModel.setEmailId(userRequest.getEmailId());
			userModel.setCreateDate(LocalDateTime.now());
			userModel.setTimestamp(LocalDateTime.now());
			userModel.setPassword(password);
			
			userModel = userRepository.save(userModel);
			
			return new UserResponse(name, emailId, mobileNumber);
		}
		
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

	@Override
	public LoginResponse loginExistingUser(LoginRequest loginRequest) {
		
		LoginResponse loginResponse= null;
		
		String emailId = loginRequest.getEmailId();
		String password = loginRequest.getPassword();
		UserModel userModel = userRepository.findByEmailIdAndPassword(emailId, password);
		
		if(!BasicUtil.isNull(userModel)){
			
			loginResponse = new LoginResponse();
			
			String mobileNumber = userModel.getMobileNumber();
			String name = userModel.getName();
			String accessToken = BasicUtil.createAccessToken(emailId);
			String refreshToken = BasicUtil.createRefreshToken();
			
			UserLoginTokenModel userLoginTokenModel = new UserLoginTokenModel(accessToken, 
					refreshToken, userModel);
			userLoginTokenRepository.save(userLoginTokenModel);
			
			
			//response to the client request
			loginResponse.setEmailId(emailId);
			loginResponse.setMobileNumber(mobileNumber);
			loginResponse.setName(name);
			loginResponse.setAccessToken(accessToken);		
			
		}else{
			System.out.println("Username and password does not match!!!!!!!!!!!!!! for username :.........." + emailId);
			
		}	
		
		return loginResponse;
	}
	
}
