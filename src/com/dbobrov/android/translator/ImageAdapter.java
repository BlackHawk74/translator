package com.dbobrov.android.translator;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    public static final int IMAGE_HEIGHT = 90;
    private ArrayList<Bitmap> bitmaps;
    private Context context;

    public ImageAdapter(Context context, ArrayList<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
        this.context = context;
    }

    public ArrayList<Bitmap> getContainer() {
        return bitmaps;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int i) {
        return bitmaps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void clear() {
        bitmaps.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ImageView v;
        if (convertView == null) {
            v = new ImageView(context);

            v.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, IMAGE_HEIGHT));
            v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            v = (ImageView) convertView;
        }
        v.setImageBitmap(bitmaps.get(position));
        return v;
    }
}