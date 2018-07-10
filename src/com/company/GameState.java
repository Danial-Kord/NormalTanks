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

    private TankHuman tank;
    private ArrayList<Tir> tirs;
    private ArrayList<Tank>tanks;
    private ArrayList<Wall>walls;
    private ArrayList<Grass>grasses;
    private ArrayList<Prizes> prizes = new ArrayList<>();
    private ArrayList<Building>buildings;
    public ArrayList<Prizes> getPrizes() {
        return prizes;
    }
    public GameState() {
        //
        // Initialize the game state and all elements ...
        //
        buildings = new ArrayList<Building>();
        grasses=new ArrayList<Grass>();
        walls = new ArrayList<Wall>();
        tank = new TankHuman( 1200, 800);
        tirs = new ArrayList<Tir>();
        tanks = new ArrayList<Tank>();
        prizes.add(new Canonshells(1400,860));
        prizes.add(new Machingun(1400,890));
        prizes.add(new Repair(1400,940));
        prizes.add(new Star(1400,960));
        mapCreator();

 /////////////////////////////////
        for (int i=0;i<walls.size();i++)
            buildings.add(walls.get(i));
        for (int i=0;i<prizes.size();i++)
            buildings.add(prizes.get(i));
        for (int i=0;i<grasses.size();i++)
            buildings.add(grasses.get(i));
    }

    /**
     * The method which updates the game state.
     */
    private void mapCreator(){

        tanks.add(new TankAi( 300, 100, 100,10,1000000));
        tanks.add(new StaticTank( 100, 2800,1000000));
        for (int i=0;i<20;i++)
            walls.add(new SoftWall(0,-520+100*i));
        for (int i=0;i<40;i++)
            walls.add(new Teazel(-500+100*i,520));
        walls.add(new HardWall(900,900));
        for (int i = 0; i < 20; i++) {
            walls.add(new HardWall(0 + 100 * i, 0));
        }
//        walls.add(new Wall(120,120,"teazel"));
        for (int i = 0; i < 30; i++) {
            walls.add(new HardWall(0, 100 * i));
        }
        for (int i = 0; i < 30; i++) {
            walls.add(new HardWall(2000, 100 * i));
        }
        for (int i = 0; i < 21; i++) {
            walls.add(new HardWall(100 * i, 3000));
        }
        for (int i = 0; i < 5; i++) {
            walls.add(new HardWall(600, 100 + 100 * i));
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                walls.add(new SoftWall(100 + 100 * i, 100 + j * 100));
            }
        }
        walls.add(new Teazel(100, 2600));
        walls.add(new Teazel(200, 2600));
        walls.add(new Teazel(300, 2600));
        walls.add(new Teazel(300, 2700));
        walls.add(new Teazel(300, 2800));
        walls.add(new Teazel(300, 2900));
        for (int i = 0; i < 5; i++) {
            walls.add(new HardWall(1600 + 100 * i, 2500));
        }
        for (int i = 0; i < 4; i++) {
            walls.add(new HardWall(1700 + 100 * i, 2400));
        }
        for (int i = 0; i < 3; i++) {
            walls.add(new HardWall(1800 + 100 * i, 2300));
        }
        for (int i = 0; i < 2; i++) {
            walls.add(new HardWall(1900 + 100 * i, 2200));
        }
        for (int i = 0; i < 4; i++) {
            walls.add(new Teazel(1000 + 100 * i, 2500));
        }
        walls.add(new SoftWall(1600, 2700));
        walls.add(new SoftWall(1600, 2800));
        walls.add(new SoftWall(1600, 2900));
        walls.add(new SoftWall(1600, 2600));
        walls.add(new HardWall(800, 1600));
        walls.add(new HardWall(900, 1600));
        walls.add(new HardWall(1000, 1600));
        walls.add(new HardWall(1100, 1600));
        walls.add(new HardWall(900, 1700));
        walls.add(new HardWall(1000, 1700));
        walls.add(new Teazel(300, 600));
        walls.add(new Teazel(400, 600));
        walls.add(new Teazel(500, 600));
        walls.add(new Teazel(600, 600));
        for (int i = 0; i < 9; i++) {
            walls.add(new HardWall(100 + 100 * i, 2500));
        }
        for(int i=0;i<9;i++){
            walls.add(new HardWall(100+100*i,900));
        }
        for(int i=0;i<5;i++){
            walls.add(new HardWall(100+100*8,900-100*i));
        }
        for(int i=0;i<4;i++){
            walls.add(new SoftWall(100+100*8,400-100*i));
        }
        for (int i=0;i<3;i++){
            grasses.add(new Grass(100*(i+10),800));
        }
    }

    public ArrayList<Grass> getGrasses() {
        return grasses;
    }

    public void update() {
        //
        // Update the state of all game elements
        //  based on user input and elapsed time ...
        //
//        tank.setStop(false);
        mapMoving();
        tankHit();
        removingTrash();
        BuildingNewBullet();
        bulletHit();
        for(int i=0;i<prizes.size();i++){
            prizes.get(i).task(tank);
        }
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }//تانک برخورد با دیوار
    public void tankHit(){

    }
    public ArrayList<Tir> getTirs() {
        return tirs;
    }
    public void mapMoving(){
        for (int i=0;i<tirs.size();i++){
            tirs.get(i).setLocX(tirs.get(i).getFirstX()-tirs.get(i).getTankDeltaX());
            tirs.get(i).setLocY(tirs.get(i).getFirstY()-tirs.get(i).getTankDeltaY());
        }
        for (int i=0;i<tanks.size();i++){
            tanks.get(i).locX=tanks.get(i).firstX-tank.getDeltaX();
            tanks.get(i).locY=tanks.get(i).firstY-tank.getDeltaY();
        }

//        for (int i=0;i<buildings.size();i++){
//            buildings.get(i).setLocX(buildings.get(i).getFirstX()-tank.getDeltaX());
//            buildings.get(i).setLocY(buildings.get(i).getFirstY()-tank.getDeltaY());
//        }
        tank.getRectangle().setLocation(tank.getLocX(),tank.getLocY());
                for (int i=0;i<walls.size();i++){
            walls.get(i).setLocX(walls.get(i).getFirstX()-tank.getDeltaX());
            walls.get(i).setLocY(walls.get(i).getFirstY()-tank.getDeltaY());
        }
        for(int i=0;i<grasses.size();i++){
            grasses.get(i).setLocX(grasses.get(i).getFirstX()-tank.getDeltaX());
            grasses.get(i).setLocY(grasses.get(i).getFirstY()-tank.getDeltaY());
        }
        for (int i=0;i<prizes.size();i++){
            prizes.get(i).setLocX(prizes.get(i).getFirstX() - tank.getDeltaX());
            prizes.get(i).setLocY(prizes.get(i).getFirstY() - tank.getDeltaY());
            prizes.get(i).rectangle.setLocation(prizes.get(i).getLocX(),prizes.get(i).getLocY());
        }

//        System.out.println(tank.locX +".........."+tank.locY);
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void BuildingNewBullet(){
        if(tank.isMouseRealised()){
            double rotationRequired = Math.atan((double) (tank.getMouseY() - tank.locY-100+25)/
                    (double) Math.abs(tank.getMouseX() - tank.locX-100+25));
            if((tank).getMouseX() - tank.locX-100 < 0 && tank.getMouseY() - tank.locY-100 > 0){
                rotationRequired = Math.PI - rotationRequired;
            }
            if((tank).getMouseX() - tank.locX-100 < 0 && tank.getMouseY() - tank.locY-100 < 0){
                rotationRequired =-Math.PI-rotationRequired ;
            }
            if(tank.stateHeavyGun){
                if(tank.getBullet() >0){
                    tirs.add(new Tir(tank.locX+tank.getTank().getWidth()/2,tank.locY+tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
                            (double) (tank).getMouseX() - tank.locX-100,rotationRequired,tank,tank.getDeltaX(),tank.getDeltaY()));
                }
            }
            else {
                if(tank.getNumberCheapBullet() >0){
                    tirs.add(new Tir(tank.locX+tank.getTank().getWidth()/2,tank.locY+tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
                            (double) (tank).getMouseX() - tank.locX-100,rotationRequired,tank,tank.getDeltaX(),tank.getDeltaY()));
                }
            }
            tank.setMouseRealised(false);
        }
        ArrayList<Tank>tanks1 = new ArrayList<Tank>();

        tanks1.add(tank);
        for (int i=0;i<tanks.size();i++)
            tanks1.add(tanks.get(i));
        for (int i=0;i<tanks1.size();i++) {
            tanks1.get(i).update(walls,tanks1);//update tank information
            if(tanks1.get(i) instanceof TankAi || tanks1.get(i) instanceof StaticTank){
                if(Math.pow(tank.locY-tanks1.get(i).locY,2)+Math.pow(tank.locX-tanks1.get(i).locX,2) < tanks1.get(i).tirRange) {
                    if (tanks1.get(i).getCount() % 30 == 0) {
                        double rotationRequired = Math.atan((double) (tank.locY - tanks1.get(i).locY) /
                                (double) Math.abs((tank.locX - tanks1.get(i).locX)));
                        if (tank.locX - tanks1.get(i).locX < 0 && tank.locY - tanks1.get(i).locY > 0) {
                            rotationRequired = Math.PI - rotationRequired;
                        }
                        if (tank.locX - tanks1.get(i).locX < 0 && tank.locY - tanks1.get(i).locY < 0) {
                            rotationRequired = -Math.PI - rotationRequired;
                        }
                        tirs.add(new Tir(tanks1.get(i).locX + tanks1.get(i).getTank().getWidth() / 2, tanks1.get(i).locY + tanks1.get(i).getTank().getHeight() / 2, (double) (tank.locY - tanks1.get(i).locY),
                                (double) tank.locX - tanks1.get(i).locX, rotationRequired, tanks1.get(i), tank.getDeltaX(), tank.getDeltaY()));
                    }
                }
            }
        }
    }

    public void bulletHit(){
        ArrayList<Tank>tanks1 = new ArrayList<Tank>();
        tanks1.add(tank);
        for (int i=0;i<tanks.size();i++)
            tanks1.add(tanks.get(i));
        //tank hit
        for (int i=0;i<tirs.size();i++){
            tirs.get(i).update();
            tirs.get(i).setTankDeltaY(tank.getDeltaY()-tirs.get(i).getFirstTankY());
            tirs.get(i).setTankDeltaX(tank.getDeltaX()-tirs.get(i).getFirstTankX());
        }
        for (int i=0;i<tirs.size();i++) {
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

                //wall hit
            boolean flags = false;
            if(!flags)
                for (int j = 0; j < walls.size(); j++) {
                    if(flags)
                        break;
                    if (Math.pow(walls.get(j).getLocX() + 50 - tirs.get(i).getLocX() - 63, 2) + Math.pow(walls.get(j).getLocY() + 50 - tirs.get(i).getLocY() - 29, 2) <3025) {
                        if(walls.get(j) instanceof SoftWall)
                        walls.get(j).setHealth(walls.get(j).getHealth() - 1);
                        if(!(walls.get(j) instanceof Teazel))
                        tirs.get(i).setDie(true);
                        flag=true;
//                        for (int k = 0; k < 100; k++) {
//                            for (int l = 0; l < 100; l++) {
//                                for (int g = 0; g < tirs.get(i).getMoshak().getWidth(); g++) {
//                                    for (int h = 0; h <tirs.get(i).getMoshak().getHeight(); h++) {
//                                        if (tirs.get(i).getLocX() + g == walls.get(j).getLocX() + k && tirs.get(i).getLocY() + h == walls.get(j).getLocY() + l) {
//                                            walls.get(j).setHealth(walls.get(j).getHealth() - 1);
//                                            tirs.get(i).setDie(true);
//                                            flags = true;
//                                            break;
//                                        }
//                                    }
//                                    if (flags)
//                                        break;
//                                }
//                                if (flags)
//                                    break;
//                            }
//                            if (flags)
//                                break;
//                        }
//                        if(flags)
//                            break;
                    }
                }
        }
    }
    public void removingTrash(){
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

    }
    public TankHuman getTank() {
        return tank;
    }


    public ArrayList<Wall> getWalls() {
        return walls;
    }
}

