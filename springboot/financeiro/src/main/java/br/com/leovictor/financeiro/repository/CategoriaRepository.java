package br.com.leovictor.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leovictor.financeiro.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public Page<Categoria> findByNomeIgnoreCase(String nome, Pageable paginacao);
}
