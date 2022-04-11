package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    String date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide Title bar
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();

        setContentView(R.layout.activity_register);


        //Register Calender Birthday
        EditText txtBirthday=(EditText)findViewById(R.id.editTextDateBirthday);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month=month+1;
                                date = year + "-" + month + "-" + dayOfMonth;
                                txtBirthday.setText(date);
                            }
                        }
                        , year, month, day);
                datePickerDialog.show();
            }
        });

        OnlineShoppingDataset mydata=new OnlineShoppingDataset(this);

        Button btnDone=(Button)findViewById(R.id.button_Register_Done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtusername = (EditText)findViewById(R.id.editText_Register_username);
                EditText txtpassword = (EditText)findViewById(R.id.editText_Register_password);
                EditText txtphone = (EditText)findViewById(R.id.editText_Register_phone);
                EditText txtemail = (EditText)findViewById(R.id.editText_Register_email);

                TextView errortxtusername = (TextView)findViewById(R.id.textViewError_Register_username) ;
                TextView errortxtpassword = (TextView)findViewById(R.id.textViewError_Register_password) ;
                TextView errortxtphone = (TextView)findViewById(R.id.textViewError_Register_phone) ;
                TextView errortxtemail = (TextView)findViewById(R.id.textViewError_Register_email) ;

                // Check if email id is valid or not

                boolean EmailCheck = android.util.Patterns.EMAIL_ADDRESS.matcher(txtemail.getText().toString()).matches();
                if (!EmailCheck){
                    txtemail.setText("");
                    errortxtemail.setText("Please Add invalid email");
                }



                Boolean checkStop=Boolean.FALSE;

                if((txtusername.getText().toString()).length()==0) {
                    errortxtusername.setText("Please Enter Your username");
                    checkStop=Boolean.TRUE;
                }
                else if(mydata.CheckUsernameEXISTS(txtusername.getText().toString())) {
                    errortxtusername.setText("Enter Another username");
                    checkStop=Boolean.TRUE;
                }
                if((txtpassword.getText().toString()).length()<4){
                    errortxtpassword.setText("Please Enter Password at least 4");
                    checkStop=Boolean.TRUE;
                }
                if((txtphone.getText().toString()).length()==0){
                    errortxtphone.setText("Please Enter Your Phone ");
                    checkStop=Boolean.TRUE;
                }
                if((txtemail.getText().toString()).length()==0){
                    errortxtemail.setText("Please Enter Your email ");
                    checkStop=Boolean.TRUE;
                }
                if(checkStop)
                    return;

                mydata.AddNewUser(txtusername.getText().toString(),txtpassword.getText().toString(),txtphone.getText().toString(),txtemail.getText().toString(),date);
                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();

                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });


    }

}