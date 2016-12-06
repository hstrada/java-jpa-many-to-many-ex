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
	}
	
	@SuppressWarnings("unchecked")
	public Paciente buscarPaciente(String cpf) {
		Query query = em.createQuery("select p from Paciente p where cpf = :cpf");
		query.setParameter("cpf", cpf);
		Paciente p = (Paciente) query.getSingleResult();
		return p;
	}

	// JPQL: usando NamedQuery
	@SuppressWarnings("unchecked")
	public List<Paciente> listarPacientes() {
		Query query = em.createNamedQuery("Paciente.findAll");
		return query.getResultList();
	}

	/*
	 * * SELECT * FROM AGENDA A JOIN AGENDA_PACIENTE AP ON A.ID = AP.AGENDA_ID
	 * JOIN PACIENTE P ON AP.PACIENTE_CPF = P.CPF JOIN MATMED M ON M.CPFPAC =
	 * P.CPF JOIN PROCEDIMENTO PR ON PR.CPFPAC = P.CPF;
	 */

}
