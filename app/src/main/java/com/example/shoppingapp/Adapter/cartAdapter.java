package com.example.shoppingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingapp.CartActivity;
import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.R;

public class cartAdapter extends BaseAdapter {
    private String[] namesItem;
    private String[] imageItem;
    private int[] countItem;
    private int[] IDItem;
    private Context context;
    private LayoutInflater layoutInflater;

    String username;
    OnlineShoppingDataset dataset;

    float price = 0f;
    public cartAdapter(String[] Names, String[] photo, int[] count, String username,int[] idItem , Context c,LayoutInflater layoutInflater) {
        this.namesItem = Names;
        this.imageItem = photo;
        this.countItem = count;
        this.context = c;
        this.username = username;
        this.layoutInflater = layoutInflater;
        this.IDItem = idItem;
        dataset = new OnlineShoppingDataset(context);
    }


    @Override
    public int getCount() {
        return imageItem.length;
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
            convertView = layoutInflater.inflate(R.layout.item_cart, parent, false);
        }
        TextView txtphoto = convertView.findViewById(R.id.txtItemInCart);
        TextView count = convertView.findViewById(R.id.textCount_item_InCart);
        ImageView img = convertView.findViewById(R.id.imageitemIncart);

        Button btnadd = convertView.findViewById(R.id.buttonAdd1_countCart);
        Button btnsub = convertView.findViewById(R.id.buttonsub1_countCart);

        txtphoto.setText(namesItem[position]);
        count.setText(String.valueOf(countItem[position]));

        LoadImage loadImage = new LoadImage(img);
        loadImage.execute(imageItem[position]);
        //Action Button
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dataset.updateItemIncart(username, IDItem[position], 1);
                }
                catch (Exception e)
                {}


                Intent intent = new Intent(context, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        if(countItem[position]==0)
        {
            btnsub.setEnabled(false);
        }
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countItem[position]!=0)
                {
                    try {
                    dataset.updateItemIncart(username,IDItem[position],-1);
                }
                catch (Exception e)
                {}
                Intent intent = new Intent(context, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                }
            }
        });
        return convertView;
    }
}