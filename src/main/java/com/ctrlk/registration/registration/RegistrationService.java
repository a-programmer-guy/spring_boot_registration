package com.ctrlk.registration.registration;

import org.springframework.stereotype.Service;

import com.ctrlk.registration.appuser.AppUser;
import com.ctrlk.registration.appuser.AppUserRole;
import com.ctrlk.registration.appuser.AppUserService;

import lombok.AllArgsConstructor;

/*
 * Registration service will handle incoming registration requests
 * Validation and other logic with registration will occur here
*/
@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final AppUserService appUserService;
	private EmailValidator emailValidator;
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		
		if(!isValidEmail) {
			throw new IllegalStateException("Email is not valid");
		}
		return appUserService.signUpUser(
				new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                        )
		);
	}

}
