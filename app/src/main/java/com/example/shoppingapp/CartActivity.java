package com.example.shoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Adapter.CategoryAdapter;
import com.example.shoppingapp.Adapter.ItemsAdapter;
import com.example.shoppingapp.Adapter.cartAdapter;
import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

public class CartActivity extends AppCompatActivity {

    ListView listViewitems;
    String [] img;
    String[] names;
    int [] idItem;
    int [] idCart;
    int [] count;
    float [] price;
    float Totalprice = 0f;
    TextView txtTotalPrice;
    Button btnCheckout;

    private String sharedPrefFile = "checkboxPrefFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideTitlebar && BottomNavigationView
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_cart);
        mylayout.bottomNavigate();

        OnlineShoppingDataset dataset = new OnlineShoppingDataset(this);

        //Tocheck if Remember
        SharedPreferences preferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        String checkboxRemember = preferences.getString("remember","");
        String userNameEntered = preferences.getString("username","");
        if(checkboxRemember.equals("true") && !userNameEntered.equals(""))
        {
            dataset.login(userNameEntered);
        }

        if(dataset.getCurrentUsername().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Login First",Toast.LENGTH_LONG).show();
            //BottomNavigationView
            return;
        }
        txtTotalPrice = (TextView) findViewById(R.id.txt_cart_totalPrice);
        listViewitems = findViewById(R.id.ListViewCart);
        try {
            Cursor cursor = dataset.GetCartbyUsername(userNameEntered);
            if (cursor != null) {
                img = new String[cursor.getCount()];
                names = new String[cursor.getCount()];
                idItem = new int[cursor.getCount()];
                idCart = new int[cursor.getCount()];
                count = new int[cursor.getCount()];
                price = new float[cursor.getCount()];
                int index = 0;

                while (!cursor.isAfterLast()) {

                    idItem[index] = Integer.parseInt(cursor.getString(0));
                    count[index] = Integer.parseInt(cursor.getString(1));
                    idCart[index] = Integer.parseInt(cursor.getString(2));
                    Cursor cursoritemDetail = dataset.searchItemsbyId(idItem[index]);
                    if (cursoritemDetail != null) {
                        names[index] = cursoritemDetail.getString(0);
                        price[index] = Float.parseFloat(cursoritemDetail.getString(5));
                        img[index] = cursoritemDetail.getString(6);
                    }
                    Totalprice += price[index] * (float) count[index];
                    index++;
                    cursor.moveToNext();
                }

                cartAdapter cartAdap = new cartAdapter(names, img, count, userNameEntered, idItem, this, (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE));
                listViewitems.setAdapter(cartAdap);
            }

            txtTotalPrice.setText(String.valueOf(Totalprice));

        }
        catch (Exception e)
        {}
        btnCheckout = findViewById(R.id.button_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< idItem.length;i++)
                {
                    try
                    {
                        dataset.updateItemCountInstore(idCart[i], count[i]);
                        dataset.deleteItemIncart(idCart[i]);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),names[i] + " has amout more than avilable now",Toast.LENGTH_LONG).show();
                    }
                }
                Intent intent = new Intent(getApplicationContext() , CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }

}