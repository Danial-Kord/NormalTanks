package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StaticTank extends Tank {


    public StaticTank(int locX, int locY,int tirRange) {
        super(locX, locY);
        this.tirRange = tirRange;
    }
}
