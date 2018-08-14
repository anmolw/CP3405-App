package com.example.primepc.dining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        Button homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity();
            }
        });

        Button orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRestaurantsActivity();
            }
        });

        Button seatingButton = findViewById(R.id.seating_button);
        seatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSeatingActivity();
            }
        });
    }


    private void openMenuActivity() {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    private void openRestaurantsActivity() {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this,SeatingActivity.class);
        startActivity(intent);
    }
}
