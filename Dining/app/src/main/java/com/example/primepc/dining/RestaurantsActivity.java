package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    ArrayList<String> restaurants = new ArrayList<>();
    ArrayList<String> thumbnails = new ArrayList<>();
    static String data;
    static String chosenRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        FetchData fd = new FetchData();
        fd.execute();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parseData();

        ListView restaurantList = findViewById(R.id.restaurant_list);
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

    private void parseData() {
        try {
            System.out.println(data);

            JSONArray restaurantArray = new JSONArray(data);
            for (int i = 0; i < restaurantArray.length(); i++) {
                JSONObject restaurant = restaurantArray.getJSONObject(i);
                restaurants.add(restaurant.getString("name"));
                thumbnails.add(restaurant.getString("thumbnail"));
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

    private void openDynamicFoodlistActivity(){
        Intent intent = new Intent(this,DynamicFoodlistActivity.class);
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.restaurant_layout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            Button button_name = view.findViewById(R.id.button_name);
            button_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chosenRestaurant = ((Button) view).getText().toString() ;
                    openDynamicFoodlistActivity();
                }
            });

            Picasso.get().load(thumbnails.get(i)).into(imageView);
            button_name.setText(restaurants.get(i));

            return view;
        }
    }
}
