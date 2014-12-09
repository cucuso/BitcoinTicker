package com.eduardodennis.bitcointicker.ui;


import com.eduardodennis.bitcointicker.R;
import com.eduardodennis.bitcointicker.service.BitcoinTickerGraphGenerator;
import com.eduardodennis.bitcointicker.service.BitcoinTickerPriceGenerator;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        new BitcoinTickerPriceGenerator(MainActivity.this).execute();
        new BitcoinTickerGraphGenerator(MainActivity.this).execute();


    }


    @Override
    protected void onResume() {
        super.onResume();

        new BitcoinTickerPriceGenerator(MainActivity.this).execute();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }


}
