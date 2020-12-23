package com.plane;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Hero extends Fu{
   private int life = 3;
   private int count = 0;
   private int quantity = 1;
   private ArrayList<Bullet> bullets;
   private int quantityTime = 0;


    public Hero() {
        super(200, 500, Window.hero0);
        this.setScore(0);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void quantities(int type){
        if (type == 0){
            quantity += 1;
        }
        if (type == 1){
            life += 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return life == hero.life &&
                count == hero.count &&
                quantity == hero.quantity &&
                quantityTime == hero.quantityTime &&
                Objects.equals(bullets, hero.bullets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(life, count, quantity, bullets, quantityTime);
    }

    public void move() {
        quantityTime++;
        if (quantityTime % 500 == 0 && quantity > 1){
            quantity -= 1;
        }
        if (count % 5 == 0){
            this.setImg(Window.hero0);
            count++;
        }else{
            this.setImg(Window.hero1);
            count++;
        }

    }
}
