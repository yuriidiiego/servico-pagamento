package br.org.portalfadesp.servicopagamento.domain.pagamento;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer codigoDebito;
  private String cpfCnpjPagador;

  @Enumerated(EnumType.STRING)
  private MetoPagamento metoPagamento;

  private String numeroCartao;
  private BigDecimal valorPagamento;

  @Enumerated(EnumType.STRING)
  private StatusPagamento statusPagamento;

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

  public StatusPagamento getStatusPagamento() {
    return statusPagamento;
  }

  public void setStatusPagamento(StatusPagamento statusPagamento) {
    this.statusPagamento = statusPagamento;
  }
}
