package br.com.leovictor.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.domain.Lancamento;
import br.com.leovictor.financeiro.domain.Usuario;

public class CriaLancamentoDTO {
	private LocalDateTime data;
	private String descricao;
	private Long categoriaId;
	private BigDecimal valor;

	public CriaLancamentoDTO(LocalDateTime data, String descricao, Long categoriaId, BigDecimal valor) {
		this.data = data;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.valor = valor;
	}

	public CriaLancamentoDTO() {

	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
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

	public Lancamento converter(Usuario usuario, Categoria categoria) {
		return new Lancamento(this.data, this.descricao, usuario, categoria, this.valor);
	}
}
