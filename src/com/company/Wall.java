package com.company;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall {
    private int health;
    private int locX;
    private int locY;
    private BufferedImage moshak;
    private boolean die;
    public Wall(){
        health = 3;
        die = false;
        try {
            moshak = ImageIO.read(new File("Src//softWall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Wall(int locX,int locY){
        this.locX = locX;
        this.locY = locY;
        health = 3;
        try {
            moshak = ImageIO.read(new File("Src//softWall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }
    public void setMoshak(BufferedImage moshak) {
        this.moshak = moshak;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public int getHealth() {
        return health;
    }

    public BufferedImage getMoshak() {
        return moshak;
    }
}

