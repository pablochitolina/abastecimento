package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.Collection;


public class Posto implements Serializable{
	private int idPosto;
	private String descricao;
	private String endereco;
	private int cidade;
	private Double latitude;
	private Double longitude;
	
	
	public Posto() {
		super();
	}

	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	@Override
	public String toString() {
		return "Posto [idPosto=" + idPosto + ", descricao=" + descricao
				+ ", endereco=" + endereco + ", cidade=" + cidade
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
	
}
