package br.com.inovacao.abastecimento;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import br.com.inovacao.abastecimento.beans.Abastecimento;
import br.com.inovacao.abastecimento.util.Constants;
import br.com.inovacao.abastecimento.util.NFCForegroundUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class LerRFIDActivity extends Activity {

	private ProgressDialog pDialog;
	NFCForegroundUtil nfcForegroundUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ler_rfid);

		nfcForegroundUtil = new NFCForegroundUtil(this);

		pDialog = new ProgressDialog(LerRFIDActivity.this);
		pDialog.setMessage("Aguardando RFID...");
		pDialog.setCancelable(false);
		pDialog.show();

	}

	public void onPause() {
		super.onPause();
		// wakeLock.release();
		nfcForegroundUtil.disableForeground();
	}

	public void onResume() {
		super.onResume();
		nfcForegroundUtil.enableForeground();

		if (!nfcForegroundUtil.getNfc().isEnabled()) {
			Toast.makeText(
					getApplicationContext(),
					"Please activate NFC and press Back to return to the application!",
					Toast.LENGTH_LONG).show();
			startActivity(new Intent(
					android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}
	}

	public void onNewIntent(Intent intent) {
		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		StringBuilder id = new StringBuilder();
		for (int i = 0; i < tag.getId().length; i++) {
			id.append(new Integer(tag.getId()[i]));

		}

		pDialog.dismiss();
		if(id.toString().length() > 0){
			Abastecimento abastecimento = (Abastecimento) getIntent().getSerializableExtra((Constants.TAG_ABASTECIMENTO));				
			
			Intent in = new Intent(LerRFIDActivity.this, AbastecimentoActivity.class);
			in.putExtra(Constants.TAG_ABASTECIMENTO, abastecimento);
			in.putExtra(Constants.ESCOLHA, Constants.RFID);
			in.putExtra(Constants.NFC_TAG, id.toString());
			startActivity(in);
			
		}else{
			finish();
		}
	}

}
