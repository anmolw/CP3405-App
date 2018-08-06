package com.example.primepc.dining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.primepc.dining.Constants.F_Name;

/**
 * Created by PRIMEPC on 6/8/2018.
 */

public class MenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        TextView textView = findViewById(R.id.message1);
        textView.setText(F_Name);
    }






}
