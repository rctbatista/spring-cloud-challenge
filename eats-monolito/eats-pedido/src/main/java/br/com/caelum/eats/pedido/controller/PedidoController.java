package br.com.caelum.eats.pedido.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.pedido.controller.domain.PedidoDto;
import br.com.caelum.eats.pedido.exception.ResourceNotFoundException;
import br.com.caelum.eats.pedido.repository.PedidoRepository;
import br.com.caelum.eats.pedido.repository.entity.Pedido;
import br.com.caelum.eats.pedido.service.PedidoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class PedidoController {

	private final PedidoService service;
	private final PedidoRepository repo;

	@GetMapping("/pedidos")
	public List<PedidoDto> lista() {
		return repo.findAll()
				.stream()
				.map(PedidoDto::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/pedidos/{id}")
	public PedidoDto porId(@PathVariable("id") Long id) {
		Pedido pedido = service.porIdComItens(id);
		return new PedidoDto(pedido);
	}

	@PostMapping("/pedidos")
	public PedidoDto adiciona(@RequestBody Pedido pedido) {
		pedido.setDataHora(LocalDateTime.now());
		pedido.setStatus(Pedido.Status.REALIZADO);
		pedido.getItens().forEach(item -> item.setPedido(pedido));
		pedido.getEntrega().setPedido(pedido);
		Pedido salvo = repo.save(pedido);
		return new PedidoDto(salvo);
	}

	@PutMapping("/pedidos/{pedidoId}/status")
	public PedidoDto atualizaStatus(@PathVariable Long pedidoId, @RequestBody Pedido pedidoParaAtualizar) throws InterruptedException {
		
		log.info("Solicitada atualização do pedido. [pedidoId: {}, id: {}, status: {}]", pedidoId, pedidoParaAtualizar.getId(), pedidoParaAtualizar.getStatus());
		
		if (System.currentTimeMillis() % 2 == 0) {
			Pedido pedido = service.porIdComItens(pedidoId);
			pedido.setStatus(pedidoParaAtualizar.getStatus());
			service.atualizaStatus(pedido.getStatus(), pedido);
			
			log.info("Finalizada atualização do pedido. [pedidoId: {}, id: {}, status: {}]", pedidoId, pedidoParaAtualizar.getId(), pedidoParaAtualizar.getStatus());
			return new PedidoDto(pedido);
		}
		
		Thread.sleep(30000);
		log.info("Erro ao atualizar status do pedido. [pedidoId: {}]", pedidoId);
		throw new RuntimeException("Erro ao atualizar status do pedido.");
		
	}

	@PutMapping("/pedidos/{id}/pago")
	public void pago(@PathVariable("id") Long id) {
		Pedido pedido = repo.porIdComItens(id).orElseThrow(ResourceNotFoundException::new);
		pedido.setStatus(Pedido.Status.PAGO);
		repo.atualizaStatus(Pedido.Status.PAGO, pedido);
	}


	@GetMapping("/parceiros/restaurantes/{restauranteId}/pedidos/pendentes")
	public List<PedidoDto> pendentes(@PathVariable("restauranteId") Long restauranteId) {
		return repo.doRestauranteSemOsStatus(restauranteId, Arrays.asList(Pedido.Status.REALIZADO, Pedido.Status.ENTREGUE)).stream()
				.map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
	}

}
