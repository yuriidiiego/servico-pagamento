package br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.MetodoPagamento;

@Schema(description = "Payload para criar um novo pagamento")
public class PagamentoRequest {

  @Schema(required = true, example = "12345", description = "Código do débito")
  @NotNull(message = "Código do débito é obrigatório")
  private Integer codigoDebito;

  @Schema(
    required = true,
    example = "12345678901",
    description = "CPF/CNPJ do pagador"
  )
  @NotBlank(message = "CPF/CNPJ do pagador é obrigatório")
  private String cpfCnpjPagador;

  @Schema(required = true, description = "Método de pagamento")
  @NotNull(message = "Método de pagamento é obrigatório")
  private MetodoPagamento metodoPagamento;

  @Schema(example = "1234567890123456", description = "Número do cartão")
  private String numeroCartao;

  @Schema(
    required = true,
    example = "100.00",
    description = "Valor do pagamento"
  )
  @NotNull(message = "Valor do pagamento é obrigatório")
  @DecimalMin(
    value = "0.0",
    inclusive = false,
    message = "Valor do pagamento deve ser maior que zero"
  )
  private BigDecimal valorPagamento;

  public Integer getCodigoDebito() {
    return codigoDebito;
  }

  public void setCodigoDebito(Integer codigoDebito) {
    this.codigoDebito = codigoDebito;
  }

  public String getCpfCnpjPagador() {
    return cpfCnpjPagador;
  }

  public void setCpfCnpjPagador(String cpfCnpjPagador) {
    this.cpfCnpjPagador = cpfCnpjPagador;
  }

  public MetodoPagamento getMetodoPagamento() {
    return metodoPagamento;
  }

  public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
    this.metodoPagamento = metodoPagamento;
  }

  public String getNumeroCartao() {
    return numeroCartao;
  }

  public void setNumeroCartao(String numeroCartao) {
    this.numeroCartao = numeroCartao;
  }

  public BigDecimal getValorPagamento() {
    return valorPagamento;
  }

  public void setValorPagamento(BigDecimal valorPagamento) {
    this.valorPagamento = valorPagamento;
  }
}
