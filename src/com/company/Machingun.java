package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Machingun extends Prizes {
    public Machingun(int locX, int locY) {
        super(locX, locY);
        try {
            super.image = ImageIO.read(new File("src\\prizes\\machingun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.plusCheapBullet(30);
                setVisible(false);
            }
        } else {
            return;
        }
    }

}
