package br.com.caelum.api.gateway.client.auth;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.caelum.api.gateway.client.auth.domain.ClientAuthenticationResponse;
import br.com.caelum.api.gateway.config.FeignLoginConfig;
import feign.Headers;

@FeignClient(name = "eats-auth", configuration = FeignLoginConfig.class)
public interface ClientAuthenticationRestClient {
	
	@Headers("Content-Type: application/x-www-form-urlencoded")
	@PostMapping(path = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ClientAuthenticationResponse run(@RequestHeader Map<String, Object> headers, @RequestBody Map<String, ?> form);

}
