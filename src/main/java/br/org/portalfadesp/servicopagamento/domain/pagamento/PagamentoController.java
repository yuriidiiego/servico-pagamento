package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

  private final PagamentoService pagamentoService;

  public PagamentoController(PagamentoService pagamentoService) {
    this.pagamentoService = pagamentoService;
  }

  @PostMapping
  public ResponseEntity<PagamentoResponse> receberPagamento(
    @Valid @RequestBody PagamentoRequest pagamentoRequest
  ) {
    PagamentoResponse pagamentoResponse = pagamentoService.receberPagamento(
      pagamentoRequest
    );
    return ResponseEntity
      .created(URI.create("/pagamentos/" + pagamentoResponse.getId()))
      .body(pagamentoResponse);
  }

  @PatchMapping("/{pagamentoId}/status")
  public ResponseEntity<PagamentoResponse> atualizarStatusPagamento(
    @PathVariable Long pagamentoId,
    @RequestParam StatusPagamento novoStatus
  ) {
    PagamentoResponse pagamentoResponse = pagamentoService.atualizarStatusPagamento(
      pagamentoId,
      novoStatus
    );
    return ResponseEntity.ok(pagamentoResponse);
  }

  @GetMapping
  public ResponseEntity<List<PagamentoResponse>> listarPagamentos(
    @RequestParam(
      value = "codigoDebito",
      required = false
    ) Integer codigoDebito,
    @RequestParam(
      value = "cpfCnpjPagador",
      required = false
    ) String cpfCnpjPagador,
    @RequestParam(
      value = "statusPagamento",
      required = false
    ) StatusPagamento statusPagamento
  ) {
    List<PagamentoResponse> pagamentos = pagamentoService.listarPagamentosComFiltros(
      codigoDebito,
      cpfCnpjPagador,
      statusPagamento
    );
    return ResponseEntity.ok(pagamentos);
  }

  @DeleteMapping("/{pagamentoId}")
  public ResponseEntity<Void> deletarPagamento(@PathVariable Long pagamentoId) {
    pagamentoService.deletarPagamento(pagamentoId);
    return ResponseEntity.noContent().build();
  }
}
