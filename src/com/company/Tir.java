package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tir {
    private int locX, locY;
    private double delytaY, deltaX;
    private double shib;
    BufferedImage moshak;
    private boolean die=false;
    private double i=0;
    private Tank sorce;
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib) {
        this.locX = locX;
        this.locY = locY;
        this.deltaX = deltaX;
        this.delytaY = deltaY;

        this.shib = shib;

        try {
            moshak = ImageIO.read(new File("Src//bom11.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Tir(int locX, int locY, double deltaY, double deltaX, double shib,Tank sorce){
        this(locX,locY,deltaY,deltaX,shib);
        this.sorce=sorce;
    }

    public Tank getSorce() {
        return sorce;
    }

    public void update() {
        i++;
        locX += deltaX / 40;
        locY += delytaY / 40;
        if(i%50 == 0) {
//            System.out.println("loc x :" + locX + "   deltax" + deltaX);
//            System.out.println("loc y :" + locY + "   deltay" + delytaY);
//            System.out.println("............................");
        }
        if(i>20000){
            die=true;
        }

    }

    public boolean isDie() {
        return die;
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
