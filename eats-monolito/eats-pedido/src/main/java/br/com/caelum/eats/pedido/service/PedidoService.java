package br.com.caelum.eats.pedido.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.caelum.eats.pedido.exception.ResourceNotFoundException;
import br.com.caelum.eats.pedido.repository.PedidoRepository;
import br.com.caelum.eats.pedido.repository.entity.Pedido;
import br.com.caelum.eats.pedido.repository.entity.Pedido.Status;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

	private final PedidoRepository repo;
	
	@HystrixCommand
	public Pedido porIdComItens(Long pedidoId) {
		return repo.porIdComItens(pedidoId).orElseThrow(ResourceNotFoundException::new);
	}

	@HystrixCommand
	public void atualizaStatus(Status status, Pedido pedido) {
		repo.atualizaStatus(status, pedido);
	}

}
