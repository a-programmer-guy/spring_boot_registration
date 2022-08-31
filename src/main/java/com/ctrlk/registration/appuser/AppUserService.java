package com.ctrlk.registration.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctrlk.registration.registration.token.ConfirmationToken;
import com.ctrlk.registration.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

/* AppUserService implements an interface to allows us to find users once we log in
UserDetailsService is a spring security package - auto generates loadUserByUsername method
ALlArgsConstructor - Lombok annotation to generate a constructor with 1 parameter for each field in the class*/

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

	private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
	/*
	 * Create AppUserRepository, BCryptPasswordEncoder, and ConfirmationTokenService 
	 * objects to access ther methods
	 */
	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;

	// Load user if they exist - check if user email exists or throw exception.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

	// Add user to db if the email isn't already in use - encode password
	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

		if (userExists) {
			throw new IllegalStateException("Email already in use");
		}
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		// Save encoded password
		appUser.setPassword(encodedPassword);

		// Save user to DB
		appUserRepository.save(appUser);

		// Create a universally unique identifier (UUID) for the users token
		String token = UUID.randomUUID().toString();
		
		// Create a confirmation token and pass in all the required attributes set in
		// the ConfirmationToken class.
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(30), appUser);
		
		//Save the token in the DB via ConfirmationTokenService layer
		confirmationTokenService.saveConfirmationToken(confirmationToken);

		// TODO send confirmation email
	
		return token;
	}

	// Update users enabled status using the email. 
	public int enableAppUser(String email) {
		return appUserRepository.enableAppUser(email);
		
	}
}
