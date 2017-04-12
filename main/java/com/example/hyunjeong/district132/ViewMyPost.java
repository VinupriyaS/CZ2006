package com.example.hyunjeong.district132;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class ViewMyPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_post);
        final UserSessionManager session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(UserSessionManager.KEY_NAME);
        String password = user.get(UserSessionManager.KEY_PASSWORD);

        session.checkLogin();

    }
}
