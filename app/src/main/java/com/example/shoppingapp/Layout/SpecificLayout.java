package com.example.shoppingapp.Layout;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.CartActivity;
import com.example.shoppingapp.LoginActivity;
import com.example.shoppingapp.barCodeActivity;
import com.example.shoppingapp.R;
import com.example.shoppingapp.Search;
import com.example.shoppingapp.categoriesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SpecificLayout {

    public Activity activity;
    public SpecificLayout(Activity CurrentActivity)
    {
        this.activity = CurrentActivity;
    }

    public void hideTitlebar ()
    {
        //hide Title bar
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((AppCompatActivity)activity).getSupportActionBar().hide();

    }
    public void bottomNavigate()
    {
        //BottomNavigationView
        BottomNavigationView bottomNavigationView = this.activity.findViewById(R.id.BottomNavigate);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent=null;
                switch (item.getItemId()){
                    case R.id.action_search:
                        intent=new Intent(activity.getApplicationContext(), Search.class);
                        break;
                    case R.id.action_QR:
                        intent=new Intent(activity.getApplicationContext(),barCodeActivity.class);
                        break;
                    case R.id.action_login:
                        intent=new Intent(activity.getApplicationContext(), LoginActivity.class);
                        break;
                    case R.id.cart:
                        intent=new Intent(activity.getApplicationContext(), CartActivity.class);
                        break;
                    case R.id.home:
                        intent=new Intent(activity.getApplicationContext(), categoriesActivity.class);
                        break;
                }
                if(intent!=null)
                    activity.startActivity(intent);
                return false;
            }
        });
    }
}
