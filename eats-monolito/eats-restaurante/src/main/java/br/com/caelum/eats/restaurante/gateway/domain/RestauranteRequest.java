package br.com.caelum.eats.restaurante.gateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequest {

	private Long id;

	private String cep;

	private Long tipoDeCozinhaId;

}
