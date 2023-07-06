package br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request;

import br.org.portalfadesp.servicopagamento.domain.pagamento.MetoPagamento;
import java.math.BigDecimal;

public class PagamentoRequest {

  private Integer codigoDebito;
  private String cpfCnpjPagador;
  private MetoPagamento metoPagamento;
  private String numeroCartao;
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

  public MetoPagamento getMetoPagamento() {
    return metoPagamento;
  }

  public void setMetoPagamento(MetoPagamento metoPagamento) {
    this.metoPagamento = metoPagamento;
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
