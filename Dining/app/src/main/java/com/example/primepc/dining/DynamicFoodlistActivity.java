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

    static ArrayList<Item> cart = new ArrayList<>();
    Restaurant chosenRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_foodlist);

        findRestaurantById();

        TextView title = findViewById(R.id.restaurant);
        title.setText(chosenRestaurant.getName());

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

    private void findRestaurantById(){
        for (Restaurant restaurant:
             RestaurantsActivity.restaurants) {
            if (restaurant.getId() == RestaurantsActivity.chosenRestaurant){
                chosenRestaurant = restaurant;
            }
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
        System.out.println(cart);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return chosenRestaurant.getItems().size();
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
            textView_name.setText(chosenRestaurant.getItems().get(i).getName());

            TextView textView_description = view.findViewById(R.id.textView_description);
            textView_description.setText(chosenRestaurant.getItems().get(i).getPrice());
            ImageButton imageButton = view.findViewById(R.id.imageButton);
            imageButton.setTag(chosenRestaurant.getItems().get(i));

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cart.add((Item)view.getTag());
                }
            });
            return view;
        }
    }
}
