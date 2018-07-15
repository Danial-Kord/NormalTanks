package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * father of all things that increase your ablities
 */
public abstract class Prizes extends Building implements Serializable{
    protected transient BufferedImage image;
    public Rectangle rectangle;
    protected transient Tank tank;
    private Boolean visible = true;
    public Prizes(int locX, int locY) {
        super(locX,locY);
    }
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public boolean collision(Tank tank) {
        if (tank.getRectangle().intersects(rectangle)) {
            return true;
        } else {
            return false;
        }
    }

    //g2d.drawImage(ImageIO.read(soil), 500, 500, this);
    // private static File photoHeavy=new File("src\\Images\\NumberOfHeavyBullet2.png");
    public abstract void task(Tank tank);

}
