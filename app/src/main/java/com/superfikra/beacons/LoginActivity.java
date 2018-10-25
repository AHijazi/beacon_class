package com.superfikra.beacons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }



    public void launchSecondActivity(View view) {
        Log.d("LOG", "Button clicked!");
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);


    }
}
