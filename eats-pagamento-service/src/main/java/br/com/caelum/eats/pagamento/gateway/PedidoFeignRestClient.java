package br.com.caelum.eats.pagamento.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.caelum.eats.pagamento.gateway.domain.PedidoMudancaDeStatusRequest;

@FeignClient(name = "eats-monolito")
public interface PedidoFeignRestClient {

	@PutMapping("/pedidos/{pedidoId}/status")
	public void notificaPagamentoDoPedido(@PathVariable("pedidoId") Long pedidoId, PedidoMudancaDeStatusRequest request);
	
}
