package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class order_cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);

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
                openIndianFoodActivity();
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

    private void openIndianFoodActivity() {
        Intent intent = new Intent(this, FoodlistActivity.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this,SeatingActivity.class);
        startActivity(intent);
    }
}
