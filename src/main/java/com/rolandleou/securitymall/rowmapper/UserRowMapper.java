package com.rolandleou.securitymall.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rolandleou.securitymall.constant.UserRole;
import com.rolandleou.securitymall.model.User;

public class UserRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		user.setUserId(rs.getInt("user_id"));
		user.setUserName(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setRole(UserRole.valueOf(rs.getString("role")));
		user.setEnabled(rs.getInt("enabled"));
		user.setCreatedDate(rs.getTimestamp("created_date"));
		user.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		return user;
	}

}
