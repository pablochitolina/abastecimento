package br.com.inovacao.abastecimento.httptask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class HttpTask {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	List<NameValuePair> nvp = null;

	public HttpTask() {

	}

	public String makeHttpRequest(String url, String method, String params) {
		BackGroundTask Task = new BackGroundTask(url, method, params);
		try {
			return Task.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public class BackGroundTask extends AsyncTask<String, String, String> {
		String postparams = "";
		String URL = "";
		String method = "";

		public BackGroundTask(String url, String method, String params) {
			URL = url;
			postparams = params;
			this.method = method;
		}

		@Override
		protected String doInBackground(String... params) {
			String retornoJson = "";
			try {
				if (method.equals("POST")) {
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(URL);
					httpGet.setHeader("host", "http://www.gestordefrotas.com.br");
					httpGet.setHeader("Content-type",
							"application/json");
					HttpResponse response = httpClient.execute(httpGet);

					retornoJson = inpuStreamToString(response.getEntity()
							.getContent());

				} else if (method.equals("GET")) {
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(URL);
					HttpResponse response = httpClient.execute(httpGet);

					retornoJson = inpuStreamToString(response.getEntity()
							.getContent());
					//Log.i("Retorno GET: ", retornoJson);

				} else if (method.equals("PUT")) {
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPut httpPut = new HttpPut(URL);
					httpPut.setHeader("Accept", "application/json");
					httpPut.setHeader("Content-type",
							"application/json");
					httpPut.setEntity(new StringEntity(postparams.toString()));
					HttpResponse httpResponse = httpClient.execute(httpPut);

					retornoJson = inpuStreamToString(httpResponse.getEntity()
							.getContent());
					//Log.i("Retorno PUT: ", retornoJson);

				} else if (method.equals("DELETE")) {
					//TODO arrumar
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpDelete httpDelete = new HttpDelete(URL);
					HttpResponse httpResponse = httpClient.execute(httpDelete);

					retornoJson = inpuStreamToString(httpResponse.getEntity()
							.getContent());
					//Log.i("Retorno DELETE: ", retornoJson);
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// return JSON String
			return retornoJson;

		}

		private String inpuStreamToString(InputStream content) {
			String linha = "";
			StringBuilder total = new StringBuilder();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						content), 2000);
				while ((linha = br.readLine()) != null) {
					total.append(linha);
				}
			} catch (Exception e) {
				return "Erro: " + e.getMessage();
			}
			return total.toString();
		}
	}
}