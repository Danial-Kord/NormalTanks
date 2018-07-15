package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * increase your move speed
 */
public class Speed extends Prizes {
    public Speed(int locX, int locY) {
        super(locX, locY);
        try {
            super.image= ImageIO.read(new File("src\\prizes\\speed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        kind = "speed";
        rectangle = new Rectangle(locX, locY, image.getWidth(), image.getHeight());
    }
    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.setSpeed(tank.getSpeed() + 2);
                setVisible(false);
                setDie(true);
                SoundsHandeler.playSoundInGame(new File("music\\repair.wav"));
            }
        } else {
            return;
        }
    }
}
