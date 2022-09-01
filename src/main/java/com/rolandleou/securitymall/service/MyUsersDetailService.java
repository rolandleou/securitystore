package com.rolandleou.securitymall.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rolandleou.securitymall.repository.UserDao;


@ConfigurationProperties(prefix="user")
@Configuration
public class MyUsersDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername: " + username);
		// 1. 查詢用戶是否存在 ?
		Optional<Entry<String, Map<String, String>>> opt= userDao.users
					.entrySet()
					.stream()
					.filter(e -> e.getKey().equals(username))
					.findFirst();
		if(!opt.isPresent()) throw new UsernameNotFoundException("Not found!");
		
		// 2. 取得相關資料並進行密碼比對
		Map<String, String> info = opt.get().getValue();
		String password = info.get("password");
		String authority = info.get("authority");
		return new User(username, 
						password, 
						AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
	}


}
