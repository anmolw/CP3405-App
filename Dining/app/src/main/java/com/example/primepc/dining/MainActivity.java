package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void menu_login(View view){

        Intent myIntent = new Intent(this, MenuActivity.class);

        this.startActivity(myIntent);
    }
}
