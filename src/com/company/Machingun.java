package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * increase your cheap bullet number
 */
public class Machingun extends Prizes {
    public Machingun(int locX, int locY) {
        super(locX, locY);
        try {
            super.image = ImageIO.read(new File("src\\prizes\\machingun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX, locY, image.getWidth(), image.getHeight());
        path = "machingun";
        kind=path;
    }

    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.plusCheapBullet(30);
                setVisible(false);
                die=true;
                SoundsHandeler.playSoundInGame(new File("music\\repair.wav"));
            }
        } else {
            return;
        }
    }

}
