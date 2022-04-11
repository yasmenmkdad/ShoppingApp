package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LoginActivity extends AppCompatActivity {


    OnlineShoppingDataset mydata=new OnlineShoppingDataset(this);
    private String sharedPrefFile = "checkboxPrefFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide Title bar & BottomNavigationView
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_login);
        mylayout.bottomNavigate();


        //Tocheck if profile
        SharedPreferences preferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        String userNameEntered = mydata.getCurrentUsername();
        if(!userNameEntered.equals(""))
        {
            Intent intent = new Intent(LoginActivity.this,profileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finishAffinity();
            startActivity(intent);
        }

        String checkboxRemember = preferences.getString("remember","");
        String usernameRemember = preferences.getString("username","");
        if(checkboxRemember.equals("true") && !usernameRemember.equals(""))
        {
            mydata.login(usernameRemember);

            Intent intent = new Intent(LoginActivity.this,profileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finishAffinity();
            startActivity(intent);
        }

        CheckBox remember=findViewById(R.id.checkBoxRememberMe);
        Button btnLOGIN = (Button) findViewById(R.id.button_Login);
        Button btnRegister = (Button) findViewById(R.id.button_Register);

        SharedPreferences.Editor Preferenceseditor = preferences.edit();

        EditText username=(EditText)findViewById(R.id.editTextText_login_username);
        EditText Password=(EditText)findViewById(R.id.editTextText_login_password);

        TextView errortxtusername = (TextView)findViewById(R.id.textViewErroeusername) ;
        TextView errortxtpassword = (TextView)findViewById(R.id.textViewErrorPassword) ;

        btnLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean checkStop=Boolean.FALSE;
                if ((username.getText().toString()).length() == 0) {
                    errortxtusername.setText("Please Enter Your username");
                    checkStop = Boolean.TRUE;
                } else if (! mydata.CheckUsernameEXISTS(username.getText().toString())) {
                    errortxtusername.setText("user name is wrong");
                    checkStop = Boolean.TRUE;
                }
                if ((Password.getText().toString()).length() < 4) {
                    errortxtpassword.setText("Please Enter Password at least 4");
                    checkStop = Boolean.TRUE;
                }
                if (checkStop)
                    return;

                // Check password
                try {
                    if(!mydata.Checkpassword(username.getText().toString(),Password.getText().toString()))
                    {
                        Password.setText("");
                        errortxtpassword.setText("Please Login again ");
                    }
                    else
                    {
                        mydata.login(username.getText().toString());
                        Preferenceseditor.putString("username",username.getText().toString());
                        Preferenceseditor.apply();
                        Intent intentApp =new Intent(LoginActivity.this,categoriesActivity.class);
                        intentApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finishAffinity();
                        startActivity(intentApp);

                    }
                }
                catch (Exception e)
                {
                    Password.setText("");
                    username.setText("");
                    errortxtpassword.setText("Please Login again ");
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    Preferenceseditor.putString("remember","true");
                    Preferenceseditor.apply();
                }
                else
                {
                    Preferenceseditor.putString("remember","false");
                    Preferenceseditor.apply();
                }
            }
        });
        TextView txtforget = (TextView) findViewById(R.id.textViewForgetPassword);
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean checkStop=Boolean.FALSE;
                if ((username.getText().toString()).length() == 0) {
                    errortxtusername.setText("Please Enter Your username");
                    checkStop = Boolean.TRUE;
                } else if (! mydata.CheckUsernameEXISTS(username.getText().toString())) {
                    errortxtusername.setText("user name is wrong");
                    checkStop = Boolean.TRUE;
                }
                if (checkStop)
                    return;

                Intent intent = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                intent.putExtra("username",username.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishAffinity();
                startActivity(intent);


            }
        });



    }

}