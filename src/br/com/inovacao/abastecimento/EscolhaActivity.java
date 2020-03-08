package br.com.inovacao.abastecimento;

import br.com.inovacao.abastecimento.beans.Abastecimento;
import br.com.inovacao.abastecimento.util.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EscolhaActivity extends Activity {

	Button btnManual, btnRFID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolha);
		
		btnManual = (Button) findViewById(R.id.btnManual);
		btnManual.setTextSize(30);
		btnRFID = (Button) findViewById(R.id.btnRFID);
		btnRFID.setTextSize(30);
		
		btnManual.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Abastecimento abastecimento = (Abastecimento) getIntent().getSerializableExtra((Constants.TAG_ABASTECIMENTO));				
				
				Intent in = new Intent(EscolhaActivity.this,AbastecimentoActivity.class);
				in.putExtra(Constants.TAG_ABASTECIMENTO, abastecimento);
				in.putExtra(Constants.ESCOLHA, Constants.MANUAL);
				startActivity(in);
				
			}
		});
		
		btnRFID.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Abastecimento abastecimento = (Abastecimento) getIntent().getSerializableExtra((Constants.TAG_ABASTECIMENTO));				
				
				Intent in = new Intent(EscolhaActivity.this,LerRFIDActivity.class);
				in.putExtra(Constants.TAG_ABASTECIMENTO, abastecimento);
				in.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(in);
				
			}
		});

	}
}
