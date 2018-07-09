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
    private final int firstX,firstY;
    private BufferedImage moshak,moshak2,moshak3;
    private boolean die;
    private String kind;
    public Wall(int locX,int locY,String kind){
        this.locX = locX;
        this.locY = locY;
        this.kind = kind;
        health = 3;
        try {
            if(kind.equals("softWall")) {
                moshak = ImageIO.read(new File("Src//softWall.png"));
                moshak2 = ImageIO.read(new File("Src//softWall2.png"));
                moshak3 = ImageIO.read(new File("Src//softWall3.png"));
            }
            if(kind.equals("hardWall"))
                moshak = ImageIO.read(new File("Src//hardWall.png"));
            if(kind.equals("teazel"))
                moshak = ImageIO.read(new File("Src//teazel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        firstX=locX;
        firstY=locY;
    }

    public String getKind() {
        return kind;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFirstY() {
        return firstY;
    }
    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health==2)
            moshak = moshak2;
        if(health == 1)
            moshak = moshak3;
        if(health<=0)
            die=true;
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

