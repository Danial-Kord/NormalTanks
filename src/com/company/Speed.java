package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Speed extends Prizes {
    public Speed(int locX, int locY) {
        super(locX, locY);
        try {
            super.image= ImageIO.read(new File("src\\prizes\\tank repair.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = "repair";
    }
    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.setSpeed(tank.getSpeed() + 2);
                setVisible(false);
                SoundsHandeler.playSoundInGame(new File("music\\repair.wav"));
            }
        } else {
            return;
        }
    }
}
