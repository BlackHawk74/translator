package com.dbobrov.android.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YandexWordTranslator implements WordTranslator {

	@Override
	public String GetTranslation(String word) {
		word = URLEncoder.encode(word);
		String baseUrl = "http://translate.yandex.net/tr.json/translate?lang=en-ru&text="
				+ word;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl)
					.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response = br.readLine();
			if (response != null) {
				return response.substring(1, response.length() - 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
