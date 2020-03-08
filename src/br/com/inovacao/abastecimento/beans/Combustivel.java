package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.Collection;


public class Combustivel implements Serializable{
	private int idTpCombustivel;
	private String descricao;
	private Double valor;
	
	public Combustivel (String descricao){
		super();
		this.descricao = descricao;
	}
	
	public Combustivel() {
		super();
	}

	public int getIdTpCombustivel() {
		return idTpCombustivel;
	}

	public void setIdTpCombustivel(int idTpCombustivel) {
		this.idTpCombustivel = idTpCombustivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return descricao.toString();
	}
	
}
