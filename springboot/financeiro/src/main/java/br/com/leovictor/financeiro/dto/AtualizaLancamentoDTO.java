package br.com.leovictor.financeiro.dto;

import java.math.BigDecimal;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.domain.Lancamento;
import br.com.leovictor.financeiro.repository.LancamentoRepository;

public class AtualizaLancamentoDTO {
	private String descricao;
	private Long categoriaId;
	private BigDecimal valor;

	public AtualizaLancamentoDTO(String descricao, Long categoriaId, BigDecimal valor) {
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.valor = valor;
	}

	public AtualizaLancamentoDTO() {

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Lancamento converter(LancamentoRepository lancamentoRepository, Categoria categoria,
			Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
