package br.com.caelum.api.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	public static final String ROLE_RESTAURANTE = "RESTAURANTE";
	public static final String ROLE_CLIENTE = "CLIENTE";
	public static final String ROLE_ADMINISTRATIVO = "ADMINISTRATIVO";

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**/admin/**").hasRole(ROLE_ADMINISTRATIVO)
				.antMatchers(HttpMethod.POST, "/**/parceiros/restaurantes").hasRole(ROLE_RESTAURANTE)
				.antMatchers(HttpMethod.PUT, "/**/parceiros/restaurantes/**").hasRole(ROLE_RESTAURANTE)
				.antMatchers("/**/pedidos").hasRole(ROLE_CLIENTE);
	}

}
