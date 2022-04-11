package com.example.shoppingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.CaptureAct;
import com.example.shoppingapp.Layout.SpecificLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class barCodeActivity extends AppCompatActivity {
    String barcode;
    OnlineShoppingDataset dataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide Title bar
        SpecificLayout mylayout=new SpecificLayout(this);
        mylayout.hideTitlebar();
        setContentView(R.layout.activity_bar_code);
        scancode();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null)
        {
            if(result.getContents() !=  null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                barcode = result.getContents();
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Agin", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scancode();
                    }
                }).setPositiveButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();

                        dataset = new OnlineShoppingDataset(getApplicationContext());

                        Cursor cursor = dataset.fetchItemsbybarcode(barcode);
                        int id ;
                        if(cursor ==null)
                            id = -1;
                        else
                            id = Integer.parseInt(cursor.getString(0));

                        Intent intent = new Intent(barCodeActivity.this, ItemDetailsActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {
                Toast.makeText(this,"No Result",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

    public void scancode()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

}