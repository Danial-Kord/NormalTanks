package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * kind of object in the map that you can move under them
 */
public class Grass extends Building implements Serializable {
    private transient BufferedImage grass;
    public Grass(int x,int y){
        super(x,y);
        kind ="grass";
        try {
            grass = ImageIO.read(new File("src\\Images\\plant.png"));
            path = "plant";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public BufferedImage getGrass() {
        return grass;
    }

}
