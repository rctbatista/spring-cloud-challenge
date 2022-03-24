package br.com.caelum.eats.pagamento.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.caelum.eats.pagamento.repository.entity.Pagamento;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	@Value("${kafka.server.url}")
	private String bootstrapAddress;

	@Bean
	public ConsumerFactory<String, Pagamento> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Pagamento.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Pagamento> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Pagamento> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}