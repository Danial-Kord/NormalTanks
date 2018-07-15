package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * kind of prizes that improve your bullet
 */
public class Canonshells extends Prizes implements Serializable{
    public Canonshells(int locX, int locY) {
        super(locX, locY);
        setKind("canonshells");
        try {
            super.image = ImageIO.read(new File("src\\prizes\\canonshells.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX, locY, image.getWidth(), image.getHeight());
        path = "canonshells";

    }

    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.plusBullet(35);
                setVisible(false);
                die=true;
                SoundsHandeler.playSoundInGame(new File("music\\repair.wav"));
            }
        } else {
            return;
        }
    }
}
