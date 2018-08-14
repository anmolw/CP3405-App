package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class indianfood_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indianfood_list);

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

        Button placeOrderButton = findViewById(R.id.place_order);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderCartActivity();
            }
        });
    }



    private void openMenuActivity() {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    private void openIndianFoodActivity() {
        Intent intent = new Intent(this, indianfood_list.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this,SeatingActivity.class);
        startActivity(intent);
    }

    private void openOrderCartActivity() {
        Intent intent = new Intent(this, order_cart.class);
        startActivity(intent);
    }
}
