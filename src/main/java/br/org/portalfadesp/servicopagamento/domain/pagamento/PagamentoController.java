package br.org.portalfadesp.servicopagamento.domain.pagamento;

import br.org.portalfadesp.servicopagamento.domain.pagamento.exception.ErrorResponse;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pagamentos", description = "API para operações de pagamentos")
public class PagamentoController {

  private final PagamentoService pagamentoService;

  public PagamentoController(PagamentoService pagamentoService) {
    this.pagamentoService = pagamentoService;
  }

  @PostMapping
  @Operation(
    summary = "Criar um novo pagamento",
    description = "Endpoint para receber o pagamento",
    operationId = "receberPagamento",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "Pagamento recebido com sucesso",
        content = @Content(
          schema = @Schema(implementation = PagamentoResponse.class)
        )
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Pagamento inválido",
        content = @Content(
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Pagamento não encontrado",
        content = @Content(
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
    }
  )
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
  @Operation(
    summary = "Atualizar o status de um pagamento",
    description = "Endpoint para atualizar o status de um pagamento",
    operationId = "atualizarStatusPagamento",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Pagamento atualizado com sucesso",
        content = @Content(
          schema = @Schema(implementation = PagamentoResponse.class)
        )
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Pagamento não encontrado",
        content = @Content(
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
      @ApiResponse(
        responseCode = "409",
        description = "Conflito no status de pagamento",
        content = @Content(
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
    }
  )
  public ResponseEntity<PagamentoResponse> atualizarStatusPagamento(
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
  ) {
    PagamentoResponse pagamentoResponse = pagamentoService.atualizarStatusPagamento(
      pagamentoId,
      novoStatus
    );
    return ResponseEntity.ok(pagamentoResponse);
  }

  @GetMapping
  @Operation(
    summary = "Listar pagamentos com filtros opcionais",
    description = "Endpoint para listar pagamentos",
    operationId = "listarPagamentos",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Lista de pagamentos",
        content = @Content(
          schema = @Schema(implementation = PagamentoResponse.class)
        )
      ),
    }
  )
  public ResponseEntity<List<PagamentoResponse>> listarPagamentos(
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
  ) {
    List<PagamentoResponse> pagamentos = pagamentoService.listarPagamentosComFiltros(
      codigoDebito,
      cpfCnpjPagador,
      statusPagamento
    );
    return ResponseEntity.ok(pagamentos);
  }

  @DeleteMapping("/{pagamentoId}")
  @Operation(
    summary = "Deletar um pagamento",
    description = "Endpoint para deletar um pagamento",
    operationId = "deletarPagamento",
    responses = {
      @ApiResponse(
        responseCode = "204",
        description = "Pagamento deletado com sucesso"
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Pagamento não encontrado",
        content = @Content(
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
    }
  )
  public ResponseEntity<Void> deletarPagamento(
    @PathVariable @Parameter(
      name = "pagamentoId",
      description = "Id do pagamento",
      example = "1",
      required = true
    ) Long pagamentoId
  ) {
    pagamentoService.deletarPagamento(pagamentoId);
    return ResponseEntity.noContent().build();
  }
}
