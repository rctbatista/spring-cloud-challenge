package br.com.caelum.api.gateway.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import feign.codec.Encoder;
import feign.form.FormEncoder;

public class FeignLoginConfig {

	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	@Primary
	public Encoder feignFormEncoder() {
		return new FormEncoder(new SpringEncoder(this.messageConverters));
	}

}
