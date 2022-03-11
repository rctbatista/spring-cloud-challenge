package br.com.caelum.eats.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.restaurante.repository.entity.Cardapio;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

	Cardapio findByRestaurante(Restaurante restaurante);
}
