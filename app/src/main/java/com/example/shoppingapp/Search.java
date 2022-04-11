package com.example.shoppingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingapp.Adapter.ItemsAdapter;
import com.example.shoppingapp.Database.OnlineShoppingDataset;
import com.example.shoppingapp.Layout.SpecificLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class Search extends AppCompatActivity {

    Button searchBtn;
    ImageButton voiceBtn ;
    EditText searchTxt;
    OnlineShoppingDataset dataset;

    ListView listViewitems;
    String [] img;
    String[] names;
    int [] id;

    LayoutInflater mylayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String myvoice = "";
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            myvoice = text.get(0).toString();
            searchTxt.setText(myvoice);

            String stringSearch = searchTxt.getText().toString();
            if(stringSearch.equals(""))
                return;
            viewList(stringSearch);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"ErrorNew",Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideTitlebar && BottomNavigationView
        SpecificLayout mylayout2=new SpecificLayout(this);
        mylayout2.hideTitlebar();
        setContentView(R.layout.activity_search);
        mylayout2.bottomNavigate();

        voiceBtn = (ImageButton) findViewById(R.id.imageButton_voiceSearch);
        searchBtn = (Button) findViewById(R.id.button_search_searchtext);
        searchTxt = (EditText)findViewById(R.id.editText_search);
        listViewitems = findViewById(R.id.Listview_Search);
        dataset = new OnlineShoppingDataset(this);

        voiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                try {
                    startActivityForResult(intent,1);
                }catch (ActivityNotFoundException e)
                {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }


            }
        });



        img = null;
        names = null;
        id = null;
        mylayout = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringSearch = searchTxt.getText().toString();
                viewList(stringSearch);
            }
        });

        listViewitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idd) {

                Intent intent = new Intent(Search.this , ItemDetailsActivity.class);
                intent.putExtra("id" , id[position]);
                startActivity(intent);
            }
        });

    }

    public void viewList(String stringSearch)
    {
        Cursor cursor= dataset.searchItemsbyName(stringSearch );

        if(cursor!=null) {

            img = new String[cursor.getCount()];
            names = new String[cursor.getCount()];
            id = new int[cursor.getCount()];
            int index = 0;

            while (!cursor.isAfterLast()) {

                id[index] = Integer.parseInt(cursor.getString(0));
                names[index] = cursor.getString(1);
                img[index] = cursor.getString(7);
                index++;
                cursor.moveToNext();
            }
            ItemsAdapter itemsAdapter = new ItemsAdapter(names,img,getApplicationContext(),mylayout);
            listViewitems.setAdapter(itemsAdapter);
        }
    }

}