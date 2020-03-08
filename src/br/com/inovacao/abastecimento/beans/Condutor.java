package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.Collection;

public class Condutor implements Serializable{
	private int idCondutor;
	private String nome;
	private String cnh;
	private String cpf;
	private String rg;
	private String telefone;
	private String celular;
	private String matricula;
	private int empresa;
	
	public Condutor(String nome) {
		super();
		this.nome = nome;
	}

	public Condutor() {
		super();
	}

	public int getIdCondutor() {
		return idCondutor;
	}

	public void setIdCondutor(int idCondutor) {
		this.idCondutor = idCondutor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return nome.toString();
	}
	
}
