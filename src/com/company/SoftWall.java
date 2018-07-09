package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SoftWall extends Wall{
    private BufferedImage moshak2,moshak3;
    public SoftWall(int locX, int locY) {
        super(locX, locY);
        try {
                moshak = ImageIO.read(new File("Src//softWall.png"));
                moshak2 = ImageIO.read(new File("Src//softWall2.png"));
                moshak3 = ImageIO.read(new File("Src//softWall3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if(health==2)
            moshak = moshak2;
        if(health == 1)
            moshak = moshak3;
    }
}
