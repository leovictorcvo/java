package br.com.leovictor.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leovictor.financeiro.domain.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	public Page<Lancamento> findByUsuarioId(Long id, Pageable paginacao);
}
