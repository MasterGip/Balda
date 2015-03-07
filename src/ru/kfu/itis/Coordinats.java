package ru.kfu.itis;

import java.io.Serializable;

/**
 * Created by mg on 15.02.15.
 */
public class Coordinats implements Serializable {



    private int x;
    private int y;

    Coordinats(int xPosition, int yPosition){
        x = xPosition;
        y = yPosition;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
