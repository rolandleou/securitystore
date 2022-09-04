package com.rolandleou.securitymall.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.rolandleou.securitymall.handle.MyAccessDeniedHandler;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private DataSource dataSource;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http
		.authorizeHttpRequests()
		.antMatchers("/loginpage").permitAll()
		.antMatchers(HttpMethod.POST, "/products/**").authenticated()
        .antMatchers(HttpMethod.PUT, "/products/**").authenticated()
        .antMatchers(HttpMethod.DELETE, "/products/**").authenticated()
		.antMatchers(HttpMethod.POST, "/users/**").authenticated()
		//.anyRequest().permitAll() // 認證所有拜訪權限
		.anyRequest().authenticated() // 認證後允許拜訪
		.and()
		.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/loginpage")
		.successForwardUrl("/")
		.failureForwardUrl("/fail");
	
		http.csrf().disable(); // 關閉 csrf 防護
	
		// 登出
		http.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // 可以使用任何的 HTTP 方法登出
	
		// 異常處理
		http.exceptionHandling()
			//.accessDeniedPage("/異常處理頁面");  // 請自行撰寫
			.accessDeniedHandler(myAccessDeniedHandler);
		
	}	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String sql="SELECT username,password,enabled FROM user WHERE username=?";
        String authorsql="SELECT username,role FROM user WHERE username=?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(sql)
                .authoritiesByUsernameQuery(authorsql)
                .passwordEncoder(getPasswordEncoder());
        
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
