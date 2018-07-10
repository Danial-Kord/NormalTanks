package com.company;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall extends Building{
    protected int health;
    protected BufferedImage moshak;
    protected boolean die;
    public Wall(int locX,int locY){
        super(locX,locY);
        health = 3;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health<=0)
            die=true;
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

