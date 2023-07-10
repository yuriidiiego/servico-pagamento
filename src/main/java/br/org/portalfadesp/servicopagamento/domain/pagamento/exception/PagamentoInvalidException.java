package br.org.portalfadesp.servicopagamento.domain.pagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PagamentoInvalidException extends RuntimeException {

  public PagamentoInvalidException(String message) {
    super(message);
  }
}
