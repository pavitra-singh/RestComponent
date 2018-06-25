package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Long>{

}
