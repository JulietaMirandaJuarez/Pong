package com.heurix.elements;

import java.awt.*;

public class Pad extends Element {

    private int speed = 5;

    public Pad(int x, int y, int id) {
        super(x, y, 25,100);
        super.centerY();
        int posX = (id==1) ? (int) super.getX() + (int) super.getWidth() - 2 : (int) super.getX() + 2;
    }

    public void moveUp() {
        super.setY(super.getY()+speed);
    }

    public void moveDown() {
        super.setY(super.getY()-speed);
    }
}
