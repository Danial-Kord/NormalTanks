package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Star extends Prizes {
    public Star(int locX, int locY) {
        super(locX, locY);
        try {
            super.image= ImageIO.read(new File("src\\prizes\\star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void task(Tank tank) {
        if (collision(tank)) {
            if (getVisible()) {
                /////////////////////////////////سرعت رو باید اضافه کنیم اینجا
                ////////////////////////////////////
                setVisible(false);
            }
        } else {
            return;
        }
    }
}
