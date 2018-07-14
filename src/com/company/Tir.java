package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Tir implements Serializable{
    private int locX, locY;
    private double delytaY, deltaX;
    private int tankDeltaX,tankDeltaY;
    private  int firstTankX,firstTankY;
    private int speed;
    private double shib;
    transient BufferedImage moshak;
    private boolean die=false;
    private double i=0;
    private transient Tank sorce;
    private int firstX,firstY;
    protected Rectangle rectangle;
    private String kind;
    private String tankSorce;
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib) {
        try {
            moshak = ImageIO.read(new File("Src//bom11.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX,locY,moshak.getWidth(),moshak.getHeight());
        this.locX = locX-moshak.getWidth()/2;
        this.locY = locY-moshak.getHeight()/2;
        firstX=this.locX;
        firstY=this.locY;
        tankDeltaX=0;
        tankDeltaY=0;
        this.deltaX = deltaX;
        this.delytaY = deltaY;
        this. deltaX =this.deltaX/Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        this.delytaY =this.delytaY/Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        this.shib = shib;

    }
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib, Tank sorce){
        this(locX,locY,deltaY,deltaX,shib);
        this.sorce=sorce;
        if(sorce!=null)
        sorce.minus();
        ////
        if(sorce instanceof TankHuman) {
            TankHuman temp = (TankHuman)sorce;
            speed = temp.getTirSpeed();
        }
        else
            speed=20;
        ////////
    }
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib,Tank sorce,int firstTankX,int firstTankY){
        this(locX,locY,deltaY,deltaX,shib,sorce);
        this.firstTankX = firstTankX;
        this.firstTankY = firstTankY;
        kind="heavy";

    }
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib,Tank sorce,int firstTankX,int firstTankY,String kind){
        this(locX,locY,deltaY,deltaX,shib,sorce);
        this.firstTankX = firstTankX;
        this.firstTankY = firstTankY;
        this.kind = kind;
    }

    public void setTankSorce(String tankSorce) {
        this.tankSorce = tankSorce;
    }

    public String getTankSorce() {
        return tankSorce;
    }

    public double getDelytaY() {
        return delytaY;
    }

    public void setDelytaY(double delytaY) {
        this.delytaY = delytaY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public void setFirstTankX(int firstTankX) {
        this.firstTankX = firstTankX;
    }

    public void setFirstTankY(int firstTankY) {
        this.firstTankY = firstTankY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setShib(double shib) {
        this.shib = shib;
    }

    public void setMoshak(BufferedImage moshak) {
        this.moshak = moshak;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setSorce(Tank sorce) {
        this.sorce = sorce;
    }

    public void setFirstX(int firstX) {
        this.firstX = firstX;
    }

    public void setFirstY(int firstY) {
        this.firstY = firstY;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Tank getSorce() {
        return sorce;
    }

    public int getFirstTankY() {
        return firstTankY;
    }

    public int getFirstTankX() {
        return firstTankX;
    }

    public void update() {
        i++;
        firstX += deltaX*speed;
        firstY += delytaY*speed;
//        if(i%50 == 0) {
//            System.out.println("loc x :" + locX + "   deltax" + deltaX);
//            System.out.println("loc y :" + locY + "   deltay" + delytaY);
//            System.out.println("............................");
//        }
        if(i>20000){
            die=true;
        }

    }

    public void setTankDeltaX(int tankDeltaX) {
        this.tankDeltaX = tankDeltaX;
    }

    public void setTankDeltaY(int tankDeltaY) {
        this.tankDeltaY = tankDeltaY;
    }

    public int getTankDeltaX() {
        return tankDeltaX;
    }

    public int getTankDeltaY() {
        return tankDeltaY;
    }

    public int getFirstY() {
        return firstY;
    }

    public int getFirstX() {
        return firstX;
    }

    public boolean isDie() {
        return die;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public BufferedImage getMoshak() {
        return moshak;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }

    public double getShib() {
        return shib;
    }
}
