package com.example.primepc.dining;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by PRIMEPC on 12/9/2018.
 */
public class HttpSeat extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://dt.anmolw.com/api/reservations");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "Token 8fd74020cf61e5e6d3504c3c407be5883f09edca");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream os = new BufferedOutputStream(con.getOutputStream());
            System.out.println("*** Sending Response ***");
            os.write(SeatMap.json.getBytes("UTF-8"));
            os.close();

            InputStream is;
            if (con.getResponseCode() / 100 == 2) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            System.out.println("*** Server Response ***");
            String result = convertStreamToString(is);
            System.out.println(result);
            is.close();
            con.disconnect();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}