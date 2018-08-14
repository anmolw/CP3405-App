package com.example.primepc.dining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IndianFoodActivity extends AppCompatActivity {

    ArrayList<String> dishes = new ArrayList<String>();
    ArrayList<String> prices = new ArrayList<String>();
    ArrayList<String> images = new ArrayList<String>();
    static String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_food);
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
}
