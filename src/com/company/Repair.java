package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * increas your health
 */
public class Repair extends Prizes implements Serializable{
    public Repair(int locX, int locY) {
        super(locX, locY);
        kind="repair";
        try {
            super.image= ImageIO.read(new File("src\\prizes\\tank repair.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX, locY, image.getWidth(), image.getHeight());
            path = "repair";
    }

    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.setHealth(tank.getHealth() + 500);
                setVisible(false);
                die=true;
                SoundsHandeler.playSoundInGame(new File("music\\repair.wav"));
            }
        } else {
            return;
        }
    }
}
