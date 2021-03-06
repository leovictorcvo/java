package br.com.leovictor.jpa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteMovimentacao {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = em.find(Conta.class, 1L);

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal(100.33));
		movimentacao.setConta(conta);

		em.getTransaction().begin();
		em.persist(movimentacao);
		em.getTransaction().commit();

	}

}
