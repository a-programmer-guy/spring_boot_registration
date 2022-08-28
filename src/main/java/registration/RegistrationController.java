package registration;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/*RestController allows restful web services - allows class to handle requests
from the client
RequestMapping maps web requests onto specific handler classes/methods
AllArgsConstructer generates a class with one parameter for every field in the class*/

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

	/*
	 * Reference the RegistrationService
	 */	
	private RegistrationService registrationService;
	
	public String register(@RequestBody RegistrationService request) {
		return registrationService.register(request);
	}
}
