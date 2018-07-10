package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Repair extends Prizes {
    public Repair(int locX, int locY) {
        super(locX, locY);
        try {
            super.image= ImageIO.read(new File("src\\prizes\\tank repair.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                tank.setHealth(5);
                setVisible(false);
            }
        } else {
            return;
        }
    }
}
