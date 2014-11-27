package com.eduardodennis.bitcointicker.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.eduardodennis.bitcointicker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Retrieve current Bitcoin price from the bitstamp.
 * <p/>
 * Created by Eddie on 11/24/2014.
 */
public class BitStampApi implements BitcoinData {


    double price = 0.0;


    @Override
    public double getPrice() {


        try {
            URL url = new URL("https://www.bitstamp.net/api/ticker/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    (InputStream) conn.getContent(), "UTF-8"));
            String json = reader.readLine();
            JSONObject jsonResponse = new JSONObject(new String(json));
            price = jsonResponse.getDouble("last");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return price;
    }


}
