 package com.ctrlk.registration.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

	@Override
	public boolean test(String t) {
		
		return true;
		
		/*
		 * // Regex that can handle googles special case with a + in the email. 
		 * String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@" +
		 * "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
		 * // create the EmailValidator instance from apache commons dependency
		 * EmailValidator validator = EmailValidator.getInstance(); return
		 * validator.isValid(email);
		 */
	}
}
