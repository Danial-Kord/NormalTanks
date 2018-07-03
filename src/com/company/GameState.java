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
    private ArrayList<Wall>walls;
    public GameState() {
        //
        // Initialize the game state and all elements ...
        //
        walls = new ArrayList<Wall>();
        tank = new Tank();
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
        tank.update();

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
                    (double) Math.abs((tank).getMouseX()) - tank.locX-100,rotationRequired));
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
            boolean flag = false;
            if(!flag)
                for (int j = 0; j < walls.size(); j++) {
                    if(flag)
                        break;
                    if (Math.pow(walls.get(j).getLocX() + 50 - tirs.get(i).getLocX() - 63, 2) + Math.pow(walls.get(j).getLocY() + 50 - tirs.get(i).getLocY() - 29, 2) <4000) {
                        for (int k = 0; k < 100; k++) {
                            for (int l = 0; l < 100; l++) {
                                for (int g = 0; g < tirs.get(i).getMoshak().getWidth(); g++) {
                                    for (int h = 0; h <tirs.get(i).getMoshak().getHeight(); h++) {
                                        if (tirs.get(i).getLocX() + g == walls.get(j).getLocX() + k && tirs.get(i).getLocY() + h == walls.get(j).getLocY() + l) {
                                            walls.get(j).setHealth(walls.get(j).getHealth() - 1);
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
        }
        updateWalls();
//        System.out.println(walls.size());
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

