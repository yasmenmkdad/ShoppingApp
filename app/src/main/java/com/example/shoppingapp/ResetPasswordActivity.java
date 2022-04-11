package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText txtemail , txtpassword;
    Button btnReset , btnReset2;
    OnlineShoppingDataset dataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide Title bar
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_reset_password);

        dataset = new OnlineShoppingDataset(this);

        String username = getIntent().getExtras().getString("username");

        txtemail = (EditText) findViewById(R.id.editTextText_ResetPassword_Email);
        btnReset = (Button) findViewById(R.id.button_ResetPassword);

        txtpassword = (EditText) findViewById(R.id.editTextText_ResetPassword_NewPassword);
        btnReset2 = (Button) findViewById(R.id.button_ResetPassword2);

        txtpassword.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.textView11)).setVisibility(View.GONE);
        btnReset2.setVisibility(View.GONE);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dataset.CheckUserEmail(username,txtemail.getText().toString())) {
                    txtemail.setEnabled(false);
                    ((TextView)findViewById(R.id.textView11)).setVisibility(View.VISIBLE);
                    txtpassword.setVisibility(View.VISIBLE);
                    btnReset.setVisibility(View.GONE);
                    btnReset2.setVisibility(View.VISIBLE);
                }
            }
        });

        btnReset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((txtpassword.getText().toString()).length()<4){
                    Toast.makeText(getApplicationContext(),"Please Enter Password at least 4",Toast.LENGTH_LONG).show();
                    return;
                }

                dataset.updateUserPassword(username,txtpassword.getText().toString());

                Intent intentApp =new Intent(ResetPasswordActivity.this,LoginActivity.class);
                intentApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishAffinity();
                startActivity(intentApp);

            }
        });
    }


}