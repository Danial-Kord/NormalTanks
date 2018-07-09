package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grass {
    private BufferedImage grass;
    public Grass(){
        try {
            grass = ImageIO.read(new File("src\\Images\\plant.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
