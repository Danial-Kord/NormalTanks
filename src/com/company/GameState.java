package com.company;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

    private Tank tank;
    private ArrayList<Tir> tirs;
    private ArrayList<Tank>tanks;
    private ArrayList<Wall>walls;
    public GameState() {
        //
        // Initialize the game state and all elements ...
        //
        walls = new ArrayList<Wall>();
        tank = new Tank(false,100,100);
        tanks = new ArrayList<Tank>();
        tanks.add(new Tank(true,300,100,1000));
        tirs = new ArrayList<Tir>();
        for (int i=0;i<20;i++)
            walls.add(new Wall(400,520+100*i));
        for (int i=0;i<20;i++)
            walls.add(new Wall(500+100*i,520));
    }

    /**
     * The method which updates the game state.
     */
    public void update() {
        //
        // Update the state of all game elements
        //  based on user input and elapsed time ...
        //

        tank.setStop(false);
//        for (int i=0;i<walls.size();i++){
//            walls.get(i).setLocX(walls.get(i).getFirstX()-tank.getLocX());
//            walls.get(i).setLocY(walls.get(i).getFirstY()-tank.getLocY());
//        }


        if(tank.isMouseRealised()){
            double rotationRequired = Math.atan((double) (tank.getMouseY() - tank.locY-100)/
                    (double) Math.abs((tank).getMouseX() - tank.locX-100));
            if((tank).getMouseX() - tank.locX-100 < 0 && tank.getMouseY() - tank.locY-100 > 0){
                rotationRequired = Math.PI - rotationRequired;
            }
            if((tank).getMouseX() - tank.locX-100 < 0 && tank.getMouseY() - tank.locY-100 < 0){
                rotationRequired =-Math.PI-rotationRequired ;
            }
//            if(tank.getMouseY() - tank.locY > 0)
            tirs.add(new Tir(tank.locX+tank.getTank().getWidth()/2,tank.locY+tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
                    (double) Math.abs((tank).getMouseX()) - tank.locX-100,rotationRequired,tank));
//            tirs.add(new Tir(tank.locX+tank.getTank().getWidth()/2+tank.getLooleh().getWidth()/2
//                    ,tank.locY+tank.getTank().getHeight()/2+tank.getLooleh().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100)
//                    ,(double) Math.abs((tank).getMouseX()) - tank.locX-100,rotationRequired));

// else {
//                tirs.add(new Tir(tank.locX-tank.getTank().getWidth()/2,tank.locY-tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
//                        (double) Math.abs((tank).getMouseX()) - tank.locX-100,rotationRequired));
//            }

            tank.setMouseRealised(false);
        }
        Iterator iterator = tirs.iterator();
        while (iterator.hasNext()){
            Tir tir = (Tir) iterator.next();
            if(tir.isDie())
                iterator.remove();
        }
        Iterator iterator1 = walls.iterator();
        while (iterator1.hasNext()){
            Wall wall = (Wall) iterator1.next();
            if(wall.isDie())
                iterator1.remove();
        }
        Iterator iterator2 = tanks.iterator();
        while (iterator2.hasNext()){
            Tank tank = (Tank) iterator2.next();
            if(tank.isDead())
                iterator2.remove();
        }
            ArrayList<Tank>tanks1 = new ArrayList<Tank>();
            tanks1.add(tank);
            for (int i=0;i<tanks.size();i++)
                tanks1.add(tanks.get(i));
            for (int j = 0; j < walls.size(); j++) {
                for (int b=0;b<tanks1.size();b++) {
                    if (Math.pow(walls.get(j).getLocX() + 50 - tanks1.get(b).getLocX() - tanks1.get(b).getTank().getWidth() / 2, 2) + Math.pow(walls.get(j).getLocY() + 50
                            - tanks1.get(b).getLocY() - tanks1.get(b).getTank().getHeight() / 2, 2) < 16000) {
                        boolean up = false, left = false, right = false, down = false;
//                    for (int k = 0; k < 100; k++) {
//                        for (int l = 0; l < 100; l++) {
//                            for (int g = 0; g < tank.getTank().getWidth(); g++) {
//                                for (int h = 0; h <tank.getTank().getHeight(); h++) {
//                                    if (tank.getLocX() + g == walls.get(j).getLocX() + k && tank.getLocY() + h == walls.get(j).getLocY() + l) {
//                                        if(k<=100 && l<5){
//                                            up=true;
//                                            System.out.println("up");
//                                        }
//                                        if(k<5 && l<=100){
//                                            left=true;
//                                            System.out.println("l");
//                                        }
//                                        if(k<=100 && l>85){
//                                            down=true;
//                                            System.out.println("down");
//                                        }
//                                        if(k>85 && l<=100){
//                                            right=true;
//                                            System.out.println("r");
//                                        }
//                                    }
//                                }
//                            }
//                            if(l>20 && l<80)
//                                l=80;
//                        }
//                        if(k>20 && k<80)
//                            k=80;
//                    }
//
                        for (int k = 0; k < 100; k++) {
                            for (int l = 0; l < 20; l++) {
                                for (int g = 0; g < tanks1.get(b).getTank().getWidth(); g++) {
                                    for (int h = 0; h < tanks1.get(b).getTank().getHeight(); h++) {
                                        if (tanks1.get(b).getLocX() + g == walls.get(j).getLocX() + k && tanks1.get(b).getLocY() + h == walls.get(j).getLocY() + l) {
                                            up = true;
                                            System.out.println("up");
                                            break;
                                        }
                                        if (up)
                                            break;
                                    }
                                    if (up)
                                        break;
                                }
                                if (up)
                                    break;
                            }
                            if (up)
                                break;
                        }
                        for (int k = 0; k < 100; k++) {
                            for (int l = 80; l < 100; l++) {
                                for (int g = 0; g < tanks1.get(b).getTank().getWidth(); g++) {
                                    for (int h = 0; h < tanks1.get(b).getTank().getHeight(); h++) {
                                        if (tanks1.get(b).getLocX() + g == walls.get(j).getLocX() + k && tanks1.get(b).getLocY() + h == walls.get(j).getLocY() + l) {
                                            down = true;
                                            System.out.println("down");
                                            break;
                                        }
                                        if (down)
                                            break;
                                    }
                                    if (down)
                                        break;
                                }
                                if (down)
                                    break;
                            }
                            if (down)
                                break;
                        }
                        for (int k = 0; k < 20; k++) {
                            for (int l = 0; l < 100; l++) {
                                for (int g = 0; g < tanks1.get(b).getTank().getWidth(); g++) {
                                    for (int h = 0; h < tanks1.get(b).getTank().getHeight(); h++) {
                                        if (tanks1.get(b).getLocX() + g == walls.get(j).getLocX() + k && tanks1.get(b).getLocY() + h == walls.get(j).getLocY() + l) {
                                            left = true;
                                            System.out.println("l");
                                            break;
                                        }
                                        if (left)
                                            break;
                                    }
                                    if (left)
                                        break;
                                }
                                if (left)
                                    break;
                            }
                            if (left)
                                break;
                        }

                        for (int k = 80; k < 100; k++) {
                            for (int l = 0; l < 100; l++) {
                                for (int g = 0; g < tanks1.get(b).getTank().getWidth(); g++) {
                                    for (int h = 0; h < tanks1.get(b).getTank().getHeight(); h++) {
                                        if (tanks1.get(b).getLocX() + g == walls.get(j).getLocX() + k && tanks1.get(b).getLocY() + h == walls.get(j).getLocY() + l) {
                                            right = true;
                                            System.out.println("r");
                                            break;
                                        }
                                    }
                                    if (right)
                                        break;
                                }
                                if (right)
                                    break;
                            }
                            if (right)
                                break;
                        }
                        tanks1.get(b).setUp(up);
                        tanks1.get(b).setDown(down);
                        tanks1.get(b).setLeft(left);
                        tanks1.get(b).setRight(right);
                    }
                }
            }

                for (int i=0;i<tanks1.size();i++) {
                    tanks1.get(i).update();
                    if(tanks1.get(i).isEnemy()){
                        if(tanks1.get(i).getCount() %30==0){
                            double rotationRequired = Math.atan((double) (tank.locY- tanks1.get(i).locY)/
                                    (double) Math.abs((tank.locX- tanks1.get(i).locX)));
                            if(tank.locX- tanks1.get(i).locX < 0 && tank.locY- tanks1.get(i).locY > 0){
                                rotationRequired = Math.PI - rotationRequired;
                            }
                            if(tank.locX- tanks1.get(i).locX < 0 && tank.locY- tanks1.get(i).locY < 0){
                                rotationRequired =-Math.PI-rotationRequired ;
                            }
                            tirs.add(new Tir(tanks1.get(i).locX+tanks1.get(i).getTank().getWidth()/2,tanks1.get(i).locY+tanks1.get(i).getTank().getHeight()/2,(double) (tank.locY- tanks1.get(i).locY),
                                    (double) tank.locX- tanks1.get(i).locX,rotationRequired,tanks1.get(i)));
                        }

                    }
                }
//            for (int i=0;i<tanks.size();i++){
//            tanks.get(i).update(up,down,right,left);
//            }
        for (int i=0;i<tirs.size();i++) {
            tirs.get(i).update();
//            for (int j=0;j<walls.size();j++) {
//                if (tirs.get(i).getLocX() + 126 > walls.get(j).getLocX() && tirs.get(i).getLocX() + 126 < walls.get(j).getLocX()+100){
//                    boolean flag=false;
//                    for (int k=0;k<53;k++){
//                        if(tirs.get(i).getLocY() + k > walls.get(j).getLocY() && tirs.get(i).getLocY() + k < walls.get(j).getLocX()+100){
//                            flag=true;
//                            break;
//                        }
//                    }
//                    if(flag) {
//                        walls.get(j).setHealth(walls.get(j).getHealth() - 1);
//                        tirs.get(i).setDie(true);
//                    }
//                    System.out.println(flag);
//                }
//            }
            boolean flag=false;
            if(!flag)
            for (int j=0;j<tanks1.size();j++){
                if(flag)
                    break;
                if(tanks1.get(j) != tirs.get(i).getSorce())
                if (Math.pow(tanks1.get(j).locX+100  - tirs.get(i).getLocX() , 2) + Math.pow(tanks1.get(j).locY + 100 - tirs.get(i).getLocY() - 29, 2) <4000) {
                    for (int k = 0; k < tanks1.get(j).getTank().getWidth(); k++) {
                        for (int l = 0; l < tanks1.get(j).getTank().getHeight(); l++) {
                            for (int g = 0; g < tirs.get(i).getMoshak().getWidth(); g++) {
                                for (int h = 0; h <tirs.get(i).getMoshak().getHeight(); h++) {
                                    if (tirs.get(i).getLocX() + g == tanks1.get(j).locX + k && tirs.get(i).getLocY() + h == tanks1.get(j).locY + l) {
                                        tanks1.get(j).setHealth(tanks1.get(j).getHealth() - 1);
                                        tirs.get(i).setDie(true);
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag)
                                    break;
                            }
                            if (flag)
                                break;
                        }
                        if (flag)
                            break;
                    }
                    if(flag)
                        break;
                }
            }

            boolean flags = false;
            if(!flags)
                for (int j = 0; j < walls.size(); j++) {
                    if(flags)
                        break;
                    if (Math.pow(walls.get(j).getLocX() + 50 - tirs.get(i).getLocX() - 63, 2) + Math.pow(walls.get(j).getLocY() + 50 - tirs.get(i).getLocY() - 29, 2) <4000) {
                        for (int k = 0; k < 100; k++) {
                            for (int l = 0; l < 100; l++) {
                                for (int g = 0; g < tirs.get(i).getMoshak().getWidth(); g++) {
                                    for (int h = 0; h <tirs.get(i).getMoshak().getHeight(); h++) {
                                        if (tirs.get(i).getLocX() + g == walls.get(j).getLocX() + k && tirs.get(i).getLocY() + h == walls.get(j).getLocY() + l) {
                                            walls.get(j).setHealth(walls.get(j).getHealth() - 1);
                                            tirs.get(i).setDie(true);
                                            flags = true;
                                            break;
                                        }
                                    }
                                    if (flags)
                                        break;
                                }
                                if (flags)
                                    break;
                            }
                            if (flags)
                                break;
                        }
                        if(flags)
                            break;
                    }
                }
        }
        updateWalls();
//        System.out.println(walls.size());
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<Tir> getTirs() {
        return tirs;
    }

    public Tank getTank() {
        return tank;
    }
    public void updateWalls(){
        for (int i=0;i<walls.size();i++){
            BufferedImage moshak = null;
            if(walls.get(i).getHealth() == 2){
                try {
                    moshak = ImageIO.read(new File("Src//softWall2.png"));
                } catch (IOException e) {
                    // e.printStackTrace();
                }
                walls.get(i).setMoshak(moshak);
            }
            if(walls.get(i).getHealth() == 1){
                try {
                    moshak = ImageIO.read(new File("Src//softWall3.png"));
                } catch (IOException e) {
                    //e.printStackTrace();
                }
                walls.get(i).setMoshak(moshak);
            }
            if(walls.get(i).getHealth() <= 0){
                walls.get(i).setDie(true);
                System.out.println("sda");
            }
        }
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}

