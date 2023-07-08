package br.org.portalfadesp.servicopagamento.domain.pagamento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PagamentoInvalidException extends RuntimeException {

  public PagamentoInvalidException(String message) {
    super(message);
  }
}
