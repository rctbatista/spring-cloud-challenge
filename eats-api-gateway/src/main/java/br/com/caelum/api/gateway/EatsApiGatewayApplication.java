package br.com.caelum.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class EatsApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatsApiGatewayApplication.class, args);
	}

}
