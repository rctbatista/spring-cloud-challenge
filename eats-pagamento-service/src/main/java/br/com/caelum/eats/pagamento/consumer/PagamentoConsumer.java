package br.com.caelum.eats.pagamento.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.caelum.eats.pagamento.gateway.PedidoRestClientFacade;
import br.com.caelum.eats.pagamento.repository.PagamentoRepository;
import br.com.caelum.eats.pagamento.repository.entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class PagamentoConsumer {

	private final PagamentoRepository pagamentoRepo;
	private final PedidoRestClientFacade pedidoClient;

	private static final String PARTITION_EATS_PAGAMENTO = "0";

	@KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.topics.pedido.confirma.pagamento}", partitions = PARTITION_EATS_PAGAMENTO))
	public void confirmaPagamentoConsumer(@Payload Pagamento pagamentoEvent,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.info("Evento de confirmação de pedido recebido. [id: {}]", pagamentoEvent.getId());
		pedidoClient.notificaPagamentoDoPedido(pagamentoEvent.getPedidoId());
		pagamentoRepo.save(pagamentoEvent);
	}

}
