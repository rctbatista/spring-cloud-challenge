package br.com.caelum.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableFeignClients
@EnableZuulProxy
@EnableResourceServer
@SpringBootApplication
public class EatsApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatsApiGatewayApplication.class, args);
	}

}
