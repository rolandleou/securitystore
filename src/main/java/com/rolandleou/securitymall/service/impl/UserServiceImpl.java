package com.rolandleou.securitymall.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rolandleou.securitymall.dao.UserDao;
import com.rolandleou.securitymall.dto.UserRegisterRequest;
import com.rolandleou.securitymall.model.User;
import com.rolandleou.securitymall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		return userDao.createUser(userRegisterRequest);
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

}
