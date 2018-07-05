package com.company;


import javafx.stage.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Tank {
    private int health;
    public int locX, locY;
    public int firstX,firstY;
    public int locXLooleh,locYLooleh;
    public boolean gameOver;
    private int deltaX,deltaY;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mousePress,mouseRealised;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private BufferedImage tank,looleh;
    private Action action;
    private int tir;
    private int moshak;
    private boolean stop;
    private final boolean enemy;
    private int range;
    private boolean dead;
    private int count=0;
    private boolean up,down,left,right;
    public Tank(boolean enemy,int locX,int locY){
        this.enemy = enemy;
        stop = false;
        dead=false;
        moshak = 50;
        right=false;
        left=false;
        up=false;
        down=false;
        tir = 200;
        deltaX=0;
        deltaY=0;
//        BufferedImage in = ImageIO.read(img);
        try {
            tank = ImageIO.read(new File("Src//icon.png"));
            looleh = ImageIO.read(new File("Src//looleh.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        tank = Toolkit.getDefaultToolkit().getImage("Src//icon.png");
        health = 3;
        this.locX = locX;
        this.locY = locY;
        firstX=locX;
        firstY=locY;
        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        action = new Action();
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        locXLooleh = locX;
        locYLooleh = locY;
    }
    public Tank(boolean enemy,int locX,int locY,int range){
        this(enemy,locX,locY);
        setRange(range);
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health <= 0)
            dead=true;
        if(health <= 0 && !enemy)
            gameOver=true;
    }

    public boolean isDead() {
        return dead;
    }

    public BufferedImage getLooleh() {
        return looleh;
    }

    public BufferedImage getTank() {
        return tank;
    }

    public int getLocX() {
        return locX;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int getLocY() {
        return locY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setLocXLooleh(int locXLooleh) {
        this.locXLooleh = locXLooleh;
    }

    public void setLocYLooleh(int locYLooleh) {
        this.locYLooleh = locYLooleh;
    }

    public int getLocXLooleh() {
        return locXLooleh;
    }

    public int getLocYLooleh() {
        return locYLooleh;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isKeyDOWN() {
        return keyDOWN;
    }

    public boolean isKeyLEFT() {
        return keyLEFT;
    }

    public boolean isKeyRIGHT() {
        return keyRIGHT;
    }

    public boolean isKeyUP() {
        return keyUP;
    }

    public boolean isMousePress() {
        return mousePress;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getHealth() {
        return health;
    }

    public KeyListener getKeyListener() {
        return keyHandler;
    }
    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }

    public void setMouseRealised(boolean mouseRealised) {
        this.mouseRealised = mouseRealised;
    }

    public boolean isMouseRealised() {
        return mouseRealised;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void update() {
//
//        if(up && keyDOWN){
//            locY-=10;
//            deltaY+=10;
//        }
//
//        if(down && keyUP){
//            locY+= 10;
//            deltaY+=10;
//        }
//        if(right && keyLEFT){
//            locX+= 10;
//            deltaX+=10;
//        }
//        if(left && keyRIGHT){
//            locX-= 10;
//            deltaX+=-10;
//        }
        Random random = new Random();
        if(enemy){
            count++;
            if(count%20==0) {
                keyLEFT = false;
                keyRIGHT = false;
                keyDOWN = false;
                keyUP = false;
                keyUP = random.nextBoolean();
                if (!keyUP)
                    keyDOWN = random.nextBoolean();
                keyRIGHT = random.nextBoolean();
                if (!keyRIGHT)
                    keyLEFT = random.nextBoolean();
            }
            if(keyUP && !down){
                if(Math.pow(locX-firstX,2) + Math.pow(locY-8-firstY,2) <= Math.pow(range,2))
                    locY -= 8;
            }
            if(keyDOWN && !up){
                if(Math.pow(locX-firstX,2) + Math.pow(locY+8-firstY,2) <= Math.pow(range,2))
                    locY += 8;
            }
            if(keyRIGHT && !left){
                if(Math.pow(locX+8-firstX,2) + Math.pow(locY-firstY,2) <= Math.pow(range,2))
                    locX += 8;
            }
            if(keyLEFT && !right){
                if(Math.pow(locX-8-firstX,2) + Math.pow(locY-firstY,2) <= Math.pow(range,2))
                    locX -= 8;
            }
            locX = Math.max(locX, 0);
            locX = Math.min(locX, GameFrame.GAME_WIDTH );
            locY = Math.max(locY, 0);
            locY = Math.min(locY, GameFrame.GAME_HEIGHT);
            up=false;
            down=false;
            left=false;
            right=false;
            return;
        }
        if (keyUP && !down) {
            locY -= 8;
            deltaY+=-8;
        }
        if (keyDOWN && !up) {
            locY += 8;
            deltaY+=8;
        }
        if (keyLEFT && !right) {
            locX -= 8;
            deltaX+=-8;
        }
        if (keyRIGHT && !left) {
            locX += 8;
            deltaX+=8;
        }
        if(!keyDOWN || !keyUP)
            deltaY=0;
        if(!keyLEFT || !keyRIGHT)
            deltaX=0;
        locX = Math.max(locX, 0);
        locX = Math.min(locX, GameFrame.GAME_WIDTH );
        locY = Math.max(locY, 0);
        locY = Math.min(locY, GameFrame.GAME_HEIGHT);
        mouseX = MouseInfo.getPointerInfo().getLocation().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y;
        up=false;
        down=false;
        left=false;
        right=false;
    }

    public int getCount() {
        return count;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(enemy)
                return;
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gameOver = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(enemy)
                return;
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;

            }
        }

    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            mousePress = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
            mouseRealised = true;
            mouseX = e.getX();
            mouseY = e.getY();
            System.out.println(mouseX + "....");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            System.out.println(mouseX);
            System.out.println(mouseY);
        }
    }
    private class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("saf");
        }
    }
}
