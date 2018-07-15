package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * kind of wall that wont destroy bullet
 */
public class Teazel extends Wall implements Serializable {
    public Teazel(int locX, int locY) {
        super(locX, locY);
        try {
            moshak = ImageIO.read(new File("Src//teazel.png"));
            path = "teazel";
            kind=path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(locX,locY,moshak.getWidth(),moshak.getHeight());
    }
}
