package com.example.primepc.dining;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class indianfood_list extends AppCompatActivity {

    static ArrayList<String> dishes = new ArrayList<String>();
    static ArrayList<String> prices = new ArrayList<String>();
    static String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indianfood_list);

        fetchData fd = new fetchData();
        fd.execute();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parseData();

        ListView restarauntList = findViewById(R.id.food_list);

        ArrayAdapter<String> adapter;

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
        try {
            System.out.println(data);

            JSONArray jArray = new JSONArray(data);
            JSONObject jb = jArray.getJSONObject(1); // index 1 - indian food
            JSONArray indianRestaurant = jb.getJSONArray("items");
            for (int y = 0; y < indianRestaurant.length(); y++) {
                JSONObject indianFood = indianRestaurant.getJSONObject(y);
                dishes.add(indianFood.getString("name"));
                prices.add(indianFood.getString("price"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void openIndianFoodActivity() {
        Intent intent = new Intent(this, indianfood_list.class);
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
            view = getLayoutInflater().inflate(R.layout.custom_layout,null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name = view.findViewById(R.id.textView_name);
            TextView textView_description = view.findViewById(R.id.textView_description);

            return null;
        }
    }
}
