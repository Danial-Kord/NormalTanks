package com.company;


import javafx.stage.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Tank {
    private int numberBullet=20;
    private int numberCheapBullet=5;
    public boolean stateHeavyGun=true;
    private int health;
    protected int locX, locY;
    public int firstX,firstY;
    protected int tirRange;
    public int locXLooleh,locYLooleh;


    protected int deltaX,deltaY;
    protected int mouseX, mouseY;

    protected BufferedImage tank,looleh;

    protected int range;
    protected boolean dead;
    protected int count=0;
    protected boolean up,down,left,right;
    public Tank(int locX,int locY){

        dead=false;
        right=false;
        left=false;
        up=false;
        down=false;
        deltaX=0;
        deltaY=0;
//        BufferedImage in = ImageIO.read(img);

//        tank = Toolkit.getDefaultToolkit().getImage("Src//icon.png");
        health = 3;
        this.locX = locX;
        this.locY = locY;
        firstX=locX;
        firstY=locY;
        //
        //
        mouseX = 0;
        mouseY = 0;
        //

        locXLooleh = locX;
        locYLooleh = locY;
    }
    public Tank(int locX,int locY,int range){
        this(locX,locY);
        setRange(range);
    }

    public void setRange(int range) {
        this.range = range;
    }
    public void update(ArrayList<Wall> walls, ArrayList<Tank>tanks){
        count++;
    }
    public int getRange() {
        return range;
    }


    public void setHealth(int health) {
        this.health = health;
        if(health <= 0)
            dead=true;
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


    public String getTankCheapBullet(){
        return ""+numberCheapBullet;
    }
    public String getTankBulletNumber(){
        return ""+numberBullet;
    }





    public int getMouseY() {
        return mouseY;
    }

    public int getHealth() {
        return health;
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


    public int getCount() {
        return count;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
    public void plusBullet(int number){
        numberBullet+=number;
    }
    public void minusBullet(){
        if(numberBullet>0)
        numberBullet--;
    }
    public int getBullet(){

        return numberBullet;
    }
    public void plusCheapBullet(int number){
        numberCheapBullet+=number;

    }
    public void minusCheapBullet(){
        if(numberCheapBullet>0)
        numberCheapBullet--;
    }
    public int getNumberCheapBullet(){
        return numberCheapBullet;
    }

}
