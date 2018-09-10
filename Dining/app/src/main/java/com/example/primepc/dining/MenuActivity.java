package com.example.primepc.dining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.primepc.dining.Constants.F_Name;

/**
 * Created by PRIMEPC on 6/8/2018.
 */

public class MenuActivity extends AppCompatActivity{

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    static String announcementsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        FetchAnnouncements fa = new FetchAnnouncements();
        fa.execute();

        try {
            Thread.sleep(1000);
            parseData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ListView announcementList = findViewById(R.id.announcemnt_list);
        CustomAdapter announcementAdapter = new CustomAdapter();
        announcementList.setAdapter(announcementAdapter);



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
            JSONArray announcementsArray = new JSONArray(announcementsData);
            for (int i = 0; i < announcementsArray.length(); i++) {
                JSONObject announcement = announcementsArray.getJSONObject(i);
                titles.add(announcement.getString("title"));
                descriptions.add(announcement.getString("description"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this,MenuActivity.class);

        startActivity(intent);
    }

    private void openRestaurantsActivity() {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this,SeatingActivity.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return titles.size();
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
            view = getLayoutInflater().inflate(R.layout.announcement_layout, null);

            TextView textView_name = view.findViewById(R.id.textView_name);
            textView_name.setText(titles.get(i));
            TextView textView_description = view.findViewById(R.id.textView_description);
            textView_description.setText(descriptions.get(i));

            return view;
        }
    }
}
