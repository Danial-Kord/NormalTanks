package com.company;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Wall extends Building implements Serializable {
    protected transient BufferedImage moshak;
    protected Rectangle rectangle;
    public Wall(int locX,int locY){
        super(locX,locY);
        health = 300;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }


    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setMoshak(BufferedImage moshak) {
        this.moshak = moshak;
    }
    public int getHealth() {
        return health;
    }
    public BufferedImage getMoshak() {
        return moshak;
    }
}

