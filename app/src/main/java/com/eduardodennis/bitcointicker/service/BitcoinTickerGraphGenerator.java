package com.eduardodennis.bitcointicker.service;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduardodennis.bitcointicker.R;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
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




        GraphView graphView = new LineGraphView(activity, "");
        graphView.addSeries(exampleSeries); // data


        graphView.getGraphViewStyle().setGridStyle(GraphViewStyle.GridStyle.NONE);
        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);

        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                // TODO Auto-generated method stub
                if (isValueX) {
                    Date d = new Date((long) (value) * 1000);


                    return d.toString();
                }
                return "" + (int) value;
            }
        });


        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.graphLayout);
        layout.addView(graphView);


        View contentView = activity.findViewById(R.id.graphLayout);


    }
}

