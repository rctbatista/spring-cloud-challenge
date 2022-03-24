package br.com.caelum.api.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.api.gateway.client.auth.ClientAuthenticationRestFacade;
import br.com.caelum.api.gateway.client.auth.domain.ClientAuthenticationResponse;
import br.com.caelum.api.gateway.controller.domain.LoginRequest;
import br.com.caelum.api.gateway.controller.domain.LoginResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class LoginController {
	
	private final ClientAuthenticationRestFacade clientAuthenticationRestFacade;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		
		ClientAuthenticationResponse authResponse = clientAuthenticationRestFacade.run(request);
		
		LoginResponse response = new LoginResponse();
		response.setAccessToken(authResponse.getAccessToken());
		response.setTokenType(authResponse.getTokenType());
		
		return response;
		
	}
	
}
