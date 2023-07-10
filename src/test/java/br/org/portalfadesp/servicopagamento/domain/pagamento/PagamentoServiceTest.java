package br.org.portalfadesp.servicopagamento.domain.pagamento;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.org.portalfadesp.servicopagamento.domain.pagamento.enums.StatusPagamento;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.request.PagamentoRequest;
import br.org.portalfadesp.servicopagamento.domain.pagamento.payload.response.PagamentoResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

  @Mock
  private PagamentoRepository pagamentoRepository;

  @Mock
  private PagamentoMapper pagamentoMapper;

  @Mock
  private PagamentoValidator pagamentoValidator;

  @InjectMocks
  private PagamentoService pagamentoService;

  @Test
  void testReceberPagamento() {
    PagamentoRequest pagamentoRequest = new PagamentoRequest();
    Pagamento mappedPagamento = new Pagamento();
    Pagamento savedPagamento = new Pagamento();
    PagamentoResponse expectedResponse = new PagamentoResponse();

    when(pagamentoMapper.mapPagamentoRequestToEntity(pagamentoRequest))
      .thenReturn(mappedPagamento);
    when(pagamentoRepository.save(mappedPagamento)).thenReturn(savedPagamento);
    when(pagamentoMapper.mapPagamentoToResponse(savedPagamento))
      .thenReturn(expectedResponse);

    PagamentoResponse result = pagamentoService.receberPagamento(
      pagamentoRequest
    );

    Assertions.assertThat(result).isEqualTo(expectedResponse);

    verify(pagamentoValidator).validarPagamento(pagamentoRequest);
    verify(pagamentoMapper).mapPagamentoRequestToEntity(pagamentoRequest);
    verify(pagamentoRepository).save(mappedPagamento);
    verify(pagamentoMapper).mapPagamentoToResponse(savedPagamento);
  }

  @Test
  void testAtualizarStatusPagamento() {
    Long pagamentoId = 1L;
    StatusPagamento novoStatus = StatusPagamento.PROCESSADO_COM_SUCESSO;
    Pagamento foundPagamento = new Pagamento();
    Pagamento savedPagamento = new Pagamento();
    PagamentoResponse expectedResponse = new PagamentoResponse();

    when(pagamentoRepository.findById(pagamentoId))
      .thenReturn(Optional.of(foundPagamento));
    when(pagamentoRepository.save(foundPagamento)).thenReturn(savedPagamento);
    when(pagamentoMapper.mapPagamentoToResponse(savedPagamento))
      .thenReturn(expectedResponse);

    PagamentoResponse result = pagamentoService.atualizarStatusPagamento(
      pagamentoId,
      novoStatus
    );

    Assertions.assertThat(result).isEqualTo(expectedResponse);

    verify(pagamentoValidator)
      .validarAtualizacaoStatusPagamento(foundPagamento, novoStatus);
    verify(pagamentoRepository).save(foundPagamento);
    verify(pagamentoMapper).mapPagamentoToResponse(savedPagamento);
  }

  @Test
  void testListarPagamentos() {
    List<Pagamento> pagamentos = Arrays.asList(
      new Pagamento(),
      new Pagamento()
    );
    List<PagamentoResponse> expectedResponse = Arrays.asList(
      new PagamentoResponse(),
      new PagamentoResponse()
    );

    when(pagamentoRepository.findAll()).thenReturn(pagamentos);
    when(pagamentoMapper.mapToResponseList(pagamentos))
      .thenReturn(expectedResponse);

    List<PagamentoResponse> result = pagamentoService.listarPagamentos();

    Assertions.assertThat(result).isEqualTo(expectedResponse);

    verify(pagamentoRepository).findAll();
    verify(pagamentoMapper).mapToResponseList(pagamentos);
  }

  @Test
  void testListarPagamentosComFiltros() {
    Integer codigoDebito = 123;
    String cpfCnpjPagador = "123.456.789-00";
    StatusPagamento statusPagamento = StatusPagamento.PROCESSADO_COM_SUCESSO;
    List<Pagamento> pagamentos = Arrays.asList(
      new Pagamento(),
      new Pagamento()
    );
    List<PagamentoResponse> expectedResponse = Arrays.asList(
      new PagamentoResponse(),
      new PagamentoResponse()
    );

    when(
      pagamentoRepository.listarPagamentosComFiltrosOpcionais(
        codigoDebito,
        cpfCnpjPagador,
        statusPagamento
      )
    )
      .thenReturn(pagamentos);
    when(pagamentoMapper.mapToResponseList(pagamentos))
      .thenReturn(expectedResponse);

    List<PagamentoResponse> result = pagamentoService.listarPagamentosComFiltros(
      codigoDebito,
      cpfCnpjPagador,
      statusPagamento
    );

    Assertions.assertThat(result).isEqualTo(expectedResponse);

    verify(pagamentoRepository)
      .listarPagamentosComFiltrosOpcionais(
        codigoDebito,
        cpfCnpjPagador,
        statusPagamento
      );
    verify(pagamentoMapper).mapToResponseList(pagamentos);
  }

  @Test
  void testDeletarPagamento() {
    Long pagamentoId = 1L;
    Pagamento foundPagamento = new Pagamento();

    when(pagamentoRepository.findById(pagamentoId))
      .thenReturn(Optional.of(foundPagamento));

    pagamentoService.deletarPagamento(pagamentoId);

    verify(pagamentoValidator)
      .validarPagamentoPendenteProcessamento(foundPagamento);
    verify(pagamentoRepository).delete(foundPagamento);
  }
}
