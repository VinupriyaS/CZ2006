package com.example.hyunjeong.district132;

import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    EditText edName, edPass;
    Button bRegister, bSignIn;
    LoginDatabaseAdapter loginDatabaseAdapter;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDatabaseAdapter = new LoginDatabaseAdapter(this);
        loginDatabaseAdapter = loginDatabaseAdapter.open();

        session = new UserSessionManager(getApplicationContext());

        edName = (EditText) findViewById(R.id.eduserName);
        edPass = (EditText) findViewById(R.id.edpassword);

        bRegister = (Button) findViewById(R.id.bRegister);
        bSignIn = (Button) findViewById(R.id.bSignIn);

        //signin
        bSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = edName.getText().toString();
                String password = edPass.getText().toString();
                String storedPassword = loginDatabaseAdapter.getSingleEntry(username);
                if (password.equals(storedPassword)) {
                    Toast.makeText(Login.this, "login Successful", Toast.LENGTH_LONG).show();

                    session.createUserLoginSession(username, password);

                    Intent toSignin = new Intent(Login.this, LoggedIn.class);
                    startActivity(toSignin);
                }else if (username.equals(" ")) {
                    edName.setError("Please enter username");
                    edName.requestFocus();
                    return;
                } else if (!username.equals(" ") && password.equals(" ")) {
                    edPass.setError("Please enter password");
                    edPass.requestFocus();
                    return;
                }else {
                    edPass.setError("Password incorrect");
                    edPass.requestFocus();
                    return;
                }
            }
        });


        //register
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edName.getText().toString();
                String password = edPass.getText().toString();
                String storedPassword = loginDatabaseAdapter.getSingleEntry(username);

                if (username.equals(" ")) {
                    edName.setError("Please enter username");
                    edName.requestFocus();
                    return;
                } else if (!username.equals(" ") && password.equals(" ")) {
                    edPass.setError("Please enter password");
                    edPass.requestFocus();
                    return;
                }else if(!username.equals(" ") && !storedPassword.equals("NOT EXIST")){
                    edName.setError("Username already exists");
                    edName.requestFocus();
                    return;
                }else if(!username.equals(" ") && (password.length()<8 || password.length()>17)){
                    edPass.setError("Password must be at least 8 letters and at most 16 letters long");
                    edPass.requestFocus();
                    return;
                }else if(!username.equals(" ") && username.length()<5){
                    edName.setError("Username must be at least 6 letters long");
                    edName.requestFocus();
                    return;
                }
                else {
                    loginDatabaseAdapter.insertEntry(username, password);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                    return;
                }
            }


        });
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //close database
        loginDatabaseAdapter.close();
    }
}









