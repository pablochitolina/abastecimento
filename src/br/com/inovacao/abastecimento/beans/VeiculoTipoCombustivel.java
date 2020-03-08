package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;


public class VeiculoTipoCombustivel implements  Serializable{
	private Double litros;
	private Double media;
	private String placa;
	private int tipoCombustivel;
	
	public VeiculoTipoCombustivel() {
		super();
	}

	public Double getLitros() {
		return litros;
	}

	public void setLitros(Double litros) {
		this.litros = litros;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(int tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	@Override
	public String toString() {
		return "VeiculoTipoCombustivel [litros=" + litros + ", media=" + media
				+ ", placa=" + placa + ", tipoCombustivel=" + tipoCombustivel
				+ "]";
	}

}
