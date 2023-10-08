package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.StatusPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PagamentoOperation {
  @PostMapping
  @Operation(
    summary = "Criar um novo pagamento",
    description = "Endpoint para receber o pagamento",
    operationId = "receberPagamento"
  )
  ResponseEntity<PagamentoResponse> receberPagamento(
    @Valid @RequestBody PagamentoRequest pagamentoRequest
  );

  @PatchMapping("/{pagamentoId}/status")
  @Operation(
    summary = "Atualizar o status de um pagamento",
    description = "Endpoint para atualizar o status de um pagamento",
    operationId = "atualizarStatusPagamento"
  )
  ResponseEntity<PagamentoResponse> atualizarStatusPagamento(
    @PathVariable @Parameter(
      name = "pagamentoId",
      example = "1",
      required = true
    ) Long pagamentoId,
    @RequestParam @Parameter(
      name = "novoStatus",
      description = "Novo status do pagamento",
      required = true
    ) StatusPagamento novoStatus
  );

  @GetMapping
  @Operation(
    summary = "Listar pagamentos com filtros opcionais",
    description = "Endpoint para listar pagamentos",
    operationId = "listarPagamentos"
  )
  ResponseEntity<List<PagamentoResponse>> listarPagamentos(
    @RequestParam(value = "codigoDebito", required = false) @Parameter(
      name = "codigoDebito",
      description = "Código do débito",
      example = "12345"
    ) Integer codigoDebito,
    @RequestParam(value = "cpfCnpjPagador", required = false) @Parameter(
      name = "cpfCnpjPagador",
      description = "CPF ou CNPJ do pagador",
      example = "12345678901"
    ) String cpfCnpjPagador,
    @RequestParam(value = "statusPagamento", required = false) @Parameter(
      name = "statusPagamento",
      description = "Status do pagamento"
    ) StatusPagamento statusPagamento
  );

  @DeleteMapping("/{pagamentoId}")
  @Operation(
    summary = "Deletar um pagamento",
    description = "Endpoint para deletar um pagamento",
    operationId = "deletarPagamento"
  )
  ResponseEntity<Void> deletarPagamento(
    @PathVariable @Parameter(
      name = "pagamentoId",
      description = "Id do pagamento",
      example = "1",
      required = true
    ) Long pagamentoId
  );
}
