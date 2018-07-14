package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Grass extends Building implements Serializable {
    private BufferedImage grass;
    public Grass(int x,int y){
        super(x,y);
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
