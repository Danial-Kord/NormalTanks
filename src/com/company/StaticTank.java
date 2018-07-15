package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * kind of tank that cannot move
 */
public class StaticTank extends Tank implements Serializable{


    public StaticTank(int locX, int locY,int tirRange) {
        super(locX, locY);
        this.tirRange = tirRange;
    }
}
