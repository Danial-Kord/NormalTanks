package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tir {
    private int locX, locY;
    private double delytaY, deltaX;
    private int tankDeltaX,tankDeltaY;
    private  int firstTankX,firstTankY;
    private double shib;
    BufferedImage moshak;
    private boolean die=false;
    private double i=0;
    private Tank sorce;
    private int firstX,firstY;
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib) {
        try {
            moshak = ImageIO.read(new File("Src//bom11.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.locX = locX-moshak.getWidth()/2;
        this.locY = locY-moshak.getHeight()/2;
        firstX=this.locX;
        firstY=this.locY;
        tankDeltaX=0;
        tankDeltaY=0;
        this.deltaX = deltaX;
        this.delytaY = deltaY;
        this. deltaX =this.deltaX/Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2))*5;
        this.delytaY =this.delytaY/Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2))*5;
        this.shib = shib;

    }
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib,Tank sorce){
        this(locX,locY,deltaY,deltaX,shib);
        this.sorce=sorce;
        sorce.minusBullet();
    }
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib,Tank sorce,int firstTankX,int firstTankY){
        this(locX,locY,deltaY,deltaX,shib,sorce);
        this.firstTankX = firstTankX;
        this.firstTankY = firstTankY;

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
        firstX += deltaX;
        firstY += delytaY;
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
