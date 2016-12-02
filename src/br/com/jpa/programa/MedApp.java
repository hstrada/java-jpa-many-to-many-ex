package br.com.jpa.programa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.jpa.entity.Agenda;
import br.com.jpa.entity.Matmed;
import br.com.jpa.entity.Paciente;
import br.com.jpa.entity.Procedimento;
import br.com.jpa.helper.MedHelper;

public class MedApp {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		// incluirPaciente(em);
		listarPacientes(em);
	}

	private static void listarPacientes(EntityManager em) {
		MedHelper dao = new MedHelper(em);
		List<Paciente> pacientes = dao.listarPacientes();
		for (Paciente paciente : pacientes) {
			System.out.println(paciente.getCpf() + ": " + paciente.getNome());
		}
		em.close();
	}
	
	private static void incluirPaciente(EntityManager em) {

		MedHelper dao = new MedHelper(em);

		// adicionando novo paciente
		Paciente paciente = new Paciente();
		paciente.setCpf("111.111.111-77");
		// paciente.setDatanasc(new Date());
		paciente.setNome("Helena");
		paciente.setTelefone("1234-56789");

		// adicionando nova agenda
		Agenda agenda = new Agenda();
		agenda.setData(new Date());
		agenda.setHora(new Date());
		agenda.setDescricao("Agenda 1");

		agenda.getPacientes().add(paciente);
		paciente.getAgendas().add(agenda);

		// adicionando novo procedimento
		Procedimento procedimento = new Procedimento();
		procedimento.setDescricao("Procedimento 1");
		procedimento.setPreco(100);
		procedimento.setPaciente(paciente);

		// adicionando novo matmed
		Matmed matmed = new Matmed();
		matmed.setDescricao("Matmed 1");
		matmed.setFabricante("Fabricante 1");
		matmed.setPaciente(paciente);
		matmed.setPreco(10);

		try {
			dao.salvarPaciente(paciente);
			dao.adicionarAgenda(agenda);
			dao.adicionarProcedimento(procedimento);
			dao.adicionarMatmed(matmed);
			System.out.println("Incluído com sucesso");
		} catch (Exception e) {
			System.out.println("ERRO ===>> " + e.getMessage());
		}

	}

}
