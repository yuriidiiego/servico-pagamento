package br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request;

import br.org.portalfadesp.servicopagamento.domain.pagamento.MetodoPagamento;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoRequest {

  @NotNull(message = "Código do débito é obrigatório")
  private Integer codigoDebito;

  @NotBlank(message = "CPF/CNPJ do pagador é obrigatório")
  private String cpfCnpjPagador;

  @NotNull(message = "Método de pagamento é obrigatório")
  private MetodoPagamento metodoPagamento;

  private String numeroCartao;

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
