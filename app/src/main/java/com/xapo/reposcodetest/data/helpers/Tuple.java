package com.xapo.reposcodetest.data.helpers;

/**
 * Created by rclement on 10/5/18.
 */

public class Tuple<X,Y> {
    private X mX;
    private Y mY;
    public Tuple(X x, Y y) {
        this.mX = x;
        this.mY = y;
    }

    public X getX() {
        return mX;
    }

    public void setX(X x) {
        this.mX = x;
    }

    public Y getY() {
        return mY;
    }

    public void setY(Y y) {
        this.mY = y;
    }
}
