package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.Collection;


public class Veiculo implements Serializable{
	private String placa;
	private Double odometro;
	private int condutor;
	private int empresa;
	
	public Veiculo(String placa) {
		super();
		this.placa = placa;
	}

	public Veiculo() {
		super();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getOdometro() {
		return odometro;
	}

	public void setOdometro(Double odometro) {
		this.odometro = odometro;
	}

	public int getCondutor() {
		return condutor;
	}

	public void setCondutor(int condutor) {
		this.condutor = condutor;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return placa.toString();
	}
	

}
