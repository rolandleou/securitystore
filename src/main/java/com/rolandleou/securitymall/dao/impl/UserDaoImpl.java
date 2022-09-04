package com.rolandleou.securitymall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rolandleou.securitymall.dao.UserDao;
import com.rolandleou.securitymall.dto.UserRegisterRequest;
import com.rolandleou.securitymall.model.User;
import com.rolandleou.securitymall.rowmapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Integer createUser(UserRegisterRequest userRegisterRequest) {
		String sql = "INSERT INTO user (userName, password, email, role, enabled, "
				+ "created_date, last_modified_date) VALUES (:userName, :password, "
				+ ":email, :role, :enabled, :createdDate, :lastModifiedDate)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userRegisterRequest.getUserName());
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = pe.encode(userRegisterRequest.getPassword());
		map.put("password", ecode);
		map.put("email", userRegisterRequest.getEmail());
	
		// default register role ROLE_USER and enabled is active
		map.put("role", "ROLE_USER");
		
		// default enabled as 1
		Boolean enabled = true;
		map.put("enabled", enabled);
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
		
		Integer userId = keyHolder.getKey().intValue();
		
		return userId;
	}

	@Override
	public User getUserById(Integer userId) {
		String sql = "SELECT user_id, username, password, email, role, enabled, created_date, "
				+ "last_modified_date FROM user WHERE user_id = :userId";

		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;			
		}

	}

	@Override
	public User getUserByEmail(UserRegisterRequest userRegisterRequest) {
		String sql = "SELECT user_id, username, password, email, role, enabled, created_date, "
				+ "last_modified_date FROM user WHERE email = :email";

		Map<String, Object> map = new HashMap<>();
		map.put("email", userRegisterRequest.getEmail());
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;			
		}

	}

}
