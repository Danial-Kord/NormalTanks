package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * kind of bullets
 */
public class CheapBullet extends Tir{
    public CheapBullet(int locX, int locY, double deltaY, double deltaX, double shib, Tank sorce, int firstTankX, int firstTankY) {
        super(locX, locY, deltaY, deltaX, shib, sorce, firstTankX, firstTankY);
        try {
            moshak = ImageIO.read(new File("Src//bom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX,locY,moshak.getWidth(),moshak.getHeight());
        setKind("cheap");
    }
}
