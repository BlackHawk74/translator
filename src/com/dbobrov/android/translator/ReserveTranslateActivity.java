package com.dbobrov.android.translator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReserveTranslateActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WordTranslator translator = new YandexWordTranslator();
		String translated = translator.GetTranslation("cat");
		TextView text = new TextView(this);
		text.setText(translated);
		setContentView(text);
	}

}
