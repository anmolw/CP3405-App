package com.example.primepc.dining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DynamicFoodlistActivity extends AppCompatActivity {

    ArrayList<String> dishes = new ArrayList<String>();
    ArrayList<String> prices = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_foodlist);

        TextView title = findViewById(R.id.restaurant);
        title.setText(RestaurantsActivity.chosenRestaurant);
    }

    private void parseData() {
        try {
            JSONArray jArray = new JSONArray(RestaurantsActivity.data);
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
