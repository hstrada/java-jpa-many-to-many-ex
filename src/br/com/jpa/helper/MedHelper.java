package br.com.jpa.helper;

import javax.persistence.EntityManager;

import br.com.jpa.entity.Paciente;

public class MedHelper {

	private EntityManager em;

	public MedHelper(EntityManager em) {
		this.em = em;
	}

	public void salvar(Paciente paciente) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(paciente);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

}
