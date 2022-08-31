package com.ctrlk.registration.registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private final RegistrationService registrationService;
	
	// Must add Post Mapping to handler methods that handle POST requests
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
    	System.out.println("request: " + request);
        return registrationService.register(request);
    }
    // Mapping to handle get requests - this request sends the confirmation token 
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
    }
}
