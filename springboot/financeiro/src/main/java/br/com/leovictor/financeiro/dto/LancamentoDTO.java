package br.com.leovictor.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.leovictor.financeiro.domain.Lancamento;

public class LancamentoDTO {
	private Long id;
	private LocalDateTime data;
	private String descricao;
	private String categoria;

	public LancamentoDTO(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.data = lancamento.getData();
		this.descricao = lancamento.getDescricao();
		this.categoria = lancamento.getCategoria().getNome();
		this.valor = lancamento.getValor();
	}

	public LancamentoDTO(Long id, LocalDateTime data, String descricao, String categoria, BigDecimal valor) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.categoria = categoria;
		this.valor = valor;
	}

	public LancamentoDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	private BigDecimal valor;

	public static Page<LancamentoDTO> converter(Page<Lancamento> lista) {
		return lista != null ? lista.map(LancamentoDTO::new) : null;
	}
}
