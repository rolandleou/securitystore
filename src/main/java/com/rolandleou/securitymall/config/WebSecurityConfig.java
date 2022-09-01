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
		.anyRequest().authenticated() // 認證後允許拜訪
	.and()
		.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/loginpage")
		.successForwardUrl("/")
		.failureForwardUrl("/fail");
		
		
/*		
		// 表單提交
		http.formLogin()
			// loginpage.html 表單 action 內容
			.loginProcessingUrl("/login")
			// 自定義登入頁面
			.loginPage("/loginpage")
			// 登入成功之後要造訪的頁面
			//.usernameParameter("username")///登入表單form中使用者名稱輸入框input的name名，不修改的話預設是username
	        //.passwordParameter("password")//form中密碼輸入框input的name名，不修改的話預設是password
			.successForwardUrl("/")  // welcome 頁面
			// 登入失敗後要造訪的頁面
			.failureForwardUrl("/fail");
		
		// 授權認證
		http.authorizeHttpRequests()
			// 不需要被認證的頁面：/loginpage
			.antMatchers("/loginpage").permitAll()
			// 權限判斷
			// 必須要有 admin 權限才可以訪問
			.antMatchers("/adminpage").hasAuthority("admin")
			// 必須要有 manager 角色才可以訪問
			.antMatchers("/managerpage").hasRole("manager")
			// 其他指定任意角色都可以訪問
			.antMatchers("/employeepage").hasAnyRole("manager", "employee")
			// 其他的都要被認證
			//.antMatchers("/products/{productId}").permitAll()
			// 查詢商品權限
			.anyRequest().authenticated()
			;
*/		
		// http.csrf().disable(); // 關閉 csrf 防護
	
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
	
	// 注意！規定！要建立密碼演算的實例

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// 設定自定義密碼
		// 'admin', 'abc123' 
		// 'uuu123', 'ccc123'
		// '123456', 'dog555'
		// '888', 'yuyu'
		
        String sql="SELECT username,password,enabled FROM user WHERE username=?";
        String authorsql="SELECT username,role FROM user WHERE username=?";
       // auth.userDetailsService(userDetailsService).passwordEncoder(password());
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(sql)
                .authoritiesByUsernameQuery(authorsql)
                .passwordEncoder(new BCryptPasswordEncoder());
        
	}
	
	

}
