package com.dbobrov.android.translator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReserveImageUrlParserActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageUrlParser parser = ImageUrlParserFactory.getImageUrlParser();
		String[] imageUrls = parser.getImageUrls("kitten");
		TextView text = new TextView(this);
		if (imageUrls == null) {
			text.setText("nothing is found");
			setContentView(text);
		} else {
			String ans = imageUrls[0];
			for (int i = 1; i < imageUrls.length; i++) {
				ans += "\n" + imageUrls[i];
			}
			text.setText(ans);
			setContentView(text);
		}
	}

}
