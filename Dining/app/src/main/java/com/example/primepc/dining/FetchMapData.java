package com.example.primepc.dining;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FetchMapData extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataParsed;
    String singleParsed;
    int id;
    int seats_available;
    boolean AC;
    ArrayList<Integer> table;

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://anmolw.com/tables.json");

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line ;
                
                
            }






            JSONArray JA=new JSONArray(data);
            /**
            for (Integer integer : table = new ArrayList<Integer>()) {
             table.add(id);
            }
             */
            for(int i=1;i<JA.length();i++){
                JSONObject JO=(JSONObject) JA.get(i);
                dataParsed = "Table no:"+ JO.getInt("id")+ "\n"+
                                "Seats Available"+ JO.getInt("seats_available")+ "\n"+
                                "AC:"+ JO.getBoolean("AC")+"\n";
            }






        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        SeatMap.data.setText(this.dataParsed);



    }
}
