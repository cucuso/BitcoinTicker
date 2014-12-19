package com.eduardodennis.bitcointicker.ui;


import com.eduardodennis.bitcointicker.R;
import com.eduardodennis.bitcointicker.service.BitcoinTickerGraphGenerator;
import com.eduardodennis.bitcointicker.service.BitcoinTickerPriceGenerator;
import com.eduardodennis.bitcointicker.ui.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        new BitcoinTickerPriceGenerator(MainActivity.this).execute();
        new BitcoinTickerGraphGenerator(MainActivity.this).execute();


        final ImageButton btnNews =(ImageButton)findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news = new Intent(view.getContext(), News.class);
                startActivity(news);
            }
        });


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
