package br.com.inovacao.abastecimento.beans;

import java.io.Serializable;
import java.util.ArrayList;


public class Abastecimento implements Serializable {
	private int id_abastecimento;
	private String data_hora;
	private Double quantidade;
	private Double valor;
	private Double odometro;
	private Double horimetro;
	private Double consumo;
	private Double valor_comb;
	private Double latitude;
	private Double longitude;
	private ArrayList<Usuario> listUsuario;
	private ArrayList<Posto> listPosto;
	private ArrayList<Combustivel> listCombustivel;
	private ArrayList<Empresa> listEmpresa;
	private ArrayList<Condutor> listCondutor;
	private ArrayList<Veiculo> listVeiculo;
	private ArrayList<VeiculoTipoCombustivel> listVeicCombustiveis;
	
	
	public Abastecimento() {
		super();
	}

	public int getId_abastecimento() {
		return id_abastecimento;
	}

	public void setId_abastecimento(int id_abastecimento) {
		this.id_abastecimento = id_abastecimento;
	}

	public String getData_hora() {
		return data_hora;
	}

	public void setData_hora(String data_hora) {
		this.data_hora = data_hora;
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

	public Double getOdometro() {
		return odometro;
	}

	public void setOdometro(Double odometro) {
		this.odometro = odometro;
	}

	public Double getHorimetro() {
		return horimetro;
	}

	public void setHorimetro(Double horimetro) {
		this.horimetro = horimetro;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Double getValor_comb() {
		return valor_comb;
	}

	public void setValor_comb(Double valor_comb) {
		this.valor_comb = valor_comb;
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

	public ArrayList<Usuario> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(ArrayList<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public ArrayList<Posto> getListPosto() {
		return listPosto;
	}

	public void setListPosto(ArrayList<Posto> listPosto) {
		this.listPosto = listPosto;
	}

	public ArrayList<Combustivel> getListCombustivel() {
		return listCombustivel;
	}

	public void setListCombustivel(ArrayList<Combustivel> listCombustivel) {
		this.listCombustivel = listCombustivel;
	}

	public ArrayList<Empresa> getListEmpresa() {
		return listEmpresa;
	}

	public void setListEmpresa(ArrayList<Empresa> listEmpresa) {
		this.listEmpresa = listEmpresa;
	}

	public ArrayList<Condutor> getListCondutor() {
		return listCondutor;
	}

	public void setListCondutor(ArrayList<Condutor> listCondutor) {
		this.listCondutor = listCondutor;
	}

	public ArrayList<Veiculo> getListVeiculo() {
		return listVeiculo;
	}

	public void setListVeiculo(ArrayList<Veiculo> listVeiculo) {
		this.listVeiculo = listVeiculo;
	}

	public ArrayList<VeiculoTipoCombustivel> getListVeicCombustiveis() {
		return listVeicCombustiveis;
	}

	public void setListVeicCombustiveis(
			ArrayList<VeiculoTipoCombustivel> listVeicCombustiveis) {
		this.listVeicCombustiveis = listVeicCombustiveis;
	}

	@Override
	public String toString() {
		return "Abastecimento [id_abastecimento=" + id_abastecimento
				+ ", data_hora=" + data_hora + ", quantidade=" + quantidade
				+ ", valor=" + valor + ", odometro=" + odometro
				+ ", horimetro=" + horimetro + ", consumo=" + consumo
				+ ", valor_comb=" + valor_comb + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", listUsuario=" + listUsuario
				+ ", listPosto=" + listPosto + ", listCombustivel="
				+ listCombustivel + ", listEmpresa=" + listEmpresa
				+ ", listCondutor=" + listCondutor + ", listVeiculo="
				+ listVeiculo + ", listVeicCombustiveis="
				+ listVeicCombustiveis + "]";
	}

	
}
