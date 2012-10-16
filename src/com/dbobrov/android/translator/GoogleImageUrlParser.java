package com.dbobrov.android.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import android.util.Base64;

public class GoogleImageUrlParser implements ImageUrlParser {
	@Override
	public String[] getImageUrls(String word) {
		try {
			URL url = new URL(
					"https://www.google.ru/search?num=10&hl=ru&newwindow=1&safe=off&site=imghp&tbm=isch&source=hp&biw=1366&bih=624&q="
							+ word
							+ "#q="
							+ word
							+ "&num=10&hl=ru&newwindow=1&safe=off&site=imghp&tbm=isch&source=lnt&tbs=isz:i&sa=X&ei=YHF9UL-WNbGO4gSKs4H4DQ&ved=0CCMQpwUoAw&bav=on.2,or.r_gc.r_pw.r_qf.&fp=69f22befd53748ff&bpcl=35277026&biw=1366&bih=667");
			Properties systemSettings = System.getProperties();
			systemSettings.put("proxySet", "true");
			systemSettings.put("http.proxyHost", "1.1.1.14");
			systemSettings.put("http.proxyPort", "3128");

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			String encodedUserPwd = Base64.encodeToString(
					"Логин:пароль".getBytes(), 0);
			con.setRequestProperty("Proxy-Authorization", "Basic "
					+ encodedUserPwd);
			con.setRequestMethod("GET");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line;
			ArrayList<String> ans = new ArrayList<String>();
			int totalCount = 0;
			int firstMet;
//			line = reader.readLine();
//			PrintWriter out = new PrintWriter("/mnt/sdcard/text.txt");
//			out.println(line);

			while ((line = reader.readLine()) != null && totalCount < 10) {
				int ind = 0;
				while ((firstMet = line.indexOf(
						"http://www.google.com/imgres?imgurl=", ind)) != -1) {
					int closing = line.indexOf("&amp;imgrefurl", firstMet + 35);
					String partOfAns = line.substring(firstMet + 36, closing);
					ans.add(partOfAns);
					ind = closing;
					totalCount++;
				}

			}
			
			String[] realAns = new String[totalCount];
			ans.toArray(realAns);
			
			reader.close();
			if (totalCount == 0) {
				return null;
			} else {
				return realAns;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
