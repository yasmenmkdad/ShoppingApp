package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingapp.Adapter.ItemsAdapter;
import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ItemsListActivity extends AppCompatActivity {

    ListView listViewitems;
    String [] img;
    String[] names;
    int [] id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideTitlebar && BottomNavigationView
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_items_list);
        mylayout.bottomNavigate();

        String category = getIntent().getExtras().getString("category");
        OnlineShoppingDataset dataset = new OnlineShoppingDataset(this);
        Cursor cursor;
        try {
            cursor = dataset.fetchItemsbyCategory(category);
        }
        catch (SQLException e)
        {return;}

        img = null;
        names = null;
        id = null;

        listViewitems = findViewById(R.id.ListviewItems);
        if(cursor!=null) {

            img = new String[cursor.getCount()];
            names = new String[cursor.getCount()];
            id = new int[cursor.getCount()];
            int index = 0;

            while (!cursor.isAfterLast()) {

                id[index] = Integer.parseInt(cursor.getString(0));
                names[index] = cursor.getString(1);
                img[index] = cursor.getString(2);
                index++;
                cursor.moveToNext();
            }
            ItemsAdapter itemsAdapter = new ItemsAdapter(names,img,this,(LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE));
            listViewitems.setAdapter(itemsAdapter);
        }

        listViewitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idd) {

                Intent intent = new Intent(ItemsListActivity.this , ItemDetailsActivity.class);
                intent.putExtra("id" , id[position]);
                startActivity(intent);
            }
        });



    }


}