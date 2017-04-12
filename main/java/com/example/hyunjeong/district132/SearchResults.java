package com.example.hyunjeong.district132;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Spinner;
import android.content.Context;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;

public class SearchResults extends AppCompatActivity {

    String location;
    String type;
    //String purpose;
    //public  String[] stringArray;
    //Context context;
    //private Object factory;

    SortbySize sbs = new SortbySize(this, null, null, 1);
    SortbyPrice sbp = new SortbyPrice(this, null, null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        /*
        // Get the Intent that started this activity and extract the string
        Bundle bundle = getIntent().getExtras();
        //Intent intent = getIntent();
        String location = bundle.getString(MainActivity.EXTRA_LOCATION);
        String type = bundle.getString(MainActivity.EXTRA_TYPE);
        String purpose = bundle.getString(MainActivity.EXTRA_PURPOSE);

        // Capture the layout's TextView and set the string as its text
        TextView textView1 = (TextView) findViewById(R.id.textView4);
        textView1.setText(location);
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        textView2.setText(type);
        TextView textView3 = (TextView) findViewById(R.id.textView7);
        textView3.setText(purpose);
        */

        Intent intent = getIntent();
        final String[] stringArray = intent.getStringArrayExtra("array");


        location = stringArray[0];
        type = stringArray[1];
        final String purpose = stringArray[2];
        // final String[] array={location,type,purpose};
        //sbs.getValues(stringArray);
        //sbp.getValues(stringArray);

        //stringArray is not null.
        //TextView textview=(TextView) findViewById(R.id.textView);
        //textview.setText(stringArray[0]);

        Spinner spinner = (Spinner) findViewById(R.id.sortspinner);
        String sort = spinner.getSelectedItem().toString();

        final Intent intent1;

        intent1 = new Intent(SearchResults.this, ImplementSPattern.class);
        String[] array = {location, type, purpose};
        intent1.putExtra("array", array);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long row_id) {
                // Intent intent;
                switch (position) {

                    case 1:
                        // intent = new Intent(SearchResults.this, ImplementSPattern.class);
                        String s = "Sort by size";

                        // String[] array={location,type,purpose};
                        intent1.putExtra("sort", s);
                        //intent.putExtra("array",array);
                        startActivity(intent1);
                        break;
                    case 2:
                        // intent = new Intent(SearchResults.this, ImplementSPattern.class);
                        String t = "Sort by price";
                        intent1.putExtra("sort", t);
                        String[] array1 = {location, type, purpose};
                        intent1.putExtra("array1", array1);

                        startActivity(intent1);
                        break;


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });



        /*TextView textView1 = (TextView) findViewById(R.id.textView4);
        textView1.setText(location);
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        textView2.setText(type);
        TextView textView3 = (TextView) findViewById(R.id.textView7);
        textView3.setText(purpose);*/

        PostDBHandler dbHandler = new PostDBHandler(this, null, null, 1);

        Cursor posts = dbHandler.searchPost(location, type, purpose);

        /*String display = DatabaseUtils.dumpCursorToString(posts);
        TextView textView4 = (TextView) findViewById(R.id.textView8);
        textView4.setText(display);*/
        // Find ListView to populate
        final ListView list = (ListView) findViewById(R.id.list);

        // Setup cursor adapter using cursor from last step
        final PostResultCursorAdapter todoAdapter = new PostResultCursorAdapter(this, posts);

        // The desired columns to be bound
        /*String[] columns = new String[] {
                PostDBHandler.COLUMN_POST_ID,
                PostDBHandler.COLUMN_USERNAME,
                PostDBHandler.COLUMN_LOCATION,
                PostDBHandler.COLUMN_PRICE,
                PostDBHandler.COLUMN_SIZE,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.postID,
                R.id.postUsername,
                R.id.postLocation,
                R.id.postPrice,
                R.id.postSize,
        };


        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                this, R.layout.list_view,
                posts,
                columns,
                to,
                0);*/

        // Attach cursor adapter to the ListView
        list.setAdapter(todoAdapter);

        //posts.close();

        /*Intent intent = getIntent();
        if (null != intent) {
            location = intent.getStringExtra("location");
            housetype = intent.getStringExtra("housetype");
            purpose = intent.getStringExtra("purpose");
        }
        TextView view = (TextView) findViewById(R.id.noResults);
        view.setText(location);

        PostDBHandler dbHandler = new PostDBHandler(this, null, null, 1);

        Cursor posts = dbHandler.searchPost(location, housetype, purpose);

        DatabaseUtils.dumpCursorToString(posts);

        //searchResults(location, housetype, purpose);
        setContentView(R.layout.activity_search_results);



    }

    public void searchResults(String location, String housetype, String purpose) {
        PostDBHandler dbHandler = new PostDBHandler(this, null, null, 1);

        Cursor posts = dbHandler.searchPost(location, housetype, purpose);
        posts.close();
        /*if (!(posts.moveToFirst()) || posts.getCount() == 0)
            noResultsFound();
        else
            displayResults(posts);

    }*/

    /*public void displayResults(Cursor posts){
        // Find ListView to populate
        ListView list = (ListView) findViewById(R.id.list);

        // Setup cursor adapter using cursor from last step
        //PostResultCursorAdapter todoAdapter = new PostResultCursorAdapter(this, posts);

        // Attach cursor adapter to the ListView
       //list.setAdapter(todoAdapter);
    }

    /*public void noResultsFound(){
        TextView noResultsView = (TextView) findViewById(R.id.noResults);
        noResultsView.setText("No Results Found");
    }*/

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Displaydetails d;
                String postid = ((TextView) view.findViewById(R.id.postID)).getText().toString();
                Intent intent = new Intent(SearchResults.this, Displaydetails.class);
                startActivity(intent);
                //TextView location=(TextView) findViewById(R.id.postUsername);
                //String loc=location.getText().toString();
                //Toast.makeText(SearchResults.this, postid, Toast.LENGTH_LONG).show();


                return false;
            }
        });
    }
    /*public void noResultsFound(){
        TextView noResultsView = (TextView) findViewById(R.id.noResults);
        noResultsView.setText("No Results Found");
    }*/
}



