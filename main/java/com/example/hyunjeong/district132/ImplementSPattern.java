package com.example.hyunjeong.district132;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Spinner;
import android.database.Cursor;
import android.widget.Toast;
import android.widget.TextView;

/**
 * Created by vinu on 8/4/17.
 */

public class ImplementSPattern extends AppCompatActivity {

    private Context context;
    private Object factory;
    private Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_results);
        final Spinner spinner = (Spinner) findViewById(R.id.sortspinner);


        // spinner.getOnItemSelectedListener();
        Intent intent = getIntent();

        // String sorttype="Sort by price";

        String sorttype = intent.getStringExtra("sort");
        switch (sorttype) {
            case "Sort by size":
                Toast.makeText(this, "sorting by size", Toast.LENGTH_LONG).show();
                break;
            case "Sort by price":
                Toast.makeText(this, " sorting by price", Toast.LENGTH_LONG).show();
                break;

        }
        String[] array = intent.getStringArrayExtra("array");
        String location = array[0];
        String type = array[1];
        String purpose = array[2];


        switch (sorttype) {
            case "Sort by price": {
                SContext sContext = new SContext(new SortbyPrice(this, null, null, 1));

                /*String[] array=intent.getStringArrayExtra("array");
                String location=array[0];
                String type=array[1];
                String purpose=array[2];*/


                c = sContext.executeSort(location, type, purpose);

                break;
            }
            case "Sort by size": {
                SContext sContext = new SContext(new SortbySize(this, null, null, 1));

               /* String[] array1=intent.getStringArrayExtra("array1");
                String location=array1[0];
                String type=array1[1];
                String purpose=array1[2];*/
                c = sContext.executeSort(location, type, purpose);
                break;
            }
        }
        ListView list = (ListView) findViewById(R.id.list);

        PostDBHandler dbHandler = new PostDBHandler(this, null, null, 1);

        PostResultCursorAdapter todoAdapter = new PostResultCursorAdapter(this, c);
        list.setAdapter(todoAdapter);
    }
        //Intent intent = new Intent(this, .class);
        //startActivity(intent);

    public void onBackPressed() {

        // Put your own code here which you want to run on back button click.

        Toast.makeText(this,"Back Button is clicked.", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }



    }


