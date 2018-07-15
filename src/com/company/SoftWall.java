package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * kind of walls that can be destroyed
 */
public class SoftWall extends Wall implements Serializable {
    private transient BufferedImage moshak2,moshak3;
    public SoftWall(int locX, int locY) {
        super(locX, locY);
        kind = "softWall";
        health = 300;
        try {
                moshak = ImageIO.read(new File("Src//softWall.png"));
                moshak2 = ImageIO.read(new File("Src//softWall2.png"));
                moshak3 = ImageIO.read(new File("Src//softWall3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX,locY,moshak.getWidth(),moshak.getHeight());
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if(health==200)
            moshak = moshak2;
        if(health == 100)
            moshak = moshak3;
    }
}
