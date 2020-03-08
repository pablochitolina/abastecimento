package br.com.inovacao.abastecimento;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import br.com.inovacao.abastecimento.beans.Abastecimento;
import br.com.inovacao.abastecimento.beans.Combustivel;
import br.com.inovacao.abastecimento.beans.Condutor;
import br.com.inovacao.abastecimento.beans.Data;
import br.com.inovacao.abastecimento.beans.Empresa;
import br.com.inovacao.abastecimento.beans.Veiculo;
import br.com.inovacao.abastecimento.beans.VeiculoTipoCombustivel;
import br.com.inovacao.abastecimento.httptask.HttpTask;
import br.com.inovacao.abastecimento.util.Constants;

public class AbastecimentoActivity extends Activity {
	
	
	private Spinner spEmpresa, spCondutor, spVeiculo, spCombustivel;
	private EditText edtOdometro, edtQuantidade, edtValor;
	private Boolean spinnerValidoEmpresa = false;
	private Boolean spinnerValidoCondutor = false;
	private Boolean spinnerValidoVeiculo = false;
	private Boolean spinnerValidoCombustivel = false;
	private Button btnSalvar;
	private Double odometro = 0.0;
	private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimento);
        
        spEmpresa = (Spinner) findViewById(R.id.spEmpresa);
		spCondutor = (Spinner) findViewById(R.id.spCondutor);
		spVeiculo = (Spinner) findViewById(R.id.spVeiculo);
		spCombustivel = (Spinner) findViewById(R.id.spCombustivel);
		
    	edtOdometro = (EditText) findViewById(R.id.edtOdometro);
    	edtOdometro.setEnabled(false);
		edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
		edtQuantidade.setEnabled(false);
		edtValor = (EditText) findViewById(R.id.edtValor);
		edtValor.setEnabled(false);
		
		spCondutor.setEnabled(false);
    	spVeiculo.setEnabled(false);
    	spCombustivel.setEnabled(false);
    	
    	data = new Data();
        
        String escolha = getIntent().getStringExtra(Constants.ESCOLHA);
        
        if(escolha.equals(Constants.MANUAL)){
        	
        	 Abastecimento abastecimento = (Abastecimento) getIntent().getSerializableExtra((Constants.TAG_ABASTECIMENTO));
        	 
        	 setEmpresaManual(abastecimento);
        	 
        }else if (escolha.equals(Constants.RFID)){
        	
        	 Abastecimento abastecimento = (Abastecimento) getIntent().getSerializableExtra((Constants.TAG_ABASTECIMENTO));
        	 
        	 String id = getIntent().getStringExtra(Constants.NFC_TAG);
        	 
        	 setEmpresaCondutorAuto("298439872", abastecimento);
        
        }
        
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        
        btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(valido()){
					
					Gson gson = new Gson();
					String json = "data=" + gson.toJson(data);
					String url = Constants.URL_INSERIR_ABASTECIMENTO + json;
					Log.i("url", url);
					
					String retorno = "";
					try {
						retorno = new HttpTask().makeHttpRequest(URLEncoder.encode(url, HTTP.UTF_8), "POST", null);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Log.i("retorno", retorno);
					
				}else{
					Toast.makeText(AbastecimentoActivity.this, "Preencha corretamente os campos!", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
    }
    
    private void setEmpresaCondutorAuto(String id, Abastecimento abastecimento){
    	
    	ArrayList<Empresa> listEmpresa = new ArrayList<Empresa>();
    	ArrayList<Condutor> listCondutor = new ArrayList<Condutor>();
    	
    	data.setPosto(abastecimento.getListPosto().get(0).getIdPosto());
    	
    	for(Condutor c : abastecimento.getListCondutor()){
    		if(c.getMatricula().toString().equals(id)){
    			listCondutor.add(c);
    		}
    	}
    	
    	for(Condutor c : listCondutor){
    		for(Empresa e : abastecimento.getListEmpresa()){
    			if(c.getEmpresa() == e.getIdEmpresa()){
    				listEmpresa.add(e);
    			}
    		}
    	}
    	
    	ArrayAdapter<Empresa> dataAdapterEmpresas = new ArrayAdapter<Empresa>(this,android.R.layout.simple_spinner_item, listEmpresa);
    	dataAdapterEmpresas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spEmpresa.setAdapter(dataAdapterEmpresas);
    	spEmpresa.setEnabled(false);
    	data.setEmpresa(listEmpresa.get(0).getIdEmpresa());
    	
    	ArrayAdapter<Condutor> dataAdapterCondutor = new ArrayAdapter<Condutor>(this,android.R.layout.simple_spinner_item, listCondutor);
    	dataAdapterCondutor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spCondutor.setAdapter(dataAdapterCondutor);
    	spCondutor.setEnabled(false);
    	data.setCondutor(listCondutor.get(0).getIdCondutor());
    	
    	ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();
		listVeiculo.add(new Veiculo("Veiculo"));
		for(Veiculo v : abastecimento.getListVeiculo()){
			if(v.getCondutor() == listCondutor.get(0).getIdCondutor()){
				listVeiculo.add(v);
			}
		}
		setVeiculoManual(listVeiculo, abastecimento);
		spVeiculo.setEnabled(true);
    	
    	
    }
    
    private void setEmpresaManual(final Abastecimento abastecimento){
    	
    	data.setPosto(abastecimento.getListPosto().get(0).getIdPosto());
    	
    	ArrayList<Empresa> listEmpresa = abastecimento.getListEmpresa();
    	listEmpresa.add(0,new Empresa("Empresa"));
    	
    	ArrayAdapter<Empresa> dataAdapterEmpresas = new ArrayAdapter<Empresa>(this,android.R.layout.simple_spinner_item, listEmpresa);
    	dataAdapterEmpresas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spEmpresa.setAdapter(dataAdapterEmpresas);
    	
    	spEmpresa.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				zeraEditText();
				spCondutor.setAdapter(null);
				spVeiculo.setAdapter(null);
				spCombustivel.setAdapter(null);
				spCondutor.setEnabled(false);
				spVeiculo.setEnabled(false);
				spCombustivel.setEnabled(false);
				if(position != 0){
					spinnerValidoEmpresa = true;
					Empresa empresaSelecionada = (Empresa) parent.getItemAtPosition(position);
					data.setEmpresa(empresaSelecionada.getIdEmpresa());
					
					ArrayList<Condutor> listCondutor = new ArrayList<Condutor>();
					listCondutor.add(new Condutor("Condutor"));
					for(Condutor c : abastecimento.getListCondutor()){
						if(c.getEmpresa() == empresaSelecionada.getIdEmpresa()){
							listCondutor.add(c);
						}
					}
					setCondutorManual(listCondutor, abastecimento);
					spCondutor.setEnabled(true);
				}else{
					spinnerValidoEmpresa = false;
					spCondutor.setAdapter(null);
					spVeiculo.setAdapter(null);
					spCombustivel.setAdapter(null);
					spCondutor.setEnabled(false);
					spVeiculo.setEnabled(false);
					spCombustivel.setEnabled(false);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    }
    
    private void setCondutorManual(ArrayList<Condutor> listCondutor, final Abastecimento abastecimento){
    	
    	ArrayAdapter<Condutor> dataAdapterCondutor = new ArrayAdapter<Condutor>(this,android.R.layout.simple_spinner_item, listCondutor);
    	dataAdapterCondutor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spCondutor.setAdapter(dataAdapterCondutor);
    	
    	spCondutor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				zeraEditText();
				spVeiculo.setAdapter(null);
				spCombustivel.setAdapter(null);
				spVeiculo.setEnabled(false);
				spCombustivel.setEnabled(false);
				if(position != 0){
					spinnerValidoCondutor = true;
					Condutor condutorSelecionado = (Condutor) parent.getItemAtPosition(position);
					data.setCondutor(condutorSelecionado.getIdCondutor());
					
					ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();
					listVeiculo.add(new Veiculo("Veiculo"));
					for(Veiculo v : abastecimento.getListVeiculo()){
						if(v.getCondutor() == condutorSelecionado.getIdCondutor()){
							listVeiculo.add(v);
						}
					}
					setVeiculoManual(listVeiculo, abastecimento);
					spVeiculo.setEnabled(true);
				}else{
					spinnerValidoCondutor = false;
					spVeiculo.setAdapter(null);
					spCombustivel.setAdapter(null);
					spVeiculo.setEnabled(false);
					spCombustivel.setEnabled(false);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private void setVeiculoManual(ArrayList<Veiculo> listVeiculo, final Abastecimento abastecimento){
    	
    	ArrayAdapter<Veiculo> dataAdapterVeiculo = new ArrayAdapter<Veiculo>(this,android.R.layout.simple_spinner_item, listVeiculo);
    	dataAdapterVeiculo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spVeiculo.setAdapter(dataAdapterVeiculo);
    	
    	spVeiculo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				zeraEditText();
				spCombustivel.setAdapter(null);
				spCombustivel.setEnabled(false);
				if(position != 0){
					spinnerValidoVeiculo = true;
					Veiculo veiculoSelecionado = (Veiculo) parent.getItemAtPosition(position);
					
					data.setPlaca(veiculoSelecionado.getPlaca());
					
					ArrayList<Integer> listVeicCombustiveis = new ArrayList<Integer>();
					for(VeiculoTipoCombustivel vc : abastecimento.getListVeicCombustiveis()){
						if(veiculoSelecionado.getPlaca().equals(vc.getPlaca())){
							listVeicCombustiveis.add(vc.getTipoCombustivel());
						}
					}
					
					ArrayList<Combustivel> listCombustivel = new ArrayList<Combustivel>();
					listCombustivel.add(new Combustivel("Combustivel"));
					
					for(Integer vtc : listVeicCombustiveis){
						for(Combustivel c : abastecimento.getListCombustivel()){
							if(vtc == c.getIdTpCombustivel()){
								listCombustivel.add(c);
							}
						}
					}
					setCombustivelManual(listCombustivel, veiculoSelecionado);
					spCombustivel.setEnabled(true);
				}else{
					spinnerValidoVeiculo = false;
					spCombustivel.setAdapter(null);
					spCombustivel.setEnabled(false);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private void setCombustivelManual(ArrayList<Combustivel> listCombustivel, final Veiculo veiculoSelecionado){
    	
    	ArrayAdapter<Combustivel> dataAdapterCombustivel = new ArrayAdapter<Combustivel>(this,android.R.layout.simple_spinner_item, listCombustivel);
    	dataAdapterCombustivel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spCombustivel.setAdapter(dataAdapterCombustivel);
    	
    	spCombustivel.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				final Combustivel combustivelSelecionado = (Combustivel) parent.getItemAtPosition(position);
				data.setTpCombustivel(combustivelSelecionado.getIdTpCombustivel());
				data.setValor_comb(combustivelSelecionado.getValor());
				
				zeraEditText();
				if(position != 0){
					spinnerValidoCombustivel = true;
					edtOdometro.setEnabled(true);
					edtQuantidade.setEnabled(true);
					edtValor.setHint(combustivelSelecionado.getValor().toString());
					if(veiculoSelecionado.getOdometro()!= null){
						edtOdometro.setHint(veiculoSelecionado.getOdometro().toString());
						odometro = veiculoSelecionado.getOdometro();
					}
					
					
					edtQuantidade.setOnKeyListener(new OnKeyListener() {
						
						@Override
						public boolean onKey(View v, int keyCode, KeyEvent event) {
							if(edtQuantidade.getText().toString().length() > 0){
								Double valor = combustivelSelecionado.getValor() * Double.parseDouble(edtQuantidade.getText().toString());
								edtValor.setText("R$: " + valor );
								data.setValor(valor);
								
							}
							return false;
						}
					});
					
				}else{
					spinnerValidoCombustivel = false;
					zeraEditText();
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
    }
    
    private Boolean valido(){
    	
    	Boolean valido = true;
    	
    	if(!spinnerValidoEmpresa){
    		valido = false;
    	}
    	if(!spinnerValidoCondutor){
    		valido = false;
    	}
    	if(!spinnerValidoVeiculo){
    		valido = false;
    	}
    	if(!spinnerValidoCombustivel){
    		valido = false;
    	}
    	if(edtQuantidade.getText().toString().length() <= 0 || 
    			Double.parseDouble(edtQuantidade.getText().toString()) <= 0){
    		edtQuantidade.setText("");
    		edtQuantidade.setBackgroundColor(new Color().RED);
    		valido = false;
    	}else{
    		edtQuantidade.setBackgroundColor(new Color().WHITE);
    		valido = true;
    		data.setQuantidade(Double.parseDouble(edtQuantidade.getText().toString()));
    	}
    	
    	if(edtOdometro.getText().toString().length() <= 0 || 
    			Double.parseDouble(edtOdometro.getText().toString()) <= odometro){
    		edtOdometro.setText("");
    		edtOdometro.setBackgroundColor(new Color().RED);
    		valido = false;
    	}else{
    		edtOdometro.setBackgroundColor(new Color().WHITE);
    		valido = true;
    		data.setOdometro(Double.parseDouble(edtOdometro.getText().toString()));
    	}
    	
    	return valido;
    }
    
    private void zeraEditText(){
    	edtOdometro.setEnabled(false);
    	edtOdometro.setHint("Odometro");
		edtOdometro.setText("");
		edtQuantidade.setEnabled(false);
		edtQuantidade.setText("");
		edtValor.setText("");
		edtValor.setHint("Valor");
    }
}
