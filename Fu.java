package com.plane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fu {
    private int blood;
    private int x;
    private int y;
    private BufferedImage img;
    private int width;
    private int height;
    private int score;
    int time = 0;

    public Fu() {
    }

    public Fu(int x, int y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void move() {
    }
    public void breakFly(){
    }
}
