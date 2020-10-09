package br.com.leovictor.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.leovictor.jpa.modelo.Conta;

public class TesteAlteraConta {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = em.find(Conta.class, 1L);

		em.getTransaction().begin();
		conta.setSaldo(1000.5);
		em.getTransaction().commit();
	}

}
