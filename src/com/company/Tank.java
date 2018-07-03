package com.company;


import javafx.stage.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private int health;
    public int locX, locY;
    public int locXLooleh,locYLooleh;
    public boolean gameOver;

    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mousePress,mouseRealised;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private BufferedImage tank,looleh;
    private Action action;
    private int tir;
    private int moshak;
    public Tank(){

        moshak = 50;
        tir = 200;
//        BufferedImage in = ImageIO.read(img);
        try {
            tank = ImageIO.read(new File("Src//icon.png"));
            looleh = ImageIO.read(new File("Src//looleh.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        tank = Toolkit.getDefaultToolkit().getImage("Src//icon.png");
        health = 3;
        locX = 200;
        locY = 200;
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

    public void setHealth(int health) {
        this.health = health;
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

    public void update() {

        if (keyUP)
            locY -= 8;
        if (keyDOWN)
            locY += 8;
        if (keyLEFT)
            locX -= 8;
        if (keyRIGHT)
            locX += 8;

        locX = Math.max(locX, 0);
        locX = Math.min(locX, GameFrame.GAME_WIDTH );
        locY = Math.max(locY, 0);
        locY = Math.min(locY, GameFrame.GAME_HEIGHT);
        mouseX = MouseInfo.getPointerInfo().getLocation().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y;
        System.out.println(mouseX);
    }

    class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
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
