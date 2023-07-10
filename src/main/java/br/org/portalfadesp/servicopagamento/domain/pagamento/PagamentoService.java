package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.StatusPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.exception.PagamentoNotFoundException;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

  private final PagamentoRepository pagamentoRepository;
  private final PagamentoMapper pagamentoMapper;
  private final PagamentoValidator pagamentoValidator;

  public PagamentoService(
    PagamentoRepository pagamentoRepository,
    PagamentoMapper pagamentoMapper,
    PagamentoValidator pagamentoValidator
  ) {
    this.pagamentoRepository = pagamentoRepository;
    this.pagamentoMapper = pagamentoMapper;
    this.pagamentoValidator = pagamentoValidator;
  }

  public PagamentoResponse receberPagamento(PagamentoRequest pagamentoRequest) {
    pagamentoValidator.validarPagamento(pagamentoRequest);
    Pagamento pagamento = pagamentoMapper.mapPagamentoRequestToEntity(
      pagamentoRequest
    );
    pagamento = pagamentoRepository.save(pagamento);
    return pagamentoMapper.mapPagamentoToResponse(pagamento);
  }

  public PagamentoResponse atualizarStatusPagamento(
    Long pagamentoId,
    StatusPagamento novoStatus
  ) {
    Pagamento pagamento = buscarPagamentoPorId(pagamentoId);
    pagamentoValidator.validarAtualizacaoStatusPagamento(pagamento, novoStatus);
    pagamento.setStatusPagamento(novoStatus);
    pagamento = pagamentoRepository.save(pagamento);
    return pagamentoMapper.mapPagamentoToResponse(pagamento);
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
    pagamentoValidator.validarPagamentoPendenteProcessamento(pagamento);
    pagamentoRepository.delete(pagamento);
  }

  private Pagamento buscarPagamentoPorId(Long pagamentoId) {
    return pagamentoRepository
      .findById(pagamentoId)
      .orElseThrow(PagamentoNotFoundException::new);
  }
}
