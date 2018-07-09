package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grass {
    private BufferedImage grass;
    private int x,y;
    private final int firstX,firstY;
    public Grass(int x,int y){
        this.x=x;
        this.y=y;
        firstX=x;
        firstY=y;
        try {
            grass = ImageIO.read(new File("src\\Images\\plant.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public BufferedImage getGrass() {
        return grass;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFirstY() {
        return firstY;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
