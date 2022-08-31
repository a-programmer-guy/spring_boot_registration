package com.ctrlk.registration.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctrlk.registration.appuser.AppUser;
import com.ctrlk.registration.appuser.AppUserRole;
import com.ctrlk.registration.appuser.AppUserService;
import com.ctrlk.registration.registration.token.ConfirmationToken;
import com.ctrlk.registration.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

/*
 * Registration service will handle incoming registration requests
 * Validation and other logic with registration will occur here
*/
@Service
@AllArgsConstructor
public class RegistrationService {

	private final AppUserService appUserService;
	private final EmailValidator emailValidator;
	private final ConfirmationTokenService confirmationTokenService;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());

		if (!isValidEmail) {
			throw new IllegalStateException("Email is not valid");
		}
		String token = appUserService.signUpUser(new AppUser(request.getFirstName(), request.getLastName(),
				request.getUsername(), request.getEmail(), request.getPassword(), AppUserRole.USER));

		return token;
	}

	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("Token not found"));

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiresAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token expired");
		}
		confirmationTokenService.setConfirmedAt(token);
		appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
		return "confirmed";
	}

}
