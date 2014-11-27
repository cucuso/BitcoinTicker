package com.eduardodennis.bitcointicker.ui;


import com.eduardodennis.bitcointicker.R;
import com.eduardodennis.bitcointicker.service.BitcoinTickerGenerator;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        new BitcoinTickerGenerator(MainActivity.this).execute();



  /*

        //Graph init
        new Graph(this).execute();




        // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 2.0d)
                , new GraphViewData(2, 1.5d)
                , new GraphViewData(3, 2.5d)
                , new GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(
                this // context
                , "GraphViewDemo" // heading
        );
        graphView.addSeries(exampleSeries); // data

        LinearLayout layout = (LinearLayout) findViewById(R.id.graphLayout);
        layout.addView(graphView);




         View contentView = findViewById(R.id.graphLayout);


        */


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }


}
