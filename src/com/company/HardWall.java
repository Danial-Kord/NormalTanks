package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HardWall extends Wall {
    public HardWall(int locX, int locY) {
        super(locX, locY);
        try {
            moshak = ImageIO.read(new File("Src//hardWall.png"));
            path =  "hardWall";
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX,locY,moshak.getWidth(),moshak.getHeight());
    }
}
