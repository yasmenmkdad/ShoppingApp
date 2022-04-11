package com.example.shoppingapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shoppingapp.CartActivity;
import com.example.shoppingapp.LoginActivity;
import com.example.shoppingapp.R;
import com.example.shoppingapp.Search;
import com.example.shoppingapp.categoriesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// Class for img in grid view
public class CategoryAdapter extends BaseAdapter {
    private String[] imageNames;
    private int[] imagePhoto;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(String[] Names, int[] photo, Context c,LayoutInflater layoutInflater) {
        this.imageNames = Names;
        this.imagePhoto = photo;
        this.context = c;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return imagePhoto.length;
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
            convertView = layoutInflater.inflate(R.layout.items_categories, parent, false);
        }
        TextView txtphoto = convertView.findViewById(R.id.txtcategory);
        ImageView img = convertView.findViewById(R.id.imageviewcategory);

        txtphoto.setText(imageNames[position]);
        img.setImageResource(imagePhoto[position]);


        return convertView;
    }


}