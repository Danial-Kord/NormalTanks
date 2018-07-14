package com.company;

import java.awt.*;
import java.io.Serializable;

public class Building implements Serializable{
    protected int locX;
    protected int locY;
    protected String path;
    protected int health;
    protected boolean die;
    protected final int firstX,firstY;
    public Building(int locX,int locY){
        this.locX = locX;
        this.locY = locY;
        firstX=locX;
        firstY=locY;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health<=0)
            die=true;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public boolean isDie() {
        return die;
    }

    public int getHealth() {
        return health;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFirstY() {
        return firstY;
    }
    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

}
