package com.inn.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.inn.cafe.POJO.User;

public interface UserDAO extends JpaRepository<User, Integer>{

    User findByEmailId(@Param("email") String email);
}
