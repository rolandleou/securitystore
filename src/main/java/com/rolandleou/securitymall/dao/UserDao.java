package com.rolandleou.securitymall.dao;

import com.rolandleou.securitymall.dto.UserLoginRequest;
import com.rolandleou.securitymall.dto.UserRegisterRequest;
import com.rolandleou.securitymall.model.User;

public interface UserDao {

	Integer createUser(UserRegisterRequest userRegisterRequest);
	
	User getUserByEmail(UserRegisterRequest userRegisterRequest);
	
	User getUserById(Integer userId);
	
	User getUserByUserName(UserLoginRequest userLoginRequest);
}
