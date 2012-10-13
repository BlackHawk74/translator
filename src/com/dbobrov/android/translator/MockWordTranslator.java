package com.dbobrov.android.translator;

public class MockWordTranslator implements WordTranslator {

	@Override
	public String GetTranslation(String word) {
		if (word != null && word.toLowerCase().equals("lazybones")) {
			return "лентяй";
		} else {
			return "Неизвестное слово";
		}
	}

}
