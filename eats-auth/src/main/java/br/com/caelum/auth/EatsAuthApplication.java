package br.com.caelum.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class EatsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatsAuthApplication.class, args);
	}

}
