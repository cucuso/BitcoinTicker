package com.eduardodennis.bitcointicker.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

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
 * Created by Eddie on 11/27/2014.
 */
public class BitcoinTickerGenerator extends AsyncTask<Void, Void, String> {


    private Activity activity;


    public BitcoinTickerGenerator(Activity activity) {

        this.activity = activity;

    }


    @Override
    protected String doInBackground(Void... params) {

        BitcoinData btcData = new BitStampApi();


        return String.valueOf(btcData.getPrice());
    }

    @Override
    protected void onPostExecute(String results) {

        TextView textView = (TextView) activity.findViewById(R.id.priceTextView);

        textView.setText("$" + results);

    }
}

