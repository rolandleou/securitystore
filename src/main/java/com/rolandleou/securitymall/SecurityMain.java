package com.rolandleou.securitymall;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Run as Java Application, only for test
public class SecurityMain {

	public static void main(String[] args) {
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = pe.encode("1234");
		System.out.println(ecode);
		boolean matches = pe.matches("1234", ecode);
		System.out.println(matches);
		
		System.out.println();
		
		String encode2 = pe.encode("1234");
		System.out.println(encode2);
		boolean matches2 = pe.matches("1234", encode2);
		System.out.println(matches2);	
		
		String ecode3 = "$2a$10$YAneWEhs/xnxrSXCYDtm4ubFZqLFBsZK2psVkZlCfYHU9ZeBmHrzC";
		boolean matches3 = pe.matches("1234", ecode3);
		System.out.println(matches3);		

		// 
		String mail = "test@gmail.com";
		System.out.println("mail: " + mail);
		mail = mail.substring(0, mail.indexOf("@"));
		System.out.println("account: " + mail);

	}

}
