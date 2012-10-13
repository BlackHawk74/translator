package com.dbobrov.android.translator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText word;
    private Button submit;
    private TextView translation;
    private GridView imageGrid;
    private static final ImageUrlParser imageParser = ImageUrlParserFactory.getImageUrlParser();
    private static final WordTranslator wordTranslator = WordTranslatorFactory.getWordTranslator();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        word = (EditText) findViewById(R.id.fldWord);
        submit = (Button) findViewById(R.id.btnSubmit);
        translation = (TextView) findViewById(R.id.txtTranslation);
        imageGrid = (GridView) findViewById(R.id.grdImages);
        submit.setOnClickListener(this);
        imageGrid.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO implement
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO implement
    }
}
