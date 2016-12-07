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
		// incluirAgenda(em);
		// listarPacientes(em);
		// buscarPaciente(em, "111.111.111-11");
		listaPacienteMatMed(em);
	}

	private static void listarPacientes(EntityManager em) {
		MedHelper dao = new MedHelper(em);
		List<Paciente> pacientes = dao.listarPacientes();
		for (Paciente paciente : pacientes) {
			System.out.println(paciente.getCpf() + ": " + paciente.getNome());
		}
		em.close();
	}

	private static void listaPacienteMatMed(EntityManager em) {
		MedHelper dao = new MedHelper(em);
		List<Paciente> pacientes = dao.listaPacienteMatMed();
		for (Paciente paciente : pacientes) {
			System.out.println(paciente.getCpf() + ": " + paciente.getNome() + paciente.getProcedimentos());
		}
		em.close();
	}

	private static void buscarPaciente(EntityManager em, String cpf) {
		MedHelper dao = new MedHelper(em);
		Paciente p = dao.buscarPaciente(cpf);
		System.out.println(p.getCpf() + ": " + p.getNome());
	}

	private static void incluirAgenda(EntityManager em) {

		MedHelper dao = new MedHelper(em);

		// adicionando nova agenda
		Agenda agenda = new Agenda();
		agenda.setData(new Date());
		agenda.setHora(new Date());
		agenda.setDescricao("Agenda 1");

		// adicionando novo paciente
		Paciente paciente = new Paciente();
		paciente.setCpf("111.111.111-11");
		// paciente.setDatanasc(new Date());
		paciente.setNome("Helena");
		paciente.setTelefone("1234-56789");

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

		paciente.getMatmeds().add(matmed);
		paciente.getProcedimentos().add(procedimento);

		try {
			dao.salvarAgenda(agenda);
			System.out.println("Incluído com sucesso");
		} catch (Exception e) {
			System.out.println("ERRO ===>> " + e.getMessage());
		}

	}

}
