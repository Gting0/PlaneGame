package com.plane;

import java.awt.*;

public class BigPlane extends Fu implements Award{
    private int ySpeed = 2;
    private int awardType = (int)(Math.random()*5);

    public BigPlane() {
        super((int)(Math.random()*(Window.WIDTH - Window.bigPlane.getWidth())), -Window.bigPlane.getHeight(), Window.bigPlane);
        this.setBlood(4);
        this.setScore(5);
    }

    public int getAwardType() {
        return awardType;
    }

    public void setAwardType(int awardType) {
        this.awardType = awardType;
    }

    @Override
    public void move() {
        this.setY(this.getY() + ySpeed);
    }

    @Override
    public int awardType() {
        return awardType;
    }
    public void breakFly(){
        setImg(Window.bigPlane_ember0);
    }
}
