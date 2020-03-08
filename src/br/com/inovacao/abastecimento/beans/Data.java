package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;

public class Data implements Serializable{

	private String placa;
	private int condutor;
	private int empresa;
	private int posto;
	private Double quantidade;
	private Double valor;
	private int tpCombustivel;
	private Double odometro;
	private Double valor_comb;
	
	public Data() {
		super();
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
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
	public int getPosto() {
		return posto;
	}
	public void setPosto(int posto) {
		this.posto = posto;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public int getTpCombustivel() {
		return tpCombustivel;
	}
	public void setTpCombustivel(int tpCombustivel) {
		this.tpCombustivel = tpCombustivel;
	}
	public Double getOdometro() {
		return odometro;
	}
	public void setOdometro(Double odometro) {
		this.odometro = odometro;
	}
	public Double getValor_comb() {
		return valor_comb;
	}
	public void setValor_comb(Double valor_comb) {
		this.valor_comb = valor_comb;
	}
	@Override
	public String toString() {
		return "Data [placa=" + placa + ", condutor=" + condutor + ", empresa="
				+ empresa + ", posto=" + posto + ", quantidade=" + quantidade
				+ ", valor=" + valor + ", tpCombustivel=" + tpCombustivel
				+ ", odometro=" + odometro + ", valor_comb=" + valor_comb + "]";
	}
	
}
