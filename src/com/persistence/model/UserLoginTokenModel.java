package com.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.persistence.converter.LocalDateTimeAttributeConverter;

@Entity(name = "user_login_token")
public class UserLoginTokenModel {
	
	@Id
    @Column(name="user_login_token_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userLoginTokenId;
	
	@Column(name="access_token")
	private String accessToken;
	
	@Column(name="refresh_token")
	private String refreshToken;
	
	@Column(name="token_timestamp")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime tokenTimestamp;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserModel userId;

	public long getUserLoginTokenId() {
		return userLoginTokenId;
	}

	public void setUserLoginTokenId(long userLoginTokenId) {
		this.userLoginTokenId = userLoginTokenId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public LocalDateTime getTokenTimestamp() {
		return tokenTimestamp;
	}

	public void setTokenTimestamp(LocalDateTime tokenTimestamp) {
		this.tokenTimestamp = tokenTimestamp;
	}

	public UserModel getUserId() {
		return userId;
	}

	public void setUserId(UserModel userId) {
		this.userId = userId;
	}
	
	public UserLoginTokenModel() {
		super();
	}

	
	
	public UserLoginTokenModel(String accessToken, String refreshToken, UserModel userId) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userId = userId;
	}
	
	
	
	
}
