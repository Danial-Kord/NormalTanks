package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MovableTank extends Tank implements Serializable {
    protected boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    protected int deltaX,deltaY;
    public MovableTank(int locX,int locY){
        super(locX,locY);
        deltaX=0;
        deltaY=0;
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        try {
            looleh = ImageIO.read(new File("Src//looleh.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    public MovableTank(int locX,int locY,int range){
        super(locX,locY,range);
        try {
            tank = ImageIO.read(new File("Src//icon.png"));
            looleh = ImageIO.read(new File("Src//looleh.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
    protected boolean check(ArrayList<Wall>walls,String jahat){



        for (int j = 0; j < walls.size(); j++) {
//            walls.get(j).setLocX(walls.get(j).getFirstX()-tanks1.get(0).getDeltaX());
//            walls.get(j).setLocY(walls.get(j).getFirstY()-tanks1.get(0).getDeltaY());
            int x1=walls.get(j).getLocX(),y1=walls.get(j).getLocY();
            if(jahat.equals("up"))
                y1-=2;
            if(jahat.equals("down"))
                y1+=2;
            if(jahat.equals("right"))
                x1+=2;
            if(jahat.equals("left"))
                x1-=2;
                if (Math.pow(x1 + 50 - this.getLocX() - this.getTank().getWidth() / 2, 2) + Math.pow(y1 + 50
                        - this.getLocY() - this.getTank().getHeight() / 2, 2) < 32000) {
//                    if(jahat.equals("up")||jahat.equals("down"))
//                        System.out.println("vared");

                    int count=0;
                    if(jahat.equals("up")) {
                        for (int k = 0; k < 100; k++) {
                            for (int l = 0; l < 5; l++) {
                                for (int g = 0; g < this.getTank().getWidth(); g += 10) {
                                    for (int h = 95; h < this.getTank().getHeight(); h++) {
                                        if (this.getLocX() + g == walls.get(j).getLocX() + k && this.getLocY() + h == walls.get(j).getLocY() - 2 + l) {
                                            // if(count>=200)
                                            up = true;
//                                            System.out.println("up");
                                            return true;
//                                        System.out.println("up");
                                        }

                                    }

                                }

                            }

                        }
                    }
                    int count2=0;
                    if(jahat.equals("down")) {
                        for (int k = 0; k < 100; k++) {
                            for (int l = 95; l < 100; l++) {
                                for (int g = 0; g < this.getTank().getWidth(); g += 10) {
                                    for (int h = 0; h < 5; h++) {
                                        if (this.getLocX() + g == walls.get(j).getLocX() + k && this.getLocY() + h == walls.get(j).getLocY() + 2 + l) {
                                            // if(count2>=200)
                                            // System.out.println(h);
                                            down = true;
//                                            System.out.println("down");
                                            return true;

//                                        System.out.println("down");

                                        }

                                    }

                                }

                            }

                        }
                    }
                    int count1=0;
                    if(jahat.equals("left")) {
                        for (int k = 0; k < 5; k++) {
                            for (int l = 0; l < 100; l++) {
                                for (int g = 95; g < this.getTank().getWidth(); g++) {
                                    for (int h = 0; h < this.getTank().getHeight(); h += 10) {
                                        if (this.getLocX() + g == walls.get(j).getLocX() - 2 + k && this.getLocY() + h == walls.get(j).getLocY() + l) {
                                            //if(count1>=200)
                                            left = true;
                                            return true;
//                                        System.out.println("l");

                                        }

                                    }

                                }

                            }

                        }
                    }
                    int count3=0;
                    if(jahat.equals("right")) {
                        for (int k = 95; k < 100; k++) {
                            for (int l = 0; l < 100; l++) {
                                for (int g = 0; g < 5; g++) {
                                    for (int h = 0; h < this.getTank().getHeight(); h += 10) {
                                        if (this.getLocX() + g == walls.get(j).getLocX() + 2 + k && this.getLocY() + h == walls.get(j).getLocY() + l) {
                                            // if(count3>=200)
                                            right = true;
                                            return true;


                                        }
                                    }

                                }

                            }

                        }
                    }
                }
        }
        return false;
    }
    public boolean isKeyDOWN() {
        return keyDOWN;
    }

    public boolean isKeyLEFT() {
        return keyLEFT;
    }

    public boolean isKeyRIGHT() {
        return keyRIGHT;
    }

    public boolean isKeyUP() {
        return keyUP;
    }
}
