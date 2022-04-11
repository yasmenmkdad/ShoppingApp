package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Adapter.ItemsAdapter;
import com.example.shoppingapp.Adapter.LoadImage;
import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

public class ItemDetailsActivity extends AppCompatActivity {
    Button Addcart;
    OnlineShoppingDataset dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideTitlebar && BottomNavigationView
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_item_details);
        mylayout.bottomNavigate();


        int iditem = getIntent().getIntExtra("id",0);

        Addcart = (Button) findViewById(R.id.button_item_AddtoCart);
        dataset = new OnlineShoppingDataset(this);
        try {

        Cursor cursor = dataset.searchItemsbyId(iditem);


        String name= cursor.getString(0);
        String sellername = cursor.getString(1);
        String desc = cursor.getString(2);
        String category = cursor.getString(3);
        int AvailableinStock = Integer.parseInt(cursor.getString(4));
        Float price = Float.parseFloat(cursor.getString(5));
        String imgpath = cursor.getString(6);

        LoadImage loadImage2 = new LoadImage(findViewById(R.id.imageitemDetail));
        loadImage2.execute(imgpath);

        ((TextView)findViewById(R.id.txtitemDetailName)).setText(name);
        ((TextView)findViewById(R.id.txtitemDetailDescription)).setText(desc);
        ((TextView)findViewById(R.id.txtitemDetailBrand)).setText("Brand : "+sellername);
        ((TextView)findViewById(R.id.txtitemDetailPrice)).setText("Price : "+price);
        if(AvailableinStock <= 0) {
            Addcart.setEnabled(false);
            Addcart.setText("No item in stock");
        }
        else
            Addcart.setText("Add To Cart");


        //Add To Cart
        Addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userNameEntered = dataset.getCurrentUsername();
                if(userNameEntered.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Login first",Toast.LENGTH_LONG).show();
                    return;
                }
                dataset.insertItemIncart(userNameEntered, iditem, 1);
                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
            }
        });


        }
        catch (Exception e)
         {
            Addcart.setEnabled(false);
            Addcart.setText("No item in stock");

             ((TextView)findViewById(R.id.txtitemDetailName)).setText("");
             ((TextView)findViewById(R.id.txtitemDetailDescription)).setText("");
             ((TextView)findViewById(R.id.txtitemDetailBrand)).setText("");
             ((TextView)findViewById(R.id.txtitemDetailPrice)).setText("");
         }


    }

}