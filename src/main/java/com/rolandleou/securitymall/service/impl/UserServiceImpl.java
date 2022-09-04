package com.rolandleou.securitymall.service.impl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.rolandleou.securitymall.dao.UserDao;
import com.rolandleou.securitymall.dto.UserLoginRequest;
import com.rolandleou.securitymall.dto.UserRegisterRequest;
import com.rolandleou.securitymall.model.User;
import com.rolandleou.securitymall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class); 
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		// Check register email if exist?
		User user = userDao.getUserByEmail(userRegisterRequest);
		
		if (user != null) {
			log.warn("email {} alreday registered!!", userRegisterRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		// create register account
		return userDao.createUser(userRegisterRequest);
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public User login(UserLoginRequest userLoginRequest) {
		User user = userDao.getUserByUserName(userLoginRequest);
		
		if (user == null) {
			log.warn("User {} nor exist!!", userLoginRequest.getUserName());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);			
		}

		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = user.getPassword();
		boolean matches = pe.matches(userLoginRequest.getPassword(), ecode);
		System.out.println("Password matched: " + matches);
		
		if (matches) {
			return user;
		} else {
			log.warn("User {} password incorrect!!", userLoginRequest.getUserName());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);				
		}
		
	}

}
