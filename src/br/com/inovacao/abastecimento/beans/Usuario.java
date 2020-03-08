package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.Collection;


public class Usuario implements Serializable{
	private int idUsuario;
	private String nome;
	private String email;
	private int posto;

	public Usuario() {
		super();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPosto() {
		return posto;
	}

	public void setPosto(int posto) {
		this.posto = posto;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome
				+ ", email=" + email + ", posto=" + posto + "]";
	}

}
