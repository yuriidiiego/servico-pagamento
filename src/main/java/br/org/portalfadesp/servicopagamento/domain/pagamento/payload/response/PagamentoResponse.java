package br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response;

import br.org.portalfadesp.servicopagamento.domain.pagamento.MetodoPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Resposta de um pagamento")
public class PagamentoResponse {

  @Schema(description = "ID do pagamento", example = "1")
  private Long id;

  @Schema(description = "Código do débito", example = "12345")
  private Integer codigoDebito;

  @Schema(description = "CPF/CNPJ do pagador", example = "12345678901")
  private String cpfCnpjPagador;

  @Schema(description = "Método de pagamento")
  private MetodoPagamento metodoPagamento;

  @Schema(description = "Valor do pagamento", example = "100.00")
  private BigDecimal valorPagamento;

  @Schema(description = "Status do pagamento", example = "PROCESSADO_COM_SUCESSO")
  private StatusPagamento statusPagamento;

  @Schema(description = "Data do pagamento", pattern = "dd/MM/yyyy HH:mm:ss", example = "01/01/2022 00:00:00")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime dataPagamento;

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
