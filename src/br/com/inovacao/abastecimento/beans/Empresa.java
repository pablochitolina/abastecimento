package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.Collection;

public class Empresa implements Serializable{
	private int idEmpresa;
	private String nome;
	private String fantasia;
	private String cpf_cnpj;
	
	public Empresa(String nome) {
		super();
		this.nome = nome;
	}

	public Empresa() {
		super();
	}
	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}


	@Override
	public String toString() {
		return nome.toString();
	}
	
}
