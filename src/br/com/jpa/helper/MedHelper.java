package br.com.jpa.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jpa.entity.Agenda;
import br.com.jpa.entity.Matmed;
import br.com.jpa.entity.Paciente;
import br.com.jpa.entity.Procedimento;

public class MedHelper {

	private EntityManager em;

	public MedHelper(EntityManager em) {
		this.em = em;
	}

	public void salvarAgenda(Agenda agenda) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(agenda);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}// JPQL: Usando Query

	@SuppressWarnings("unchecked")
	public List<Paciente> listarPacientes() {
		Query query = em.createQuery("select p from Paciente p");
		return query.getResultList();
	}

}
