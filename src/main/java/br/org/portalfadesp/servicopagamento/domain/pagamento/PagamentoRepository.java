package br.org.portalfadesp.servicopagamento.domain.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository
  extends JpaRepository<Pagamento, Integer> {}
