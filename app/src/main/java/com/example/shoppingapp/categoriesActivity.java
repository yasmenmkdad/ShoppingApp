package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.shoppingapp.Adapter.CategoryAdapter;
import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

public class categoriesActivity extends AppCompatActivity {


    GridView GridView_categories;

    String[] names={"SuperMarket","Fashion","Beauty","Phone","Decor","Electronics","Baby care","Book","Pet Supplies"};
    int [] images={R.drawable.supermarket,R.drawable.fashion,R.drawable.beauty
            ,R.drawable.phone,R.drawable.decor,R.drawable.electronics
            ,R.drawable.baby,R.drawable.book,R.drawable.pet};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide Title bar & BottomNavigationView
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_categories);
        mylayout.bottomNavigate();
            GridView_categories = (GridView) findViewById(R.id.GridView_categories);
            CategoryAdapter adapter = new CategoryAdapter(names, images, this, (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE));
            GridView_categories.setAdapter(adapter);

            GridView_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(categoriesActivity.this, ItemsListActivity.class);
                    intent.putExtra("category", names[position]);
                    startActivity(intent);

                }
            });
    }

}