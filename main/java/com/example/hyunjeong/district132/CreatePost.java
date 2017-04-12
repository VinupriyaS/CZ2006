package com.example.hyunjeong.district132;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import java.util.HashMap;


public class CreatePost extends AppCompatActivity {
    int id, no_of_rooms;
    String username, location, housetype, facilities, desc,address;
    int price, size;
    String purpose, furnishing;
    PostDBHandler dbHandler;
    PostDB post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        final UserSessionManager session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        username = user.get(UserSessionManager.KEY_NAME);

        session.checkLogin();










        dbHandler = new PostDBHandler(this, null, null, 1);
        Button submit=(Button) findViewById(R.id.submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //get data from user

                EditText priceinfo,sizeinfo,addrinfo,description;
                Spinner salerent=(Spinner) findViewById(R.id.purpose);
                purpose=String.valueOf(salerent.getSelectedItem());
                Spinner furnish=(Spinner) findViewById(R.id.furnishing);
                furnishing=String.valueOf(furnish.getSelectedItem());

                description=(EditText) findViewById(R.id.editText7);
                sizeinfo=(EditText) findViewById(R.id.editText6);
                addrinfo=(EditText) findViewById(R.id.AddressEt);
                priceinfo=(EditText) findViewById(R.id.editText8);
                price=Integer.parseInt(priceinfo.getText().toString());
                size=Integer.parseInt(sizeinfo.getText().toString());
                address=addrinfo.getText().toString();
                desc=description.getText().toString();

                Spinner loc,type,rooms;
                loc=(Spinner) findViewById(R.id.locationspinner);
                type=(Spinner) findViewById(R.id.typespinner);
                rooms=(Spinner) findViewById(R.id.numberofroomspinner);

                location=String.valueOf(loc.getSelectedItem());
                housetype=String.valueOf(type.getSelectedItem());
                no_of_rooms=Integer.parseInt(String.valueOf(rooms.getSelectedItem()));

                id=0;


                post = new PostDB(username, location, housetype, purpose, price, no_of_rooms,
                        furnishing, facilities, desc, size);

                dbHandler.addPost(post);
                Toast.makeText(CreatePost.this, "Post Successful", Toast.LENGTH_LONG).show();
                Intent homepage=new Intent(CreatePost.this,LoggedIn.class);
                startActivity(homepage);
            }
        });

    }

}
//public PostDB(int post_id, String username, String location, String housetype, boolean purpose, double price,
//int no_of_rooms, boolean furnishing, String facilities, String desc, double size) {

