package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Soosk extends MilitaryTool{
    private transient BufferedImage image;
    private boolean active;
    public Soosk(int locX, int locY) {
        super(locX, locY);
        active=false;
        rectangle = new Rectangle(locX,locY,100,100);
        health = 100;
        try {
            image = ImageIO.read(new File("src\\Images\\KhengEnemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tank= null;
        kind="Soosk";
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }

    public void update(TankHuman tank) {
        if(dead)
            return;
        if(Math.abs(tank.locY - locY) <=400 && Math.abs(tank.locX - locX) <=400){
            this.tank = image;
            active=true;
            double deltaX=tank.locX-locX+100;
            double deltaY=tank.locY-locY+100;
            deltaX =deltaX/Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
            deltaY =deltaY/Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
            firstX+=deltaX*5;
            firstY+=deltaY*5;
        }
        if(tank.rectangle.intersects(rectangle)) {
            dead = true;
            tank.setHealth(tank.health-100);
        }
    }
}
