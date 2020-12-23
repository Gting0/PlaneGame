package com.plane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Window extends JPanel{
    public static BufferedImage airplane;
    public static BufferedImage airplane_ember0;
    public static BufferedImage airplane_ember1;
    public static BufferedImage airplane_ember2;
    public static BufferedImage airplane_ember3;
    public static BufferedImage background;
    public static BufferedImage bee;
    public static BufferedImage bee_ember0;
    public static BufferedImage bee_ember1;
    public static BufferedImage bee_ember2;
    public static BufferedImage bee_ember3;
    public static BufferedImage bigPlane;
    public static BufferedImage bigPlane_ember0;
    public static BufferedImage bigPlane_ember1;
    public static BufferedImage bigPlane_ember2;
    public static BufferedImage bigPlane_ember3;
    public static BufferedImage bullet;
    public static BufferedImage hero_ember0;
    public static BufferedImage hero_ember1;
    public static BufferedImage hero_ember2;
    public static BufferedImage hero_ember3;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage gameover;
    public static int WIDTH = 600;
    public static int HEIGHT = 750;
    private Timer timer = new Timer();
    Hero hero = new Hero();

    static {
        try {
            airplane = ImageIO.read(Window.class.getResourceAsStream("img/airplane.png"));
            airplane_ember0 = ImageIO.read(Window.class.getResourceAsStream("img/airplane_ember0.png"));
            airplane_ember1 = ImageIO.read(Window.class.getResourceAsStream("img/airplane_ember1.png"));
            airplane_ember2 = ImageIO.read(Window.class.getResourceAsStream("img/airplane_ember2.png"));
            airplane_ember3 = ImageIO.read(Window.class.getResourceAsStream("img/airplane_ember3.png"));
            background = ImageIO.read(Window.class.getResourceAsStream("img/background.png"));
            bee = ImageIO.read(Window.class.getResourceAsStream("img/bee.png"));
            bee_ember0 = ImageIO.read(Window.class.getResourceAsStream("img/bee_ember0.png"));
            bee_ember1 = ImageIO.read(Window.class.getResourceAsStream("img/bee_ember1.png"));
            bee_ember2 = ImageIO.read(Window.class.getResourceAsStream("img/bee_ember2.png"));
            bee_ember3 = ImageIO.read(Window.class.getResourceAsStream("img/bee_ember3.png"));
            bigPlane = ImageIO.read(Window.class.getResourceAsStream("img/bigplane.png"));
            bigPlane_ember0 = ImageIO.read(Window.class.getResourceAsStream("img/bigplane_ember0.png"));
            bigPlane_ember1 = ImageIO.read(Window.class.getResourceAsStream("img/bigplane_ember1.png"));
            bigPlane_ember2 = ImageIO.read(Window.class.getResourceAsStream("img/bigplane_ember2.png"));
            bigPlane_ember3 = ImageIO.read(Window.class.getResourceAsStream("img/bigplane_ember3.png"));
            bullet = ImageIO.read(Window.class.getResourceAsStream("img/bullet.png"));
            hero_ember0 = ImageIO.read(Window.class.getResourceAsStream("img/hero_ember0.png"));
            hero_ember1 = ImageIO.read(Window.class.getResourceAsStream("img/hero_ember1.png"));
            hero_ember2 = ImageIO.read(Window.class.getResourceAsStream("img/hero_ember2.png"));
            hero_ember3 = ImageIO.read(Window.class.getResourceAsStream("img/hero_ember3.png"));
            hero0 = ImageIO.read(Window.class.getResourceAsStream("img/hero0.png"));
            hero1 = ImageIO.read(Window.class.getResourceAsStream("img/hero1.png"));
            pause = ImageIO.read(Window.class.getResourceAsStream("img/pause.png"));
            start = ImageIO.read(Window.class.getResourceAsStream("img/start.png"));
            gameover = ImageIO.read(Window.class.getResourceAsStream("img/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ArrayList<Fu> fus = new ArrayList();
    ArrayList<Bullet> bullets1 = new ArrayList();
    ArrayList<Bullet> bullets2 = new ArrayList();
    int fuFlag = 0;
    int bulletFlag = 0;
    final int START = 0;
    final int RUNNING = 1;
    final int PAUSE = 2;
    final int GAMEOVER = 3;
    int state = START;


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        JPanel jPanel = new Window();
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        ((Window) jPanel).start();
    }
    private void start(){
        timer.schedule(new TimerTask() {
            public void run() {
                if (state == RUNNING) {
                    creatFus();
                    creatBullets();
                    fusMove();
                    bulletsMove();
                    removeFus();
                    removeBullets();
                    crash();
                    hero.move();
                    heroCrash();
                    repaint();
                }
            }
        },1000,20);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            //移动
            public void mouseMoved(MouseEvent e) {
                if (state == RUNNING) {
                    super.mouseMoved(e);
                    hero.setX(e.getX() - (hero.getImg().getWidth() / 2));
                    hero.setY(e.getY() - (hero.getImg().getHeight() / 2));
                    repaint();
                }
            }

            @Override
            //退出
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (state == RUNNING) {
                    state = PAUSE;
                }
                repaint();
            }

            @Override
            //进入
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (state == PAUSE) {
                    state = RUNNING;
                }
                repaint();
            }

            @Override
            //点击
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (state == START) {
                    state = RUNNING;
                }
                repaint();
            }
        };
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }

    private void fusMove() {
        for (int i = 0; i < fus.size(); i++) {
            Fu fu = fus.get(i);
            fu.move();
            if (fu.getBlood() <= 0){
                fu.time++;
                if (fu.time == 3) {
                    fu.breakFly();
                }
                if (fu.time == 6){
                    fus.remove(i);
                    i--;
                }
            }
        }
    }
    private void bulletsMove(){
        for (int i = 0; i < bullets1.size(); i++) {
            Bullet bullet = bullets1.get(i);
            bullet.move();
        }
        for (int i = 0; i < bullets2.size(); i++) {
            Bullet bullet = bullets2.get(i);
            bullet.move();
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
        g.drawImage(hero.getImg(), hero.getX(), hero.getY(), this);
        paintFus(g);
        paintBullets(g);
        String score = String.valueOf(hero.getScore());
        String life = String.valueOf(hero.getLife());
        g.drawString("得分："+score,20,30);
        g.drawString("生命："+life,20,60);
        if (state == START) {
            g.drawImage(start,0,0,WIDTH,HEIGHT,this);
        }
        if (state == PAUSE) {
            g.drawImage(pause,0,0,WIDTH,HEIGHT,this);
        }
        if (state == GAMEOVER){
            g.drawImage(gameover,0,0,WIDTH,HEIGHT,this);
        }
    }
    private void paintFus(Graphics g) {
        for (int i = 0; i < fus.size(); i++) {
            Fu fu = fus.get(i);
            g.drawImage(fu.getImg(),fu.getX(),fu.getY(),this);
        }
    }
    private void paintBullets(Graphics g){
        for (int i = 0; i < bullets1.size(); i++) {
            Bullet bullet = bullets1.get(i);
            g.drawImage(bullet.getImg(),bullet.getX(),bullet.getY(),this);
        }
        for (int i = 0; i < bullets2.size(); i++) {
            Bullet bullet = bullets2.get(i);
            g.drawImage(bullet.getImg(),bullet.getX(),bullet.getY(),this);
        }
    }
    private void removeFus(){
        for (int i = 0; i < fus.size(); i++) {
            Fu fu = fus.get(i);
            if (fu.getY() >= HEIGHT){
                fus.remove(i);
                i--;
            }
        }
    }
    private void removeBullets(){
        for (int i = 0; i < bullets1.size(); i++) {
            Bullet bullet = bullets1.get(i);
            if (bullet.getY() <= 0){
                bullets1.remove(i);
                i--;
            }
        }
        for (int i = 0; i < bullets2.size(); i++) {
            Bullet bullet = bullets2.get(i);
            if (bullet.getY() <= 0){
                bullets2.remove(i);
                i--;
            }
        }
    }

    private void creatFus(){
        fuFlag++;
        if (fuFlag % 15 == 0) {
            int index = (int) (Math.random() * 10);
            if (index == 0) {
                fus.add(new Bee());
            } else if (index == 1) {
                fus.add(new BigPlane());
            } else {
                fus.add(new Airplane());
            }
        }
    }
    private void creatBullets(){
        bulletFlag++;
        if (hero.getQuantity() == 1) {
            if (bulletFlag % 5 == 0) {
                bullets1.add(new Bullet(hero.getX() + (hero.getImg().getWidth() / 2) - 3, hero.getY() - 5));
            }
        }
        if (hero.getQuantity() >= 2){
            if (bulletFlag % 5 == 0) {
                bullets1.add(new Bullet(hero.getX() + 12, hero.getY() - 5));
                bullets2.add(new Bullet(hero.getX() + hero.getImg().getWidth() - 21,hero.getY()-5));
            }
        }
    }
    public void crash(){
        for (int j = 0; j < bullets1.size(); j++) {
            for (int i = 0; i < fus.size(); i++) {
                int x0 = bullets1.get(j).getX();
                int x1 = bullets1.get(j).getX() + bullets1.get(j).getImg().getWidth();
                int y0 = bullets1.get(j).getY();
                if (x0 > fus.get(i).getX() && x0 < fus.get(i).getX() + fus.get(i).getImg().getWidth() && y0 <= fus.get(i).getY() + fus.get(i).getImg().getHeight() && y0 >= fus.get(i).getY()
                    && x1 > fus.get(i).getX() && x1 < fus.get(i).getX() + fus.get(i).getImg().getWidth()){
                    fus.get(i).setBlood(fus.get(i).getBlood() - 1);
                    if (fus.get(i).getBlood() <= 0) {
                        hero.setScore(hero.getScore() + fus.get(i).getScore());
                        if (fus.get(i) instanceof BigPlane){
                            BigPlane big = (BigPlane) (fus.get(i));
                            hero.quantities(big.getAwardType());
                        }
                        if (fus.get(i) instanceof Bee){
                            Bee bee = (Bee) (fus.get(i));
                            hero.quantities(bee.getAwardType());
                        }
                    }
                    bullets1.remove(j);
                    j--;
                    break;
                }
            }
        }
        for (int j = 0; j < bullets2.size(); j++) {
            for (int i = 0; i < fus.size(); i++) {
                int x0 = bullets2.get(j).getX();
                int x1 = bullets2.get(j).getX() + bullets2.get(j).getImg().getWidth();
                int y0 = bullets2.get(j).getY();
                if (x0 > fus.get(i).getX() && x0 < fus.get(i).getX() + fus.get(i).getImg().getWidth() && y0 <= fus.get(i).getY() + fus.get(i).getImg().getHeight() && y0 >= fus.get(i).getY()
                        && x1 > fus.get(i).getX() && x1 < fus.get(i).getX() + fus.get(i).getImg().getWidth()){
                    fus.get(i).setBlood(fus.get(i).getBlood() - 1);
                    if (fus.get(i).getBlood() <= 0) {
                        hero.setScore(hero.getScore() + fus.get(i).getScore());
                        if (fus.get(i) instanceof BigPlane){
                            BigPlane big = (BigPlane) (fus.get(i));
                            hero.quantities(big.getAwardType());
                        }
                        if (fus.get(i) instanceof Bee){
                            Bee bee = (Bee) (fus.get(i));
                            hero.quantities(bee.getAwardType());
                        }
                    }
                    bullets2.remove(j);
                    j--;
                    break;
                }
            }
        }
    }
    private void heroCrash(){
        for (int i = 0; i < fus.size(); i++) {
            int x0 = hero.getX();
            int x1 = hero.getX() + hero.getImg().getWidth();
            int y0 = hero.getY();
            int y1 = hero.getY() + hero.getImg().getHeight();
            if((x0 >= fus.get(i).getX() &&
              x0 <= fus.get(i).getX() + fus.get(i).getImg().getWidth() &&
              y0 <= fus.get(i).getY() + fus.get(i).getImg().getHeight() &&
              y0 >= fus.get(i).getY()) ||
              (x1 >= fus.get(i).getX() &&
              x1 <= fus.get(i).getX() + fus.get(i).getImg().getWidth() &&
              y1 <= fus.get(i).getY() + fus.get(i).getImg().getHeight() &&
              y1 >= fus.get(i).getY()) ||
              (x0 >= fus.get(i).getX() &&
              x0 <= fus.get(i).getX() + fus.get(i).getImg().getWidth() &&
              y1 <= fus.get(i).getY() + fus.get(i).getImg().getHeight() &&
              y1 >= fus.get(i).getY()) ||
              (x1 >= fus.get(i).getX() &&
              x1 <= fus.get(i).getX() + fus.get(i).getImg().getWidth() &&
              y0 <= fus.get(i).getY() + fus.get(i).getImg().getHeight() &&
              y0 >= fus.get(i).getY()) ||
              (fus.get(i).getX() >= x0 &&
              fus.get(i).getX() <= x1 &&
              fus.get(i).getY() <= y1 &&
              fus.get(i).getY() >= y0) ||
              (fus.get(i).getX() >= x0 &&
              fus.get(i).getX() <= x1 &&
              fus.get(i).getY() + fus.get(i).getImg().getHeight() <= y1 &&
              fus.get(i).getY() + fus.get(i).getImg().getHeight() >= y0) ||
              (fus.get(i).getX() + fus.get(i).getImg().getWidth() >= x0 &&
              fus.get(i).getX() + fus.get(i).getImg().getWidth() <= x1 &&
              fus.get(i).getY() <= y1 &&
              fus.get(i).getY() >= y0) ||
              (fus.get(i).getX() + fus.get(i).getImg().getWidth() >= x0 &&
              fus.get(i).getX() + fus.get(i).getImg().getWidth() <= x1 &&
              fus.get(i).getY() + fus.get(i).getImg().getHeight() <= y1 &&
              fus.get(i).getY() + fus.get(i).getImg().getHeight() >= y0)){
                if (!(fus.get(i) instanceof Bee)) {
                    hero.setLife(hero.getLife() - 1);
                }
                if (hero.getLife() <= 0){
                    state = GAMEOVER;
                }
                fus.remove(i);
            }
        }
    }
    private void breakPicture(Fu fu){
        if (fu instanceof Airplane){
            if (hero.getCount() % 5 ==0) {
                fu.setImg(airplane_ember0);
            }

        }
    }
}
