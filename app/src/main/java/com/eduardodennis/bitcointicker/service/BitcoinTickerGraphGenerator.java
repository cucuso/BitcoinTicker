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
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eddie on 11/27/2014.
 */
public class BitcoinTickerGraphGenerator extends AsyncTask<Void, Void, JSONArray> {


    private Activity activity;
    private final static Logger l = Logger.getLogger(BitcoinTickerGraphGenerator.class.getName());

    public BitcoinTickerGraphGenerator(Activity activity) {

        this.activity = activity;

    }


    @Override
    protected JSONArray doInBackground(Void... params) {

        l.info("BitcoinTickerGraphGenerator::doInBackground: begining background process for graph data ");

        JSONArray graphData = null;

        BlockChainInfoGraphData blockChainInfoData = new BlockChainInfoGraphData();


        try {
            graphData = blockChainInfoData.getPrice();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return graphData;
    }

    @Override
    protected void onPostExecute(JSONArray results) {


        GraphView.GraphViewData[] data = new GraphView.GraphViewData[results.length()];

        for (int i = 0; i < results.length(); i++) {


            try {


                JSONObject price_point = results.getJSONObject(i);

                l.info(String.format("BitcoinTickerGraphGenerator::onPostExecute: x value: %s, y value: %s  ", price_point.getInt("x"), price_point.getInt("y")));

                data[i] = new GraphView.GraphViewData(price_point.getInt("x"), price_point.getDouble("y"));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(data);

        GraphView graphView = new LineGraphView(activity, "test");
        graphView.addSeries(exampleSeries); // data

        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.graphLayout);
        layout.addView(graphView);


        View contentView = activity.findViewById(R.id.graphLayout);


    }
}

