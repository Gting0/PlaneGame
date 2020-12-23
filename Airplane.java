package com.plane;

import java.awt.*;

public class Airplane extends Fu{
    private int ySpeed = 3;

    public Airplane() {
        super((int)(Math.random()*(Window.WIDTH - Window.airplane.getWidth())), -Window.airplane.getHeight(), Window.airplane);
        this.setBlood(1);
        this.setScore(1);
    }
    @Override
    public void move() {
        this.setY(this.getY() + ySpeed);
    }
    public void breakFly(){
        setImg(Window.airplane_ember0);
    }
}
