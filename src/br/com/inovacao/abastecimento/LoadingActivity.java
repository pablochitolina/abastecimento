package br.com.inovacao.abastecimento;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import br.com.inovacao.abastecimento.beans.Abastecimento;
import br.com.inovacao.abastecimento.beans.Combustivel;
import br.com.inovacao.abastecimento.beans.Condutor;
import br.com.inovacao.abastecimento.beans.Empresa;
import br.com.inovacao.abastecimento.beans.Posto;
import br.com.inovacao.abastecimento.beans.Usuario;
import br.com.inovacao.abastecimento.beans.Veiculo;
import br.com.inovacao.abastecimento.beans.VeiculoTipoCombustivel;
import br.com.inovacao.abastecimento.httptask.HttpTask;
import br.com.inovacao.abastecimento.util.Constants;


public class LoadingActivity extends Activity {

	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loanding);

		pDialog = new ProgressDialog(LoadingActivity.this);
		pDialog.setMessage("Carregando dados...");
		pDialog.setCancelable(false);
		pDialog.show();
		
		final String url = getIntent().getStringExtra(Constants.URL_LOGIN);

		Runnable r = new Runnable() {
			public void run() {

					
					Abastecimento abastecimento = getAbastecimento(Constants.URL_ABASTECIMENTO + url);
					
					if (abastecimento == null) {
						pDialog.dismiss();
						Intent in = new Intent(LoadingActivity.this,LoginActivity.class);
						in.putExtra("back", true);
						in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(in);
					} else {
						pDialog.dismiss();
						Intent in = new Intent(LoadingActivity.this,EscolhaActivity.class);
						in.putExtra(Constants.TAG_ABASTECIMENTO, abastecimento);
						startActivity(in);
				} 
			}

		};
		Thread t = new Thread(r);
		t.start();
	}
	
	private Abastecimento getAbastecimento(String url){
		
		ArrayList<Usuario> listUsuario = null;
		ArrayList<Posto> listPosto = null;
		ArrayList<Combustivel> listCombustiveis = null;
		ArrayList<Empresa> listEmpresas = null;
		ArrayList<Condutor> listCondutores = null;
		ArrayList<Veiculo> listVeiculos = null;
		ArrayList<VeiculoTipoCombustivel> listVeicCombustiveis = null;
		Abastecimento abastecimento = null;
		
		String retornoJson = getJSON(url,"GET", null);
		
		try {
        	
        	//Gson g = null;
        	JSONObject JSONObject = new JSONObject(retornoJson);
        	
        	//USUARIO
        	listUsuario = new ArrayList<Usuario>();
			JSONArray response = JSONObject.getJSONArray("Usuarios");
			for (int i = 0; i < response.length(); i++) {
				JSONObject JSONItem = response.getJSONObject(i);
				//g = new Gson();
				//Usuario object = (Usuario) g.fromJson(JSONItem.toString(),Usuario.class);
				Usuario object = new Usuario();
				object.setNome(JSONItem.getString("nome"));
				object.setIdUsuario(JSONItem.getInt("idUsuario"));
				object.setPosto(JSONItem.getInt("posto"));
				listUsuario.add(object);
				Log.i("Usuarios", object.toString());
			}
			
			//POSTO
        	listPosto = new ArrayList<Posto>();
			response = JSONObject.getJSONArray("Posto");
			for (int i = 0; i < response.length(); i++) {
				JSONObject JSONItem = response.getJSONObject(i);
				//g = new Gson();
				//Posto object = (Posto) g.fromJson(JSONItem.toString(),Posto.class);
				Posto object = new Posto();
				object.setDescricao(JSONItem.getString("descricao"));
				object.setIdPosto(JSONItem.getInt("idPosto"));
				listPosto.add(object);
				Log.i("Posto", object.toString());
			}
			
			//COMBUSTIVEL
        	listCombustiveis = new ArrayList<Combustivel>();
        	//listCombustiveis.add(new Combustivel("Combust�vel"));
			response = JSONObject.getJSONArray("Combustiveis");
			for (int i = 0; i < response.length(); i++) {
				
				JSONObject JSONItem = response.getJSONObject(i);
				JSONObject objectConbustivel = JSONItem.getJSONObject("Combustiveis");
				//g = new Gson();
				//Combustivel object = (Combustivel) g.fromJson(objectConbustivel.toString(),Combustivel.class);
				Combustivel object = new Combustivel();
				object.setDescricao(objectConbustivel.getString("descricao"));
				object.setIdTpCombustivel(objectConbustivel.getInt("idTpCombustivel"));
				object.setValor(objectConbustivel.getDouble("valor"));
				listCombustiveis.add(object);
				Log.i("Combustiveis", object.toString());
			}
			
			//EMPRESA
        	listEmpresas = new ArrayList<Empresa>();
        	//listEmpresas.add(new Empresa("Empresa"));
			response = JSONObject.getJSONArray("Empresas");
			for (int i = 0; i < response.length(); i++) {
				JSONObject JSONItem = response.getJSONObject(i);
				//g = new Gson();
				//Empresa object = (Empresa) g.fromJson(JSONItem.toString(),Empresa.class);
				Empresa object = new Empresa();
				object.setNome(JSONItem.getString("nome"));
				object.setIdEmpresa(JSONItem.getInt("idEmpresa"));
				listEmpresas.add(object);
				Log.i("Empresas", object.toString());
			}
			
			//CONDUTOR
        	listCondutores = new ArrayList<Condutor>();
        	//listCondutores.add(new Condutor("Condutor"));
			response = JSONObject.getJSONArray("Condutores");
			for (int i = 0; i < response.length(); i++) {
				JSONObject JSONItem = response.getJSONObject(i);
				//g = new Gson();
				//Condutor object = (Condutor) g.fromJson(JSONItem.toString(),Condutor.class);
				Condutor object = new Condutor();
				object.setNome(JSONItem.getString("nome"));
				object.setMatricula(JSONItem.getString("matricula"));
				object.setIdCondutor(JSONItem.getInt("idCondutor"));
				object.setEmpresa(JSONItem.getInt("empresa"));
				listCondutores.add(object);
				Log.i("Condutores", object.toString());
			}
			
			//VEICULO
        	listVeiculos = new ArrayList<Veiculo>();
        	//listVeiculos.add(new Veiculo("Ve�culo"));
			response = JSONObject.getJSONArray("Veiculos");
			for (int i = 0; i < response.length(); i++) {
				JSONObject JSONItem = response.getJSONObject(i);
				//g = new Gson();
				//Veiculo object = (Veiculo) g.fromJson(JSONItem.toString(),Veiculo.class);
				Veiculo object = new Veiculo();
				object.setPlaca(JSONItem.getString("placa"));
				object.setOdometro(JSONItem.getDouble("odometro"));
				object.setEmpresa(JSONItem.getInt("empresa"));
				object.setCondutor(JSONItem.getInt("condutor"));
				listVeiculos.add(object);
				Log.i("Veiculos", object.toString());
			}
			
			//VEICULOTIPOCOMBUSTIVEL
        	listVeicCombustiveis = new ArrayList<VeiculoTipoCombustivel>();
			response = JSONObject.getJSONArray("VeicCombustiveis");
			for (int i = 0; i < response.length(); i++) {
				JSONObject JSONItem = response.getJSONObject(i);
				//g = new Gson();
				//VeiculoTipoCombustivel object = (VeiculoTipoCombustivel) g.fromJson(JSONItem.toString(),VeiculoTipoCombustivel.class);
				VeiculoTipoCombustivel object = new VeiculoTipoCombustivel();
				object.setLitros(JSONItem.getDouble("litros"));
				object.setMedia(JSONItem.getDouble("media"));
				object.setTipoCombustivel(JSONItem.getInt("tipoCombustivel"));
				object.setPlaca(JSONItem.getString("placa"));
				listVeicCombustiveis.add(object);
				Log.i("VeicCombustiveis", object.toString());
			}
			
			abastecimento = new Abastecimento();
			abastecimento.setListUsuario(listUsuario);
			abastecimento.setListPosto(listPosto);
			abastecimento.setListCombustivel(listCombustiveis);
			abastecimento.setListEmpresa(listEmpresas);
			abastecimento.setListCondutor(listCondutores);
			abastecimento.setListVeiculo(listVeiculos);
			abastecimento.setListVeicCombustiveis(listVeicCombustiveis);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return abastecimento;
	}

	private String getJSON(String url, String method, String params) {

		return new HttpTask().makeHttpRequest(url, method, params);

	}

}
