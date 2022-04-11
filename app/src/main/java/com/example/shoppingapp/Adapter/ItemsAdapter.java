package com.example.shoppingapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ItemsAdapter extends BaseAdapter {

    private String[] namesItem;
    private String[] imageItem;
    private Context context;
    private LayoutInflater layoutInflater;
    TextView txtphoto;
    ImageView img;
    public ItemsAdapter(String[] Names, String[] photo, Context c,LayoutInflater layoutInflater) {
        this.namesItem = Names;
        this.imageItem = photo;
        this.context = c;
        this.layoutInflater = layoutInflater;
    }
    @Override
    public int getCount() {
        return namesItem.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_show, parent, false);
        }
        txtphoto = convertView.findViewById(R.id.txtitemDetailName);
        img = convertView.findViewById(R.id.imageitemDetail);
        txtphoto.setText(namesItem[position]);

        LoadImage loadImage = new LoadImage(img);
        loadImage.execute(imageItem[position]);

        //img.setImageBitmap(imageItem[position]);

        return convertView;
    }
}
