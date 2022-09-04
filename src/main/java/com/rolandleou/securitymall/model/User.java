package com.rolandleou.securitymall.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rolandleou.securitymall.constant.UserRole;

public class User {

	private Integer userId;
	private String userName;
	
	@JsonIgnore
	private String password;
	
	private String email;
	private UserRole role;
	private Integer enabled;
	private Date createdDate;
	private Date lastModifiedDate;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	
}
