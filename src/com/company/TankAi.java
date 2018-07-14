package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TankAi extends MovableTank{

    private int  rangeX,rangeY;
    public TankAi(int locX, int locY,int range,int tirRange) {
        super(locX, locY,range);
        rangeX=range;
        rangeY=range;
        this.tirRange = tirRange;
    }
    public TankAi(int locX, int locY,int rangeX,int rangeY,int tirRange) {
        super(locX, locY);
        this.rangeX=range;
        this.rangeY=range;
        this.tirRange = tirRange;
        kind = "TankAi";
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (health >= 3) {
            try {
                tank = ImageIO.read(new File("Src//icon.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (health >= 2) {
            try {
                tank = ImageIO.read(new File("Src//icon2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (health == 1) {
            try {
                tank = ImageIO.read(new File("Src//icon3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    if (Math.abs(deltaX)<=rangeX && Math.abs(deltaY-1)<=rangeY) {
                        firstY -= 1;
                        deltaX-=8;
                    }
                }
            if(keyDOWN){
                if(Math.abs(deltaX)<=rangeX && Math.abs(deltaY+1)<=rangeY) {
                    firstY += 1;
                    deltaY += 8;
                }
            }
            if(keyRIGHT ){
                if(Math.abs(deltaX+1)<=rangeX && Math.abs(deltaY)<=rangeY) {
                    firstX += 1;
                    deltaX+=8;
                }
            }
            if(keyLEFT ){
                if(Math.abs(deltaX-1)<=rangeX && Math.abs(deltaY)<=rangeY) {

                    firstX -= 1;
                    deltaX-=8;
                }
            }
//            locX = Math.max(locX, 0);
//            locX = Math.min(locX, GameFrame.GAME_WIDTH );
//            locY = Math.max(locY, 0);
//            locY = Math.min(locY, GameFrame.GAME_HEIGHT);


    }
}
