package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.model.UserLoginTokenModel;
import com.persistence.model.UserModel;

public interface IUserLoginTokenRepository extends JpaRepository<UserLoginTokenModel, Long>{

}
