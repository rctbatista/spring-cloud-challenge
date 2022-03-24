package br.com.caelum.eats.pagamento.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlteracaoPedidoException extends RuntimeException {

	private static final long serialVersionUID = 6305889052500754021L;
	public static final String MENSAGEM_ERRO = "Problema ao tentar mudar o status do pedido.";
	
	private final transient Long pedidoId;
	private final transient Throwable rootCause;
	
}
