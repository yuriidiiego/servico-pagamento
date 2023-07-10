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
    // Prepare test data
    PagamentoRequest pagamentoRequest = new PagamentoRequest();
    Pagamento mappedPagamento = new Pagamento();
    Pagamento savedPagamento = new Pagamento();
    PagamentoResponse expectedResponse = new PagamentoResponse();

    // Define the behavior of the mock objects
    when(pagamentoMapper.mapPagamentoRequestToEntity(pagamentoRequest))
      .thenReturn(mappedPagamento);
    when(pagamentoRepository.save(mappedPagamento)).thenReturn(savedPagamento);
    when(pagamentoMapper.mapPagamentoToResponse(savedPagamento))
      .thenReturn(expectedResponse);

    // Call the method under test
    PagamentoResponse result = pagamentoService.receberPagamento(
      pagamentoRequest
    );

    // Assert the result
    Assertions.assertThat(result).isEqualTo(expectedResponse);

    // Verify the behavior of the mock objects
    verify(pagamentoValidator).validarPagamento(pagamentoRequest);
    verify(pagamentoMapper).mapPagamentoRequestToEntity(pagamentoRequest);
    verify(pagamentoRepository).save(mappedPagamento);
    verify(pagamentoMapper).mapPagamentoToResponse(savedPagamento);
  }

  @Test
  void testAtualizarStatusPagamento() {
    // Prepare test data
    Long pagamentoId = 1L;
    StatusPagamento novoStatus = StatusPagamento.PROCESSADO_COM_SUCESSO;
    Pagamento foundPagamento = new Pagamento();
    Pagamento savedPagamento = new Pagamento();
    PagamentoResponse expectedResponse = new PagamentoResponse();

    // Define the behavior of the mock objects
    when(pagamentoRepository.findById(pagamentoId))
      .thenReturn(Optional.of(foundPagamento));
    when(pagamentoRepository.save(foundPagamento)).thenReturn(savedPagamento);
    when(pagamentoMapper.mapPagamentoToResponse(savedPagamento))
      .thenReturn(expectedResponse);

    // Call the method under test
    PagamentoResponse result = pagamentoService.atualizarStatusPagamento(
      pagamentoId,
      novoStatus
    );

    // Assert the result
    Assertions.assertThat(result).isEqualTo(expectedResponse);

    // Verify the behavior of the mock objects
    verify(pagamentoValidator)
      .validarAtualizacaoStatusPagamento(foundPagamento, novoStatus);
    verify(pagamentoRepository).save(foundPagamento);
    verify(pagamentoMapper).mapPagamentoToResponse(savedPagamento);
  }

  @Test
  void testListarPagamentos() {
    // Prepare test data
    List<Pagamento> pagamentos = Arrays.asList(
      new Pagamento(),
      new Pagamento()
    );
    List<PagamentoResponse> expectedResponse = Arrays.asList(
      new PagamentoResponse(),
      new PagamentoResponse()
    );

    // Define the behavior of the mock objects
    when(pagamentoRepository.findAll()).thenReturn(pagamentos);
    when(pagamentoMapper.mapToResponseList(pagamentos))
      .thenReturn(expectedResponse);

    // Call the method under test
    List<PagamentoResponse> result = pagamentoService.listarPagamentos();

    // Assert the result
    Assertions.assertThat(result).isEqualTo(expectedResponse);

    // Verify the behavior of the mock objects
    verify(pagamentoRepository).findAll();
    verify(pagamentoMapper).mapToResponseList(pagamentos);
  }

  @Test
  void testListarPagamentosComFiltros() {
    // Prepare test data
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

    // Define the behavior of the mock objects
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

    // Call the method under test
    List<PagamentoResponse> result = pagamentoService.listarPagamentosComFiltros(
      codigoDebito,
      cpfCnpjPagador,
      statusPagamento
    );

    // Assert the result
    Assertions.assertThat(result).isEqualTo(expectedResponse);

    // Verify the behavior of the mock objects
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
    // Prepare test data
    Long pagamentoId = 1L;
    Pagamento foundPagamento = new Pagamento();

    // Define the behavior of the mock objects
    when(pagamentoRepository.findById(pagamentoId))
      .thenReturn(Optional.of(foundPagamento));

    // Call the method under test
    pagamentoService.deletarPagamento(pagamentoId);

    // Verify the behavior of the mock objects
    verify(pagamentoValidator)
      .validarPagamentoPendenteProcessamento(foundPagamento);
    verify(pagamentoRepository).delete(foundPagamento);
  }
}
