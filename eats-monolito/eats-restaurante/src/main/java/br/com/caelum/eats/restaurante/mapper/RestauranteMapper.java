package br.com.caelum.eats.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

	@Mapping(target = "tipoDeCozinhaId", source = "tipoDeCozinha.id")
	RestauranteRequest toRequest(Restaurante input);
	
}
