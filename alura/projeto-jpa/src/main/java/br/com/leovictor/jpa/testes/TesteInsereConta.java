package br.com.leovictor.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.leovictor.jpa.modelo.Conta;

public class TesteInsereConta {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setTitular("Leonardo");
		conta.setAgencia(103);
		conta.setNumero(1234);
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
	}

}
