package com.rolandleou.securitymall.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegisterRequest {

	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	@NotBlank
	@Email
	private String email;
	
	
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

	
}
