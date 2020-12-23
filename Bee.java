package com.plane;

import java.awt.*;

public class Bee extends Fu implements Award{
    private int xSpeed = 2;
    private int ySpeed = 2;
    private int awardType = (int)(Math.random()*5);
    public Bee() {
        super((int)(Math.random()*(Window.WIDTH - Window.bee.getWidth())), -Window.bee.getHeight(), Window.bee);
        this.setBlood(1);
        this.setScore(0);
    }

    public int getAwardType() {
        return awardType;
    }

    public void setAwardType(int awardType) {
        this.awardType = awardType;
    }

    @Override
    public void move() {
        this.setX(this.getX() + xSpeed);
        this.setY(this.getY() + ySpeed);
        if (this.getX() >= Window.WIDTH-this.getImg().getWidth() || this.getX() <= 0){
            xSpeed = -xSpeed;
        }
    }

    @Override
    public int awardType() {
        return awardType;
    }
}
