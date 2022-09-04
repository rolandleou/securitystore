package com.rolandleou.securitymall.dao;

import com.rolandleou.securitymall.dto.UserRegisterRequest;
import com.rolandleou.securitymall.model.User;

public interface UserDao {

	Integer createUser(UserRegisterRequest userRegisterRequest);
	
	User getUserById(Integer userId);
}
