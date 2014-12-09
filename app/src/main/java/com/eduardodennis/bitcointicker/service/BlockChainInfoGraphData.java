package com.eduardodennis.bitcointicker.service;

import android.view.View;
import android.widget.LinearLayout;

import com.eduardodennis.bitcointicker.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by Eddie on 11/27/2014.
 */
public class BlockChainInfoGraphData {



    public JSONArray getPrice() throws JSONException {

        double price;



        JSONObject data = getJSONfromURL("https://blockchain.info/charts/market-price?format=json");
        JSONArray data_array = data.getJSONArray("values");

        return data_array;
    }


    public static JSONObject getJSONfromURL(String URL) throws JSONException {
        try {
            URLConnection uc;
            URL url = new URL(URL);
            uc = url.openConnection();
            uc.setConnectTimeout(10000);
            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            uc.connect();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(uc.getInputStream(),
                            Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            String jsonText = (sb.toString());

            return new JSONObject(jsonText.toString());
        } catch (IOException ex) {
            return null;
        }
    }
}
