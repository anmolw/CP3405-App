package com.example.primepc.dining;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodlistActivity extends AppCompatActivity {

    ArrayList<String> restaurants = new ArrayList<>();
    ArrayList<String> thumbnails = new ArrayList<>();
    static String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        fetchData fd = new fetchData();
        fd.execute();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parseData();

        System.out.println("Updating List");
        ListView restaurantList = findViewById(R.id.food_list);

        CustomAdapter restaurantAdapter = new CustomAdapter();

        restaurantList.setAdapter(restaurantAdapter);


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

    private void parseData() {
        System.out.println("Parsing JSON");
        try {
            System.out.println(data);

            JSONArray restaurantArray = new JSONArray(data);
            for (int i = 0; i < restaurantArray.length(); i++) {
                JSONObject restaurant = restaurantArray.getJSONObject(i);
                restaurants.add(restaurant.getString("name"));
                thumbnails.add(restaurant.getString("thumbnail"));
            }
            System.out.println("Parsing Done");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void openIndianFoodActivity() {
        Intent intent = new Intent(this, FoodlistActivity.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this, SeatingActivity.class);
        startActivity(intent);
    }

    private void openOrderCartActivity() {
        Intent intent = new Intent(this, order_cart.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return restaurants.size();
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
            view = getLayoutInflater().inflate(R.layout.restaurant_layout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            Button button_name = view.findViewById(R.id.button_name);

            Picasso.get().load(thumbnails.get(i)).into(imageView);
            button_name.setText(restaurants.get(i));

            return view;
        }
    }
}
