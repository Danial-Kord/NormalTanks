package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Teazel extends Wall{
    public Teazel(int locX, int locY) {
        super(locX, locY);
        try {
            moshak = ImageIO.read(new File("Src//teazel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
