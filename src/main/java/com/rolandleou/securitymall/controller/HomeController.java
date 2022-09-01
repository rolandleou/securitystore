package com.rolandleou.securitymall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "loginpage";
	}

	
	@RequestMapping("/fail")
	@ResponseBody
	public String fail() {
		return "fail";
	}
}
