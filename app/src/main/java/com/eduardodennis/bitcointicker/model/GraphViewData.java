package com.eduardodennis.bitcointicker.model;

/**
 * Created by Eddie on 11/26/2014.
 */

import com.jjoe64.graphview.GraphViewDataInterface;
public class GraphViewData implements GraphViewDataInterface {

    private double x;
    private double y;

    public GraphViewData(double x, double y){

        this.x = x;
        this.y = y;
    }
    @Override
    public double getX() {

        return x;
    }

    @Override
    public double getY() {

        return y;
    }

}
