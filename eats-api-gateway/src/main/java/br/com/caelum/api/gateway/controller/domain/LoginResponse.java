package br.com.caelum.api.gateway.controller.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String accessToken;
	
	private String tokenType;
	
}
