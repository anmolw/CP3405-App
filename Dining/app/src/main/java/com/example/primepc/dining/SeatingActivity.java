package com.example.primepc.dining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SeatingActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;


    // URL to get contacts JSON
    private static String url = "https://anmolw.com/tables.json";

    ArrayList<HashMap<String, String>> SeatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        SeatList = new ArrayList<>();


        new GetDetails().execute();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seating);

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity();
            }
        });

        ImageButton orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRestaurantsActivity();
            }
        });

        ImageButton seatingButton = findViewById(R.id.seating_button);
        seatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSeatingActivity();
            }
        });
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    private void openRestaurantsActivity() {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        startActivity(intent);
    }

    private void openSeatingActivity() {
        Intent intent = new Intent(this,SeatingActivity.class);
        startActivity(intent);
    }

    private class GetDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SeatingActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonarray = new JSONArray(jsonStr);

                    // Getting JSON Array node

                    // looping through All details
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject c = jsonarray.getJSONObject(i);

                        String id = c.getString("id");
                        String seat = c.getString("seats_available");
                        String hall = c.getString("AC");




                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("seats_available", seat);
                        contact.put("AC", hall);

                        // adding contact to contact list
                        SeatList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            System.out.println("I come here !");




            for (HashMap<String, String> entry : SeatList) {
                System.out.println(entry.get("id"));

                String seats = entry.get("seats_available");
                int seat_count = 0;
                if(Integer.parseInt(seats) == 0){
                    seat_count=4;
                    }
                else if(Integer.parseInt(seats) == 1){
                    seat_count=3;

                }else if(Integer.parseInt(seats) == 2){
                    seat_count=2;

                }else if(Integer.parseInt(seats) == 3){
                    seat_count=1;

                }
                else if(Integer.parseInt(seats) == 4){
                    seat_count=0;

                }


                String value = entry.get("id");
                switch (Integer.parseInt(value)) {
                    case 1:  Button button = findViewById(R.id.seat01);
                        button.setText((String.valueOf(seat_count))+"/4");
                        break;
                    case 2:  Button button2 = findViewById(R.id.seat02);
                        button2.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 3:  Button button3 = findViewById(R.id.seat03);
                        button3.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 4: Button button4 = findViewById(R.id.seat04);
                        button4.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 5:  Button button5 = findViewById(R.id.seat05);
                        button5.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 6:  Button button6 = findViewById(R.id.seat06);
                        button6.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 7: Button button7 = findViewById(R.id.seat07);
                        button7.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 8:  Button button8 = findViewById(R.id.seat08);
                        button8.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 9:  Button button9 = findViewById(R.id.seat09);
                        button9.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 10: Button button10 = findViewById(R.id.seat10);
                        button10.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 11: Button button11 = findViewById(R.id.seat11);
                        button11.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 12:Button button12 = findViewById(R.id.seat12);
                        button12.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 13: Button button13 = findViewById(R.id.seat13);
                        button13.setText((Integer.toString(seat_count))+"/4");
                        break;

                    case 14: Button button14 = findViewById(R.id.seat14);
                        button14.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 15: Button button15 = findViewById(R.id.seat15);
                        button15.setText((Integer.toString(seat_count))+"/4");
                        break;
                    case 16: Button button16 = findViewById(R.id.seat16);
                        button16.setText((Integer.toString(seat_count))+"/4");
                        break;

                    default:
                        break;
                }
                System.out.println("done!!");
            }
        }
    }
}





