package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * kind of bullet that have many damage
 */
public class HeavyBullet extends Tir {
    public HeavyBullet(int locX, int locY, double deltaY, double deltaX, double shib,Tank sorce,int firstTankX,int firstTankY) {
        super(locX, locY, deltaY, deltaX, shib,sorce,firstTankX,firstTankY);
    }
}
