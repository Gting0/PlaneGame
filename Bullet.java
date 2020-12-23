package com.plane;

import java.awt.*;

public class Bullet extends Fu{
    private int ySpeed = 13;
    public Bullet(int x,int y) {
        super(x, y, Window.bullet);
    }
    public void move() {
        this.setY(this.getY() - ySpeed);
    }
}
