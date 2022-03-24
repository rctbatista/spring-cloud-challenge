package br.com.caelum.api.gateway.client.auth;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import br.com.caelum.api.gateway.client.auth.domain.ClientAuthenticationResponse;
import br.com.caelum.api.gateway.controller.domain.LoginRequest;
import feign.FeignException.FeignClientException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class ClientAuthenticationRestFacade {
	
	private final ClientAuthenticationRestClient client;
	
	public ClientAuthenticationResponse run(LoginRequest request) {
		
		String encodedCredentials = null;
		ClientAuthenticationResponse response = null;
		
		try {
			encodedCredentials = Base64.getEncoder().encodeToString(("api-gateway:gtwsecret").getBytes("UTF-8"));
			
			Map<String, Object> headers = new HashMap<>();
			headers.put(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
			
			Map<String, String> form = new HashMap<>();
			form.put("scope", "internal");
			form.put("grant_type", "password");
			form.put("username", request.getUsername());
			form.put("password", request.getPassword());
			
			response = this.client.run(headers, form);
		} catch (UnsupportedEncodingException e) {
			log.error("Erro ao transformar String em Base64", e);
		} catch (FeignClientException e) {
			log.info(encodedCredentials);
			log.info(e.contentUTF8());
			log.error("Erro ao realizar login", e);
		}
		
		return response;
				
	}

}
