package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

  private final PagamentoRepository pagamentoRepository;
  private final PagamentoMapper pagamentoMapper;

  public PagamentoService(
    PagamentoRepository pagamentoRepository,
    PagamentoMapper pagamentoMapper
  ) {
    this.pagamentoRepository = pagamentoRepository;
    this.pagamentoMapper = pagamentoMapper;
  }

  public PagamentoResponse receberPagamento(PagamentoRequest pagamentoRequest) {
    validarPagamentoRequest(pagamentoRequest);
    Pagamento pagamento = pagamentoMapper.mapToEntity(pagamentoRequest);
    pagamento = pagamentoRepository.save(pagamento);
    return pagamentoMapper.mapToResponse(pagamento);
  }

  public PagamentoResponse atualizarStatusPagamento(
    Long pagamentoId,
    StatusPagamento novoStatus
  ) {
    Pagamento pagamento = buscarPagamentoPorId(pagamentoId);
    validarAtualizacaoStatusPagamento(pagamento, novoStatus);
    pagamento.setStatusPagamento(novoStatus);
    pagamento = pagamentoRepository.save(pagamento);
    return pagamentoMapper.mapToResponse(pagamento);
  }

  public List<PagamentoResponse> listarPagamentos() {
    List<Pagamento> pagamentos = pagamentoRepository.findAll();
    return pagamentoMapper.mapToResponseList(pagamentos);
  }

  public List<PagamentoResponse> listarPagamentosComFiltros(
    Integer codigoDebito,
    String cpfCnpjPagador,
    StatusPagamento statusPagamento
  ) {
    List<Pagamento> pagamentos = pagamentoRepository.listarPagamentosComFiltrosOpcionais(
      codigoDebito,
      cpfCnpjPagador,
      statusPagamento
    );
    return pagamentoMapper.mapToResponseList(pagamentos);
  }

  public void deletarPagamento(Long pagamentoId) {
    Pagamento pagamento = buscarPagamentoPorId(pagamentoId);
    validarPagamentoPendenteProcessamento(pagamento);
    pagamentoRepository.delete(pagamento);
  }

  private Pagamento buscarPagamentoPorId(Long pagamentoId) {
    return pagamentoRepository
      .findById(pagamentoId)
      .orElseThrow(PagamentoNotFoundException::new);
  }

  private void validarPagamentoPendenteProcessamento(Pagamento pagamento) {
    if (
      pagamento.getStatusPagamento() !=
      StatusPagamento.PENDENTE_DE_PROCESSAMENTO
    ) {
      throw new PagamentoInvalidException(
        "Não é possível excluir um pagamento que não está pendente de processamento"
      );
    }
  }

  private void validarPagamentoRequest(PagamentoRequest pagamentoRequest) {
    MetodoPagamento metodoPagamento = pagamentoRequest.getMetodoPagamento();

    if (
      metodoPagamento == MetodoPagamento.CARTAO_CREDITO ||
      metodoPagamento == MetodoPagamento.CARTAO_DEBITO
    ) {
      String numeroCartao = pagamentoRequest.getNumeroCartao();

      if (numeroCartao == null || numeroCartao.isEmpty()) {
        throw new PagamentoInvalidException(
          "Número do cartão é obrigatório para pagamento com cartão."
        );
      }
    }
  }

  private void validarAtualizacaoStatusPagamento(
    Pagamento pagamento,
    StatusPagamento novoStatus
  ) {
    StatusPagamento statusAtual = pagamento.getStatusPagamento();

    if (statusAtual == StatusPagamento.PROCESSADO_COM_SUCESSO) {
      throw new PagamentoInvalidException(
        "Não é possível alterar o status de um pagamento processado com sucesso"
      );
    }

    if (
      statusAtual == StatusPagamento.PROCESSADO_COM_FALHA &&
      novoStatus != StatusPagamento.PENDENTE_DE_PROCESSAMENTO
    ) {
      throw new PagamentoInvalidException(
        "Não é possível alterar o status de um pagamento processado com falha"
      );
    }
  }
}
