package com.ctrlk.registration.registration.token;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/*
 * ConfirmationTokenService will expose the ability for us to save a users confirmation token
 */

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	// Bring in the ConfirmationTokenRepository to access db
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	// Function to save the confirmation token to the db
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
		
	}

}
