package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.MetodoPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.StatusPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.exception.PagamentoInvalidException;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import org.springframework.stereotype.Component;

@Component
public class PagamentoValidator {

  public void validarPagamentoPendenteProcessamento(Pagamento pagamento) {
    validarStatusPagamentoPendenteProcessamento(pagamento.getStatusPagamento());
  }

  public void validarPagamentoComCartao(PagamentoRequest pagamentoRequest) {
    MetodoPagamento metodoPagamento = pagamentoRequest.getMetodoPagamento();
    String numeroCartao = pagamentoRequest.getNumeroCartao();

    if (
      metodoPagamento == MetodoPagamento.CARTAO_CREDITO ||
      metodoPagamento == MetodoPagamento.CARTAO_DEBITO
    ) {
      validarNumeroCartao(numeroCartao);
    }
  }

  public void validarAtualizacaoStatusPagamento(
    Pagamento pagamento,
    StatusPagamento novoStatus
  ) {
    validarStatusPagamentoProcessadoComSucesso(pagamento.getStatusPagamento());
    validarAtualizacaoStatusPagamentoFalha(
      pagamento.getStatusPagamento(),
      novoStatus
    );
  }

  private void validarStatusPagamentoPendenteProcessamento(
    StatusPagamento statusPagamento
  ) {
    if (statusPagamento != StatusPagamento.PENDENTE_DE_PROCESSAMENTO) {
      throw new PagamentoInvalidException(
        "Não é possível excluir um pagamento que não está pendente de processamento."
      );
    }
  }

  void validarNumeroCartao(String numeroCartao) {
    if (numeroCartao == null || numeroCartao.isEmpty()) {
      throw new PagamentoInvalidException(
        "Número do cartão é obrigatório para pagamento com cartão."
      );
    }
  }

  private void validarStatusPagamentoProcessadoComSucesso(
    StatusPagamento statusPagamento
  ) {
    if (statusPagamento == StatusPagamento.PROCESSADO_COM_SUCESSO) {
      throw new PagamentoInvalidException(
        "Não é possível alterar o status de um pagamento processado com sucesso."
      );
    }
  }

  private void validarAtualizacaoStatusPagamentoFalha(
    StatusPagamento statusPagamento,
    StatusPagamento novoStatus
  ) {
    if (
      statusPagamento == StatusPagamento.PROCESSADO_COM_FALHA &&
      novoStatus != StatusPagamento.PENDENTE_DE_PROCESSAMENTO
    ) {
      throw new PagamentoInvalidException(
        "Não é possível alterar o status de um pagamento processado com falha para processado com sucesso."
      );
    }
  }
}
