package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
public class CartActivity extends AppCompatActivity {
    CustomAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView cartList = findViewById(R.id.cart_list);
        cartAdapter = new CustomAdapter();
        cartList.setAdapter(cartAdapter);

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

        Button place_order= findViewById(R.id.place_order);
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(DynamicFoodlistActivity.cart.get(0));
                placeCartOrder();
            }
        });
    }
    private void placeCartOrder(){
        System.out.print(cartAdapter);
        Intent intent = new Intent(this,SeatMap.class);
        startActivity(intent);
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    private void openIndianFoodActivity() {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this,SeatingActivity.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return DynamicFoodlistActivity.cart.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.cart_layout,null);

            TextView textView_name = view.findViewById(R.id.textView_name);
            textView_name.setText(DynamicFoodlistActivity.cart.get(i));
            TextView textView_description = view.findViewById(R.id.textView_description);
            textView_description.setText(DynamicFoodlistActivity.prices.get(DynamicFoodlistActivity.dishes.indexOf(DynamicFoodlistActivity.cart.get(i))));

            return view;
        }
    }
}
