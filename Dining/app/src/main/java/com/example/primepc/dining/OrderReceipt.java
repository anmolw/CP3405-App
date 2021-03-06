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

/**
 * Created by PRIMEPC on 5/9/2018.
 */

public class OrderReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_receipt);
        Button button = findViewById(R.id.home_bbutton);

        Bundle extras = getIntent().getExtras();
        String message = extras.getString("tablenumber");

        System.out.println(message);

        ListView cartList = findViewById(R.id.list01);
        CustomAdap customAdap = new CustomAdap();
        cartList.setAdapter(customAdap);

        final TextView textView = findViewById(R.id.tablenum);
        textView.setText("YOUR SEAT NO : "+message);


        }
            class CustomAdap extends BaseAdapter {

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
                    view = getLayoutInflater().inflate(R.layout.cart_layout, null);

                    TextView textView_name = view.findViewById(R.id.textView_name);
                    textView_name.setText(DynamicFoodlistActivity.cart.get(i).getName());
                    TextView textView_description = view.findViewById(R.id.textView_description);
                    textView_description.setText(DynamicFoodlistActivity.cart.get(i).getPrice());

                    return view;
                }
            }

        public void home(View view){

            Intent intent = new Intent(view.getContext(), MenuActivity.class);
            startActivity(intent);


        }

    }