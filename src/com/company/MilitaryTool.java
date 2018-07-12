package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MilitaryTool {
    protected int locX, locY;
    public int firstX,firstY;
    protected int tirRange;
    protected int range;
    protected int health;
    protected boolean dead;
    protected int count=0;
    protected BufferedImage tank;
    protected Rectangle rectangle;
    public MilitaryTool(int locX,int locY){
        this.locX = locX;
        this.locY = locY;
        firstX=locX;
        firstY=locY;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public BufferedImage getTank() {
        return tank;
    }

    public int getCount() {
        return count;
    }

    public int getHealth() {
        return health;
    }

    public MilitaryTool(int locX, int locY, int range){
        this(locX,locY);
        this.range = range;
    }
    public int getLocX() {
        return locX;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFirstY() {
        return firstY;
    }

    public boolean isDead() {
        return dead;
    }
    public void update(){
    }
    public void setHealth(int health) {
        this.health = health;
        if(health <= 0)
            dead=true;
    }
    public void update(ArrayList<Wall> walls){
        count++;
    }
    public void update(ArrayList<Wall> walls,ArrayList<Tank>tanks){
       update(walls);
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getRange() {
        return range;
    }
    public void update(TankHuman tankHuman){

    }
    public int getLocY() {
        return locY;
    }

}
