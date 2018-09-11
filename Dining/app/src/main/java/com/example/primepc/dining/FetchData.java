package com.example.primepc.dining;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class FetchData extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        HttpsURLConnection connection;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL requestURL = new URL("https://dt.anmolw.com/api/restaurants?format=json");
            connection = (HttpsURLConnection) requestURL.openConnection();
            InputStream is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
            RestaurantsActivity.data = sb.toString();

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
