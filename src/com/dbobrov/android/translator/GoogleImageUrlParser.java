package com.dbobrov.android.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GoogleImageUrlParser implements ImageUrlParser {
	@Override
	public String[] getImageUrls(String word) {
		try {
			URL url = new URL(
					"https://www.google.com/search?as_st=y&tbm=isch&as_q="
							+ word
							+ "&as_epq=&as_oq=&as_eq=&cr=&as_sitesearch=&safe=active&orq=&tbs=isz:m&biw=1304&bih=683&sei=n359UIuSG8jU4QTSvYHYBw");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			ArrayList<String> ans = new ArrayList<String>();
			int totalCount = 0;
			int firstMet;

			while ((line = reader.readLine()) != null && totalCount < 10) {
				int ind = 0;
				while ((firstMet = line.indexOf(
						"http://www.google.com/imgres?imgurl=", ind)) != -1
						&& totalCount < 10) {
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
