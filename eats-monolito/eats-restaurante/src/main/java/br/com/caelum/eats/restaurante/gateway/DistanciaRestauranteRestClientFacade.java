package br.com.caelum.eats.restaurante.gateway;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class DistanciaRestauranteRestClientFacade {
	
	private final DistanciaRestauranteFeignRestClient restClient;
	
	public void criaDistanciaRestaurante(RestauranteRequest request) {
		   
        try {
        	this.restClient.criaDistanciaRestaurante(request);
		} catch (FeignException e) {
			log.error("Erro ao chamar API para grava��o de dist�ncia do restaurante.", e);
			throw new RuntimeException("Problema ao tentar cadastrar dist�ncia do restaurante. restauranteId: " + request.getId());
		}
        
    }
	
	public void atualizaDistanciaRestaurante(RestauranteRequest request) {
		   
        try {
        	this.restClient.atualizaDistanciaRestaurante(BigInteger.valueOf(request.getId()), request);
		} catch (FeignException e) {
			log.error("Erro ao chamar API para atualiza��o de dist�ncia do restaurante.", e);
			throw new RuntimeException("Problema ao tentar alterar dist�ncia do restaurante. restauranteId: " + request.getId());
		}
        
    }

}
