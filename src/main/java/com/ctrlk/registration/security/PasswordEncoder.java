package com.ctrlk.registration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * Password encoder is used to encode the password. It can be used to verify the encoded
 * password obtained from storage matches the submitted raw password after it too is encoded
*/

@Configuration
public class PasswordEncoder {
	
	@Bean //Bean annotation so this is managed by the Spring contect and we can use it
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
