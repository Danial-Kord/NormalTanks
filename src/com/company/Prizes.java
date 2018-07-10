package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Prizes extends Building{
    protected Image image;
    public Rectangle rectangle;
    protected Tank tank;
    private Boolean visible = true;
    public Prizes(int locX, int locY) {
        super(locX,locY);
        rectangle = new Rectangle(locX, locY, 40, 40);
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
