package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * increase your speed of your bullet
 */
public class Star extends Prizes implements Serializable{
    public Star(int locX, int locY) {
        super(locX, locY);
        kind = "star";
        try {
            super.image= ImageIO.read(new File("src\\prizes\\star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        path="star";
        rectangle = new Rectangle(locX, locY, image.getWidth(), image.getHeight());
    }



    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                if(tank instanceof TankHuman){
                    TankHuman temp = (TankHuman)tank;
                    temp.setTirSpeed(40);
                }
                setVisible(false);
                die=true;
                SoundsHandeler.playSoundInGame(new File("music\\repair.wav"));
            }
        } else {
            return;
        }
    }
}
