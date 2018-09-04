package com.example.primepc.dining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText id,id2;
    private Button button2;
    private RequestQueue requestQueue;
    private static final String URL="https://dt.anmolw.com/api/login";
    private StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.id);
        id2 = (EditText) findViewById(R.id.id2);
        button2=(Button)findViewById(R.id.button2);

        requestQueue = Volley.newRequestQueue(this);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.has("token")){
                                Toast.makeText(getApplicationContext(),"token" + jsonObject.getString("token"),Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this,"Successful login",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                        }else{
                                Toast.makeText(MainActivity.this,"Invalid credentials,Please try again",Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"Error"+jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                            //    Toast.makeText(MainActivity.this,"Invalid credentials,Please try again",Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Invalid credentials,Please try again",Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put("username",id.getText().toString());
                        hashMap.put("password",id2.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });

    }


    public void menu_login(View view){

        Intent myIntent = new Intent(this, MenuActivity.class);

        this.startActivity(myIntent);
    }
}
