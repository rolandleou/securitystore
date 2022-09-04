package com.rolandleou.securitymall.service;

import com.rolandleou.securitymall.dto.UserLoginRequest;
import com.rolandleou.securitymall.dto.UserRegisterRequest;
import com.rolandleou.securitymall.model.User;

public interface UserService {

	Integer register(UserRegisterRequest userRegisterRequest);
	
	User getUserById(Integer userId);
	
	User login(UserLoginRequest userLoginRequest);
}
