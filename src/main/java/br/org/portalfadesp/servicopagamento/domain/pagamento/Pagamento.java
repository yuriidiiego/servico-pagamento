package br.org.portalfadesp.servicopagamento.domain.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
  private MetodoPagamento metodoPagamento;

  private String numeroCartao;
  private BigDecimal valorPagamento;

  @Enumerated(EnumType.STRING)
  private StatusPagamento statusPagamento;

  private LocalDateTime dataPagamento;

  public Pagamento() {}

  public Pagamento(
    Integer codigoDebito,
    String cpfCnpjPagador,
    MetodoPagamento metodoPagamento,
    String numeroCartao,
    BigDecimal valorPagamento
  ) {
    this.codigoDebito = codigoDebito;
    this.cpfCnpjPagador = cpfCnpjPagador;
    this.metodoPagamento = metodoPagamento;
    this.numeroCartao = numeroCartao;
    this.valorPagamento = valorPagamento;
    this.statusPagamento = StatusPagamento.PENDENTE_DE_PROCESSAMENTO;
    this.dataPagamento = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public StatusPagamento getStatusPagamento() {
    return statusPagamento;
  }

  public void setStatusPagamento(StatusPagamento statusPagamento) {
    this.statusPagamento = statusPagamento;
  }

  public LocalDateTime getDataPagamento() {
    return dataPagamento;
  }

  public void setDataPagamento(LocalDateTime dataPagamento) {
    this.dataPagamento = dataPagamento;
  }
}
