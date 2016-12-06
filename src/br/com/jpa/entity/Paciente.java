package br.com.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PACIENTE", catalog = "db_med")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CPF", unique = true, nullable = false, length = 45)
	private String cpf;

	@Column(name = "NOME", nullable = false, length = 45)
	private String nome;

	@Column(name = "TELEFONE", nullable = false, length = 20)
	private String telefone;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATANASC")
	private Date datanasc;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pacientes")
	private Set<Agenda> agendas = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	private Set<Procedimento> procedimentos = new LinkedHashSet<Procedimento>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	private Set<Matmed> matmeds = new LinkedHashSet<Matmed>();

	public Set<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Set<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Set<Matmed> getMatmeds() {
		return matmeds;
	}

	public void setMatmeds(Set<Matmed> matmeds) {
		this.matmeds = matmeds;
	}

	public Set<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

}
