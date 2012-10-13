package com.dbobrov.android.translator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText word;
    private Button submit;
    private TextView translation;
    private GridView imageGrid;


    private ImageAdapter adapter;
    private ImageView largeImage;
    private static final ImageUrlParser imageParser = ImageUrlParserFactory.getImageUrlParser();
    private static final WordTranslator wordTranslator = WordTranslatorFactory.getWordTranslator();


    private class NetworkLayer extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            String translation = wordTranslator.GetTranslation(strings[0]);
            if (translation == null) {
                translation = getString(R.string.error_translating);
            }
            publishProgress("t" + translation);
            String[] imageUrls = imageParser.getImageUrls(strings[0]);
            ArrayList<Bitmap> bitmaps = adapter.getContainer();
            if (imageUrls == null) {
                bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.no_photo));
                publishProgress("i");
            } else {
                for (int i = 0; i < imageUrls.length && !isCancelled(); ++i) {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL(imageUrls[i]).openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        bitmaps.add(bitmap);

                    } catch (IOException e) {
                        Log.e("DOWNLOADING", "Can't download image");
                    }
                    publishProgress("i");
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            if (progress[0].charAt(0) == 't') {
                translation.setText(progress[0].substring(1));
            } else {
                adapter.notifyDataSetChanged();
            }
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                Toast.makeText(MainActivity.this, R.string.smth_wrong, Toast.LENGTH_SHORT).show();
            }
        }
    }


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
        adapter = new ImageAdapter(this, new ArrayList<Bitmap>());
        imageGrid.setAdapter(adapter);
        largeImage = (ImageView) findViewById(R.id.imgLarge);
        largeImage.setOnClickListener(this);
        largeImage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                findViewById(R.id.tv1).setVisibility(View.VISIBLE);
                findViewById(R.id.tv2).setVisibility(View.VISIBLE);
                String text = word.getText().toString();
                text = text.trim();
                if (!text.matches("^[\\w\\s]+$")) {
                    Toast.makeText(this, R.string.enter_word, Toast.LENGTH_SHORT).show();
                } else {
                    adapter.clear();
                    new NetworkLayer().execute(text);
                }
                break;
            case R.id.imgLarge:
                view.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        switch (parent.getId()) {
            case R.id.grdImages:
                largeImage.setImageBitmap((Bitmap) imageGrid.getAdapter().getItem(position));
                largeImage.setVisibility(View.VISIBLE);
                break;

        }

    }
}
