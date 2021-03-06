package com.company;


import javafx.stage.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * father of all tanks
 */
public class Tank extends MilitaryTool implements Serializable{
    private int numberBullet=20;
    private int numberCheapBullet=5;
    public boolean stateHeavyGun=true;

    private int locXLooleh,locYLooleh;
    protected int mouseX, mouseY;
    protected transient BufferedImage looleh;
    protected boolean up,down,left,right;
    protected int speed;



    public Tank(int locX, int locY){
        super(locX,locY);
        rectangle= new Rectangle(locX,locY,200,200);
        dead=false;
        right=false;
        left=false;
        up=false;
        down=false;

//        BufferedImage in = ImageIO.read(img);

//        tank = Toolkit.getDefaultToolkit().getImage("Src//icon.png");
        health = 500;
        //
        //
        mouseX = 0;
        mouseY = 0;
        //
        locXLooleh = locX;
        locYLooleh = locY;
    }
    public Tank(int locX,int locY,int range){
        super(locX,locY,range);

    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void update(ArrayList<Wall> walls, ArrayList<Tank>tanks){
        count++;
    }

    public int getNumberBullet() {
        return numberBullet;
    }

    public void setNumberBullet(int numberBullet) {
        this.numberBullet = numberBullet;
    }

    public void setNumberCheapBullet(int numberCheapBullet) {
        this.numberCheapBullet = numberCheapBullet;
    }

    public boolean isStateHeavyGun() {
        return stateHeavyGun;
    }

    public void setStateHeavyGun(boolean stateHeavyGun) {
        this.stateHeavyGun = stateHeavyGun;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public void setLooleh(BufferedImage looleh) {
        this.looleh = looleh;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
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

    @Override
    public void update() {
        count++;
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

    public void plusBullet(int number){
        numberBullet+=number;
    }
    public void minus(){
        if(stateHeavyGun)
            minusBullet();
        else
            minusCheapBullet();
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
