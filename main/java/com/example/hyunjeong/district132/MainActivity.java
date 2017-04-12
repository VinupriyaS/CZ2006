package com.example.hyunjeong.district132;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String loc;
    String hse;
    String sl;
    public static final String EXTRA_LOCATION = "com.example.hyunjeong.district132.location";
    public static final String EXTRA_TYPE = "com.example.hyunjeong.district132.location";
    public static final String EXTRA_PURPOSE = "com.example.hyunjeong.district132.location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final UserSessionManager session = new UserSessionManager(getApplicationContext());

        final Button signInButton = (Button) findViewById(R.id.SignIn);
        final Button searchButton = (Button) findViewById(R.id.Search);
        signInButton.setOnClickListener(this);

        /*if (slease == "Sale")
            sl = true;
        else if (slease == "Lease")
            sl = false;*/

        /*searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loc = location.getSelectedItem().toString();
                hse = hseType.getSelectedItem().toString();
                sl = saleLease.getSelectedItem().toString();
                //TextView view = (TextView) findViewById(R.id.noResults);
                //view.setText(loc);
                Intent toSearchResults = new Intent(MainActivity.this, SearchResults.class);
                toSearchResults.putExtra("location", loc);
                toSearchResults.putExtra("housetype", hse);
                toSearchResults.putExtra("purpose", sl);
                startActivity(toSearchResults);
            }
        });*/



        if(session.checkLoginMain()){
            Intent alrLoggedIn = new Intent(MainActivity.this, LoggedIn.class);
            startActivity(alrLoggedIn);
        }
    }

    public void search(View view) {
        // Do something in response to button

        final Spinner location = (Spinner) findViewById(R.id.Location);
        final Spinner hseType = (Spinner) findViewById(R.id.Type);
        final Spinner saleLease = (Spinner) findViewById(R.id.SaleLease);
        loc = location.getSelectedItem().toString();
        hse = hseType.getSelectedItem().toString();
        sl = saleLease.getSelectedItem().toString();

        /*TextView textView1 = (TextView) findViewById(R.id.textView4);
        textView1.setText(loc);
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        textView2.setText(hse);
        TextView textView3 = (TextView) findViewById(R.id.textView7);
        textView3.setText(sl);*/

        String[] values = new String[3];
        values[0]=loc;
        values[1]=hse;
        values[2]=sl;

        /*TextView textView1 = (TextView) findViewById(R.id.textView4);
        textView1.setText(values[0]);
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        textView2.setText(values[1]);
        TextView textView3 = (TextView) findViewById(R.id.textView7);
        textView3.setText(values[2]);*/

        Intent intent = new Intent(this, SearchResults.class);
        intent.putExtra("array", values);
        startActivity(intent);

        /*Intent intent = new Intent(this, SearchResults.class);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_LOCATION, loc);
        extras.putString(EXTRA_TYPE, hse);
        extras.putString(EXTRA_PURPOSE, sl);
        intent.putExtras(extras);
        startActivity(intent);*/
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SignIn: {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                break;
            }
        }
    }



}