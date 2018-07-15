package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * save all of the military information in game
 */
public class MilitaryTool implements Serializable{

    protected int firstX,firstY;
    protected int health;
    protected boolean dead;
    protected int count=0;
    protected double rotate;
    protected double lastRotate;
    protected String name;

    protected int locX, locY;
    protected int tirRange;
    protected int range;
    protected transient BufferedImage tank;
    protected  Rectangle rectangle;
    protected String kind;

    public MilitaryTool(int locX,int locY){
        this.locX = locX;
        this.locY = locY;
        firstX=locX;
        firstY=locY;
    }

    public void setLastRotate(double lastRotate) {
        this.lastRotate = lastRotate;
    }

    public double getLastRotate() {
        return lastRotate;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFirstX(int firstX) {
        this.firstX = firstX;
    }

    public void setFirstY(int firstY) {
        this.firstY = firstY;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public int getTirRange() {
        return tirRange;
    }

    public void setTirRange(int tirRange) {
        this.tirRange = tirRange;
    }

    public void setTank(BufferedImage tank) {
        this.tank = tank;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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
