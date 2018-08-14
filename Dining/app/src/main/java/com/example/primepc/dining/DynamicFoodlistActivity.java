package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DynamicFoodlistActivity extends AppCompatActivity {

    ArrayList<String> dishes = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_foodlist);

        TextView title = findViewById(R.id.restaurant);
        title.setText(RestaurantsActivity.chosenRestaurant);

        parseData();

        ListView foodList = findViewById(R.id.food_list);
        CustomAdapter foodAdapter = new CustomAdapter();
        foodList.setAdapter(foodAdapter);


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

        Button placeOrderButton = findViewById(R.id.check_order);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderCartActivity();
            }
        });
    }

    private void parseData() {
        try {
            JSONArray restaurantArray = new JSONArray(RestaurantsActivity.data);
            for (int i = 0; i < restaurantArray.length(); i++) {
                JSONObject restaraunt = restaurantArray.getJSONObject(i);
                if (restaraunt.getString("name").equals(RestaurantsActivity.chosenRestaurant)){
                    JSONArray items = restaraunt.getJSONArray("items");
                    for (int y = 0; y < items.length(); y++) {
                        JSONObject indianFood = items.getJSONObject(y);
                        dishes.add(indianFood.getString("name"));
                        prices.add(indianFood.getString("price"));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void openRestaurantsActivity() {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this, SeatingActivity.class);
        startActivity(intent);
    }

    private void openOrderCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return dishes.size();
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
            view = getLayoutInflater().inflate(R.layout.foodlist_layout, null);

            final TextView textView_name = view.findViewById(R.id.textView_name);
            textView_name.setText(dishes.get(i));
            TextView textView_description = view.findViewById(R.id.textView_description);
            textView_description.setText(prices.get(i));
            ImageButton imageButton = view.findViewById(R.id.imageButton);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cart.add((String) textView_name.getText());
                }
            });
            return view;
        }
    }
}
