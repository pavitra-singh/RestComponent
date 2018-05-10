package com.myValuePack.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myValuePack.persistence.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Long>{

}
