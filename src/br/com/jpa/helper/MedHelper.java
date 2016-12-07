package br.com.jpa.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

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

	@SuppressWarnings("unchecked")
	public List<Paciente> listaPacienteMatMed() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Paciente> p = cb.createQuery(Paciente.class);
		Root<Procedimento> pr = p.from(Procedimento.class);
		Join<Procedimento, Paciente> phones = pr.join("paciente");
		p.select(phones);

		List<Paciente> results = em.createQuery(p).getResultList();

		return results;
	}

	/*
	 * * SELECT * FROM AGENDA A JOIN AGENDA_PACIENTE AP ON A.ID = AP.AGENDA_ID
	 * JOIN PACIENTE P ON AP.PACIENTE_CPF = P.CPF JOIN MATMED M ON M.CPFPAC =
	 * P.CPF JOIN PROCEDIMENTO PR ON PR.CPFPAC = P.CPF;
	 */

}
