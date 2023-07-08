package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

  public Pagamento mapToEntity(PagamentoRequest pagamentoRequest) {
    return new Pagamento(
      pagamentoRequest.getCodigoDebito(),
      pagamentoRequest.getCpfCnpjPagador(),
      pagamentoRequest.getMetodoPagamento(),
      pagamentoRequest.getNumeroCartao(),
      pagamentoRequest.getValorPagamento()
    );
  }

  public PagamentoResponse mapToResponse(Pagamento pagamento) {
    PagamentoResponse pagamentoResponse = new PagamentoResponse();
    pagamentoResponse.setId(pagamento.getId());
    pagamentoResponse.setCodigoDebito(pagamento.getCodigoDebito());
    pagamentoResponse.setCpfCnpjPagador(pagamento.getCpfCnpjPagador());
    pagamentoResponse.setMetodoPagamento(pagamento.getMetodoPagamento());
    pagamentoResponse.setValorPagamento(pagamento.getValorPagamento());
    pagamentoResponse.setStatusPagamento(pagamento.getStatusPagamento());
    pagamentoResponse.setDataPagamento(pagamento.getDataPagamento());
    return pagamentoResponse;
  }

  public List<PagamentoResponse> mapToResponseList(List<Pagamento> pagamentos) {
    return pagamentos
      .stream()
      .map(this::mapToResponse)
      .collect(Collectors.toList());
  }
}
