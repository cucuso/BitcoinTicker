package com.eduardodennis.bitcointicker.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduardodennis.bitcointicker.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Eddie on 11/27/2014.
 */
public class BitcoinTickerPriceGenerator extends AsyncTask<Void, Void, String> {


    private Activity activity;


    public BitcoinTickerPriceGenerator(Activity activity) {

        this.activity = activity;

    }


    @Override
    protected String doInBackground(Void... params) {

        BitcoinData btcPriceData = new BitStampCurrentPrice();
        BlockChainInfoGraphData blockChainInfoData = new BlockChainInfoGraphData();


        return String.valueOf(btcPriceData.getPrice());
    }

    @Override
    protected void onPostExecute(String results) {

        TextView textView = (TextView) activity.findViewById(R.id.priceTextView);

        textView.setText("$" + results);





    }
}

