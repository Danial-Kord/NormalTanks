package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * tanks have intelligence
 */
public class TankAi extends MovableTank implements Serializable{

    private int  rangeX,rangeY;
    public TankAi(int locX, int locY,int range,int tirRange) {
        super(locX, locY,range);
        rangeX=range;
        rangeY=range;
        this.tirRange = tirRange;
    }
    public TankAi(int locX, int locY,int rangeX,int rangeY,int tirRange) {
        super(locX, locY);
        this.rangeX=rangeX;
        this.rangeY=rangeY;
        this.tirRange = tirRange;
        kind = "TankAi";
        health = 300;
        try {
            tank = ImageIO.read(new File("src\\Images\\BigEnemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);

    }

    public int getRangeX() {
        return rangeX;
    }

    public int getRangeY() {
        return rangeY;
    }

    public void update(ArrayList<Wall> walls) {
    super.update(walls);
//        if(up && keyDOWN){
//            locY-=1;
//            deltaY+=-8;
//        }
//
//        if(down && keyUP){
//            locY+= 1;
//            deltaY+=8;
//        }
//        if(right && keyLEFT){
//            locX+= 1;
//            deltaX+=8;
//        }
//        if(left && keyRIGHT){
//            locX-= 1;
//            deltaX+=-8;
//        }
        Random random = new Random();

            if(count%20==0) {
                keyLEFT = false;
                keyRIGHT = false;
                keyDOWN = false;
                keyUP = false;
                keyUP = random.nextBoolean();
                if (!keyUP)
                    keyDOWN = random.nextBoolean();
                keyRIGHT = random.nextBoolean();
                if (!keyRIGHT)
                    keyLEFT = random.nextBoolean();
            }
                if (keyUP) {
                    if (Math.abs(deltaX)<=rangeX && Math.abs(deltaY-8)<=rangeY) {
                        firstY -= 8;
                        deltaY-=8;
                    }
                }

            if(keyDOWN){
                if(Math.abs(deltaX)<=rangeX && Math.abs(deltaY-8)<=rangeY) {
                    GameState.setChanged(true);
                    firstY -= 8;
                    deltaY -= 8;
                }
            }
            if(keyRIGHT ){
                if(Math.abs(deltaX+8)<=rangeX && Math.abs(deltaY)<=rangeY) {

                    GameState.setChanged(true);
                    firstX +=8;
                    deltaX+=8;
                }
            }
            if(keyLEFT ){
                if(Math.abs(deltaX-8)<=rangeX && Math.abs(deltaY)<=rangeY) {

                    GameState.setChanged(true);
                    firstX -= 8;
                    deltaX-=8;
                }
            }
//            locX = Math.max(locX, 0);
//            locX = Math.min(locX, GameFrame.GAME_WIDTH );
//            locY = Math.max(locY, 0);
//            locY = Math.min(locY, GameFrame.GAME_HEIGHT);


    }
}
