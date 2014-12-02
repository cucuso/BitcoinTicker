package com.eduardodennis.bitcointicker.ui;


import com.eduardodennis.bitcointicker.R;
import com.eduardodennis.bitcointicker.service.BitcoinTickerGenerator;
import com.eduardodennis.bitcointicker.service.Graph;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        new BitcoinTickerGenerator(MainActivity.this).execute();







        //Graph init
        new Graph(this).execute();




        // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[] {
                new GraphView.GraphViewData(1, 2.0d)
                , new GraphView.GraphViewData(2, 1.5d)
                , new GraphView.GraphViewData(3, 2.5d)
                , new GraphView.GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(
                this // context
                , "GraphViewDemo" // heading
        );
        graphView.addSeries(exampleSeries); // data

        LinearLayout layout = (LinearLayout) findViewById(R.id.graphLayout);
        layout.addView(graphView);




         View contentView = findViewById(R.id.graphLayout);





    }


    @Override
    protected void onResume() {
        super.onResume();

        new BitcoinTickerGenerator(MainActivity.this).execute();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }


}
