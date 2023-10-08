package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.StatusPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos", description = "API para operações de pagamentos")
public class PagamentoController implements PagamentoOperation {

  private final PagamentoService pagamentoService;

  public PagamentoController(PagamentoService pagamentoService) {
    this.pagamentoService = pagamentoService;
  }

  @Override
  public ResponseEntity<PagamentoResponse> receberPagamento(
    @Valid PagamentoRequest pagamentoRequest
  ) {
    PagamentoResponse pagamentoResponse = pagamentoService.receberPagamento(
      pagamentoRequest
    );
    return ResponseEntity
      .created(URI.create("/pagamentos/" + pagamentoResponse.getId()))
      .body(pagamentoResponse);
  }

  @Override
  public ResponseEntity<PagamentoResponse> atualizarStatusPagamento(
    Long pagamentoId,
    StatusPagamento novoStatus
  ) {
    PagamentoResponse pagamentoResponse = pagamentoService.atualizarStatusPagamento(
      pagamentoId,
      novoStatus
    );
    return ResponseEntity.ok(pagamentoResponse);
  }

  @Override
  public ResponseEntity<List<PagamentoResponse>> listarPagamentos(
    Integer codigoDebito,
    String cpfCnpjPagador,
    StatusPagamento statusPagamento
  ) {
    List<PagamentoResponse> pagamentos = pagamentoService.listarPagamentosComFiltros(
      codigoDebito,
      cpfCnpjPagador,
      statusPagamento
    );
    return ResponseEntity.ok(pagamentos);
  }

  @Override
  public ResponseEntity<Void> deletarPagamento(Long pagamentoId) {
    pagamentoService.deletarPagamento(pagamentoId);
    return ResponseEntity.noContent().build();
  }
}
