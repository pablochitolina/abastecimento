package br.com.inovacao.abastecimento;

import br.com.inovacao.abastecimento.util.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText userEdtTxt;
	private EditText passEdtTxt;
	private CheckBox cbSalvar;

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Boolean voltou = (Boolean) getIntent().getBooleanExtra("back", false);
		if (voltou != null) {
			if (voltou) {
				Toast.makeText(LoginActivity.this,
						"Usuario e/ou senha invalidos!", Toast.LENGTH_LONG)
						.show();
			}
		}

		cbSalvar = (CheckBox) findViewById(R.id.cbSalvar);

		userEdtTxt = (EditText) findViewById(R.id.edtDtInicio);
		userEdtTxt.requestFocus();
		passEdtTxt = (EditText) findViewById(R.id.edtDtFim);

		sharedpreferences = getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);

		if (sharedpreferences.contains(Constants.CHECKED)) {
			if (sharedpreferences.getString(Constants.CHECKED, "").equals(
					"true")) {
				cbSalvar.setChecked(true);
				if (sharedpreferences.contains(Constants.NOME)) {
					userEdtTxt.setText(sharedpreferences.getString(Constants.NOME, ""));
				}
				if (sharedpreferences.contains(Constants.SENHA)) {
					passEdtTxt.setText(sharedpreferences.getString(Constants.SENHA, ""));
				}
			} else {
				cbSalvar.setChecked(false);
			}
		}

		Button btnlogin = (Button) findViewById(R.id.btnManual);

		btnlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (cbSalvar.isChecked()) {
					Editor editor = sharedpreferences.edit();
					editor.putString(Constants.NOME, userEdtTxt.getText().toString());
					editor.putString(Constants.SENHA, passEdtTxt.getText().toString());
					editor.putString(Constants.CHECKED, "true");
					editor.commit();
				} else {
					Editor editor = sharedpreferences.edit();
					editor.putString(Constants.NOME, "");
					editor.putString(Constants.SENHA, "");
					editor.putString(Constants.CHECKED, "false");
					editor.commit();
				}

				if (userEdtTxt.getText().length() <= 0) {
					Toast.makeText(LoginActivity.this, "Preencher usuario!",
							Toast.LENGTH_LONG).show();
					userEdtTxt.requestFocus();
				} else if (passEdtTxt.getText().length() <= 0) {
					Toast.makeText(LoginActivity.this, "Preencher senha!",
							Toast.LENGTH_LONG).show();
					passEdtTxt.requestFocus();
				} else {
					// TODO Auto-generated method stub
					Intent in = new Intent(v.getContext(),LoadingActivity.class);
					in.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					String user = userEdtTxt.getText().toString();
					String pw = passEdtTxt.getText().toString();
					String url = "user=" + user + "&pass=" + pw;
					in.putExtra(Constants.URL_LOGIN, url);
					startActivity(in);
				}
			}
		});

		Button btnCancel = (Button) findViewById(R.id.btnRFID);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				System.exit(0);
			}
		});
	}

}
