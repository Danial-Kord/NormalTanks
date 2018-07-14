package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Canonshells extends Prizes {
    public Canonshells(int locX, int locY) {
        super(locX, locY);
        try {
            super.image = ImageIO.read(new File("src\\prizes\\canonshells.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = "canonshells";
    }

    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.plusBullet(35);
                setVisible(false);
            }
        } else {
            return;
        }
    }
}
