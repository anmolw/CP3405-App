package com.example.primepc.dining;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class fetchData extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://e18fd4b5.ngrok.io/api/restaurants?format=json");

            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
            FoodlistActivity.data = sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br !=null){
                try{
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
