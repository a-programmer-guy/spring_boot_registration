package com.ctrlk.registration.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/*
 * ConfirmationTokenService will expose the ability for us to save,
 * retrieve and update a users confirmation token
 */

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	// Bring in the ConfirmationTokenRepository to access db
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	// Save the confirmation token to the db
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);	
	}
	// Retrieve token from DB
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
    // Set the confirmation time
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

}
