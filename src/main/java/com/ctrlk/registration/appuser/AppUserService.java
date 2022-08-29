package com.ctrlk.registration.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/* AppUserService implements an interface to allows us to find users once we log in
UserDetailsService is a spring security package - auto generates loadUserByUsername method
ALlArgsConstructor - Lombok annotation to generate a constructor with 1 parameter for each field in the class*/

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	
	private final static String USER_NOT_FOUND_MSG = 
			"User with email %s not found";
	private final AppUserRepository appUserRepository = null;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	
	public String signUpUser(AppUser appUser) {
		return "";
	}
}
