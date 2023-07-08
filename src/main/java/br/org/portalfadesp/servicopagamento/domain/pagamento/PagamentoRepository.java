package br.org.portalfadesp.servicopagamento.domain.pagamento;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
  @Query(
    "SELECT p FROM Pagamento p WHERE " +
    "(:codigoDebito IS NULL OR p.codigoDebito = :codigoDebito) AND " +
    "(:cpfCnpjPagador IS NULL OR p.cpfCnpjPagador = :cpfCnpjPagador) AND " +
    "(:statusPagamento IS NULL OR p.statusPagamento = :statusPagamento)"
  )
  List<Pagamento> listarPagamentosComFiltrosOpcionais(
    @Param("codigoDebito") Integer codigoDebito,
    @Param("cpfCnpjPagador") String cpfCnpjPagador,
    @Param("statusPagamento") StatusPagamento statusPagamento
  );
}
