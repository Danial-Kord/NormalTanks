package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * kind of wall never destroyed
 */
public class HardWall extends Wall implements Serializable {
    public HardWall(int locX, int locY) {
        super(locX, locY);
        kind = "hardWall";
        try {
            moshak = ImageIO.read(new File("Src//hardWall.png"));
            path =  "hardWall";
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX,locY,moshak.getWidth(),moshak.getHeight());
    }
}
