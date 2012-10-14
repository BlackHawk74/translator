package com.dbobrov.android.translator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class GoogleImageUrlParser implements ImageUrlParser {
	@Override
	public String[] getImageUrls(String word) {
		try {
			URL url = new URL(
					"https://www.google.ru/search?num=10&hl=ru&newwindow=1&safe=off&site=imghp&tbm=isch&source=hp&biw=1366&bih=667&q="
							+ word);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			String[] ans = new String[10];
			int totalCount = 0;

			while ((line = reader.readLine()) != null && totalCount < 10) {
				int ind = 0;
				int firstMet;
				while ((firstMet = line.indexOf("data-src=", ind)) != -1) {
					int closing = line.indexOf("\"", ind + 10);
					String partOfAns = line.substring(ind + 10, closing);
					ans[totalCount++] = partOfAns;
					ind = closing;
				}

			}
			reader.close();
			if (totalCount == 0) {
				return null;
			}
			if (totalCount < 10) {
				return Arrays.copyOfRange(ans, 0, totalCount);
			}
			if (totalCount == 10) {
				return ans;
			}
		} catch (MalformedURLException e) {
			// nothing
		} catch (IOException e) {
			// nothing
		}
		return null;

	}
}
