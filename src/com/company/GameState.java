package com.company;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

    private TankHuman tank;
    private ArrayList<Tir> tirs;
    private ArrayList<Tank> tanks;
    private ArrayList<Wall> walls;
    private ArrayList<Grass> grasses;
    private ArrayList<Prizes> prizes = new ArrayList<>();
    private ArrayList<Building> buildings;
    private ArrayList<MilitaryTool>militaryTools = new ArrayList<MilitaryTool>();
    public Soosk soosk = new Soosk(1000,1000);

    public ArrayList<Prizes> getPrizes() {
        return prizes;
    }

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;
    //متغیر های مربوط به گویی
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    public int locX, locY, diam;
    public boolean gameOver;
    private boolean mousePress;
    private int mouseX, mouseY;

    public GameState() {
        locX = 100;
        locY = 100;
        diam = 32;
        gameOver = false;
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        mouseHandler = new MouseHandler();
        keyHandler = new KeyHandler();

        //
        // Initialize the game state and all elements ...
        //
        buildings = new ArrayList<Building>();
        grasses = new ArrayList<Grass>();
        walls = new ArrayList<Wall>();
        tank = new TankHuman(1200, 800);
        tirs = new ArrayList<Tir>();
        tanks = new ArrayList<Tank>();
        prizes.add(new Canonshells(1400, 860));
        prizes.add(new Machingun(1400, 890));
        prizes.add(new Repair(1400, 940));
        prizes.add(new Star(1400, 960));
        mapCreator();
        for (int i=0;i<tanks.size();i++)
            militaryTools.add(tanks.get(i));
        militaryTools.add(soosk);
        /////////////////////////////////
        for (int i = 0; i < walls.size(); i++)
            buildings.add(walls.get(i));
        for (int i = 0; i < prizes.size(); i++)
            buildings.add(prizes.get(i));
        for (int i = 0; i < grasses.size(); i++)
            buildings.add(grasses.get(i));
    }

    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }

    public KeyListener getKeyListener() {
        return keyHandler;
    }

    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE: {
                    GameFrame.loneWolfClick = false;
                    GameFrame.start = false;
                    GameFrame.join = false;
                    GameFrame.server = false;
                    GameFrame.eseay = false;
                    GameFrame.meduim = false;
                    GameFrame.hard = false;
                    GameFrame.serverClick = false;
                }
                break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            mousePress = true;
            guiSensorClick();
//            System.out.println("کلیک رو گرفتم");

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {


        }
    }

    private void guiSensorClick() {
        if (GameFrame.start) {
            GameFrame.loneWolfClick = true;
        }
        if (GameFrame.server) {
            GameFrame.serverClick = true;
        }
        if (GameFrame.join) {
            GuiConnection guiConnection = new GuiConnection();
        }
        if (GameFrame.loneWolfClick) {
            if (GameFrame.hard) {
                GameFrame.inMenu = false;
            } else if (GameFrame.meduim) {
                GameFrame.inMenu = false;
            } else if (GameFrame.eseay) {
                GameFrame.inMenu = false;
            }
        }
    }


    /**
     * The method which updates the game state.
     */
    private void mapCreator() {
        tanks.add(new TankAi(300, 100, 100, 10, 1000000));
        tanks.add(new SimpleStaticTank(100, 2800, 1000000));
        tanks.add(new PoisenosStaticTank(100, 2200, 1000000));
        for (int i = 0; i < 20; i++)
            walls.add(new SoftWall(0, -520 + 100 * i));
//        for (int i=0;i<40;i++)
//            walls.add(new Teazel(-500+100*i,520));
        walls.add(new HardWall(900, 900));
        for (int i = 0; i < 20; i++) {
            walls.add(new HardWall(0 + 100 * i, 0));
        }
//        walls.add(new Wall(120,120,"teazel"));
        for (int i = 0; i < 40; i++) {
            walls.add(new HardWall(0, 100 * i));
        }
        for (int i = 0; i < 40; i++) {
            walls.add(new HardWall(2000, 100 * i));
        }
        for (int i = 0; i < 21; i++) {
            walls.add(new HardWall(100 * i, 4000));
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
        walls.add(new Teazel(300, 3000));
        walls.add(new Teazel(300, 3100));
        walls.add(new Teazel(300, 3200));
        walls.add(new Teazel(200, 3200));
        walls.add(new Teazel(100, 3200));
        grasses.add(new Grass(100, 3100));
        grasses.add(new Grass(100, 3000));
        grasses.add(new Grass(200, 3000));
        grasses.add(new Grass(200, 3100));
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
        for (int i = 0; i < 3; i++) {
            walls.add(new Teazel(1000 + 100 * i, 2500));
        }
        //اون چمن های وسط
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                grasses.add(new Grass(1000 + 100 * i, 2600+100*j));
            }
        }
        //سیم خاردار سمت چپ چمن
        for(int i=0;i<4;i++){
            walls.add(new Teazel(1600+100*i,3000));
        }
        walls.add(new SoftWall(1600, 2700));
        walls.add(new SoftWall(1600, 2800));
        walls.add(new SoftWall(1600, 2900));
        walls.add(new SoftWall(1700, 2900));
        walls.add(new SoftWall(1800, 2900));
        walls.add(new SoftWall(1900, 2900));
        walls.add(new SoftWall(1600, 2600));
        walls.add(new HardWall(800, 1600));
        walls.add(new HardWall(900, 1600));
        walls.add(new HardWall(1000, 1600));
        walls.add(new HardWall(1100, 1600));
        walls.add(new HardWall(900, 1700));
        walls.add(new HardWall(1000, 1700));
        for(int i=0;i<8;i++) {
            walls.add(new SoftWall(1000, 1800+i*100));
            walls.add(new SoftWall(900, 1800+i*100));
        }
        //مربع بالای صفحه سمت چپ
        for(int i=0;i<5;i++){
            for(int j=0;j<19;j++){
                walls.add(new HardWall(1500+i*100,100+j*100));
            }
        }
        for(int i=0;i<5;i++){
            walls.add(new HardWall(900,2600+100*i));
        }
        walls.add(new Teazel(300, 600));
        walls.add(new Teazel(400, 600));
        walls.add(new Teazel(500, 600));
        walls.add(new Teazel(600, 600));
        for (int i = 0; i < 9; i++) {
            walls.add(new HardWall(100 + 100 * i, 2500));
        }
        for (int i = 0; i < 9; i++) {
            walls.add(new HardWall(100 + 100 * i, 900));
        }
        for (int i = 0; i < 5; i++) {
            walls.add(new HardWall(100 + 100 * 8, 900 - 100 * i));
        }
        for (int i = 0; i < 4; i++) {
            walls.add(new SoftWall(100 + 100 * 8, 400 - 100 * i));
        }
        for (int i = 0; i < 3; i++) {
            grasses.add(new Grass(100 * (i + 10), 800));
        }
        //بخش های مربوط به بالای مپ
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j != i; j++) {
                walls.add(new HardWall(100 + j * 100, 1000 + 100 * (9 - i)));
            }
        }

        //از این جا به بعد تکه دوم نقشه است یعنی از وست به پایین
        walls.add(new SoftWall(1500,3000));
        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++){
                walls.add(new SoftWall(100+100*i,3500+100*j));
            }
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
        if (GameFrame.inMenu) {
            guiSensourArea();
        } else {
            if (tank.isPause())
                return;
            mapMoving();
            tankHit();
            removingTrash();
            BuildingNewBullet();
            bulletHit();
            for (int i = 0; i < prizes.size(); i++) {
                prizes.get(i).task(tank);
            }
            soosk.update(tank);
        }
    }

    public ArrayList<MilitaryTool> getMilitaryTools() {
        return militaryTools;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }//تانک برخورد با دیوار

    public void tankHit() {

    }

    public ArrayList<Tir> getTirs() {
        return tirs;
    }

    public void mapMoving(){
        for (int i=0;i<tirs.size();i++){
            tirs.get(i).setLocX(tirs.get(i).getFirstX()-tirs.get(i).getTankDeltaX());
            tirs.get(i).setLocY(tirs.get(i).getFirstY()-tirs.get(i).getTankDeltaY());
            tirs.get(i).getRectangle().setLocation(tirs.get(i).getFirstX()-tirs.get(i).getTankDeltaX(),tirs.get(i).getFirstY()-tirs.get(i).getTankDeltaY());
        }
        for (int i = 0; i< militaryTools.size(); i++){
            militaryTools.get(i).locX= militaryTools.get(i).firstX-tank.getDeltaX();
            militaryTools.get(i).locY= militaryTools.get(i).firstY-tank.getDeltaY();
            militaryTools.get(i).rectangle.setLocation(militaryTools.get(i).getFirstX() - tank.getDeltaX(),militaryTools.get(i).getFirstY() - tank.getDeltaY());
        }
//        for (int i=0;i<buildings.size();i++){
//            buildings.get(i).setLocX(buildings.get(i).getFirstX()-tank.getDeltaX());
//            buildings.get(i).setLocY(buildings.get(i).getFirstY()-tank.getDeltaY());
//        }
        tank.getRectangle().setLocation(tank.getLocX(),tank.getLocY());
        for (int i=0;i<walls.size();i++){
            walls.get(i).setLocX(walls.get(i).getFirstX()-tank.getDeltaX());
            walls.get(i).setLocY(walls.get(i).getFirstY()-tank.getDeltaY());
            walls.get(i).rectangle.setLocation(walls.get(i).getFirstX() - tank.getDeltaX(),walls.get(i).getFirstY() - tank.getDeltaY());
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
                    tirs.add(new HeavyBullet(tank.locX+tank.getTank().getWidth()/2,tank.locY+tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
                            (double) (tank).getMouseX() - tank.locX-100,rotationRequired,tank,tank.getDeltaX(),tank.getDeltaY()));
                }
            }
            else {
                if(tank.getNumberCheapBullet() >0){
                    tirs.add(new CheapBullet(tank.locX+tank.getTank().getWidth()/2,tank.locY+tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
                            (double) (tank).getMouseX() - tank.locX-100,rotationRequired,tank,tank.getDeltaX(),tank.getDeltaY()));
                }
            }
            tank.setMouseRealised(false);
        }
        ArrayList<MilitaryTool>militaryTools = new ArrayList<MilitaryTool>();
        for (int i=0;i<this.militaryTools.size();i++)
            militaryTools.add(this.militaryTools.get(i));
        militaryTools.add(tank);


        for (int i=0;i<militaryTools.size();i++) {
            militaryTools.get(i).update(walls);
            militaryTools.get(i).update(tank);//update tank information
            if(militaryTools.get(i) instanceof TankAi || militaryTools.get(i) instanceof StaticTank){
                Tank temp = (Tank) militaryTools.get(i);
                if(Math.pow(tank.locY-militaryTools.get(i).locY,2)+Math.pow(tank.locX-militaryTools.get(i).locX,2) < militaryTools.get(i).tirRange) {
                    if (militaryTools.get(i).getCount() % 30 == 0) {
                        double rotationRequired = Math.atan((double) (tank.locY - militaryTools.get(i).locY) /
                                (double) Math.abs((tank.locX - militaryTools.get(i).locX)));
                        if (tank.locX - militaryTools.get(i).locX < 0 && tank.locY - militaryTools.get(i).locY > 0) {
                            rotationRequired = Math.PI - rotationRequired;
                        }
                        if (tank.locX - militaryTools.get(i).locX < 0 && tank.locY - militaryTools.get(i).locY < 0) {
                            rotationRequired = -Math.PI - rotationRequired;
                        }
                        tirs.add(new Tir(militaryTools.get(i).locX + militaryTools.get(i).getTank().getWidth() / 2, militaryTools.get(i).locY + militaryTools.get(i).getTank().getHeight() / 2, (double) (tank.locY - militaryTools.get(i).locY),
                                (double) tank.locX - militaryTools.get(i).locX, rotationRequired, temp, tank.getDeltaX(), tank.getDeltaY()));
                    }
                }
            }
        }
    }

    public void bulletHit(){
        ArrayList<MilitaryTool>militaryTools = new ArrayList<MilitaryTool>();
        militaryTools.add(tank);
        for (int i = 0; i< this.militaryTools.size(); i++)
            militaryTools.add(this.militaryTools.get(i));
        //tank hit
        for (int i=0;i<tirs.size();i++){
            tirs.get(i).update();
            tirs.get(i).setTankDeltaY(tank.getDeltaY()-tirs.get(i).getFirstTankY());
            tirs.get(i).setTankDeltaX(tank.getDeltaX()-tirs.get(i).getFirstTankX());
        }
        for (int i=0;i<tirs.size();i++) {
            boolean flag=false;
            if(!flag)
                for (int j=0;j<militaryTools.size();j++){
                    if(flag)
                        break;
                    if(militaryTools.get(j) != tirs.get(i).getSorce())
                        if (Math.pow(militaryTools.get(j).locX+militaryTools.get(j).tank.getWidth()/2  - tirs.get(i).getLocX() , 2) + Math.pow(militaryTools.get(j).locY + militaryTools.get(j).tank.getHeight()/2 - tirs.get(i).getLocY() - 29, 2) <4000) {
//                            System.out.println("ds");
//                            for (int k = 0; k < militaryTools.get(j).getTank().getWidth(); k++) {
//                                for (int l = 0; l < militaryTools.get(j).getTank().getHeight(); l++) {
//                                    for (int g = 0; g < tirs.get(i).getMoshak().getWidth(); g++) {
//                                        for (int h = 0; h <tirs.get(i).getMoshak().getHeight(); h++) {
//                                            if (tirs.get(i).getLocX() + g == militaryTools.get(j).locX + k && tirs.get(i).getLocY() + h == militaryTools.get(j).locY + l) {
//                                                if(tirs.get(i) instanceof HeavyBullet)
//                                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-100);
//                                                else   if(tirs.get(i) instanceof CheapBullet)//TODO
//                                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-50);
//                                                else
//                                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-100);
//                                                tirs.get(i).setDie(true);
//                                                flag = true;
//                                                break;
//                                            }
//                                        }
//                                        if (flag)
//                                            break;
//                                    }
//                                    if (flag)
//                                        break;
//                                }
//                                if (flag)
//                                    break;
//                            }
                            if(tirs.get(i).getRectangle().intersects(militaryTools.get(j).rectangle)){
                                if(tirs.get(i) instanceof HeavyBullet)
                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-100);
                                else   if(tirs.get(i) instanceof CheapBullet)//TODO
                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-50);
                                else
                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-100);
                                tirs.get(i).setDie(true);
                                flag = true;
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
                    if (Math.pow(walls.get(j).getLocX() + 50 - tirs.get(i).getLocX() - 63, 2) + Math.pow(walls.get(j).getLocY() + 50 - tirs.get(i).getLocY() - 29, 2) <32000) {
                        if(tirs.get(i).getRectangle().intersects(walls.get(j).getRectangle())) {
                            if (walls.get(j) instanceof SoftWall) {
                                if (tirs.get(i) instanceof HeavyBullet)
                                    walls.get(j).setHealth(walls.get(j).getHealth() - 100);
                                if (tirs.get(i) instanceof CheapBullet)
                                    walls.get(j).setHealth(walls.get(j).getHealth() - 50);
                                else
                                    walls.get(j).setHealth(walls.get(j).getHealth() - 100);
                            }
                            if (!(walls.get(j) instanceof Teazel))
                                tirs.get(i).setDie(true);
                            flag = true;
                        }
                        if(flag)
                            break;
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
        Iterator iterator2 = militaryTools.iterator();
        while (iterator2.hasNext()){
            MilitaryTool militaryTool = (MilitaryTool) iterator2.next();
            if(militaryTool.isDead())
                iterator2.remove();
        }

    }

    public TankHuman getTank() {
        return tank;
    }


    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public static Boolean distance(int x, int y, int ox, int oy, int acceptable) {
        if ((x - ox) * (x - ox) + (y - oy) * (y - oy) < (acceptable * acceptable)) {
//            System.out.println(true);
            return true;
        } else {
            return false;
        }
    }

    public void guiSensourArea() {
        int mouseNowX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseNowY = MouseInfo.getPointerInfo().getLocation().y;
//        System.out.println(mouseNowX+"  "+mouseNowY);
        if (mousePress) {
            locY = mouseY - diam / 2;
            locX = mouseX - diam / 2;
        }
        if (distance(170, 105, mouseNowX, mouseNowY, 68)) {
            GameFrame.start = true;
        } else {
            GameFrame.start = false;
        }
        if (distance(307, 105, mouseNowX, mouseNowY, 65)) {
            GameFrame.join = true;
        } else {
            GameFrame.join = false;
        }
        if (distance(465, 105, mouseNowX, mouseNowY, 65)) {
            GameFrame.server = true;
        } else {
            GameFrame.server = false;
        }
        if (distance(307, 105, mouseNowX, mouseNowY, 65)) {
            GameFrame.join = true;
        } else {
            GameFrame.join = false;
        }
        if (distance(720, 500, mouseNowX, mouseNowY, 65)) {
            GameFrame.eseay = true;
//            System.out.println(true);
        } else {
            GameFrame.eseay = false;
        }
        if (distance(980, 500, mouseNowX, mouseNowY, 80)) {
            GameFrame.meduim = true;
            System.out.println(true);
        } else {
            GameFrame.meduim = false;
        }
        if (distance(1300, 500, mouseNowX, mouseNowY, 65)) {
            GameFrame.hard = true;
//            System.out.println(true);
        } else {
            GameFrame.hard = false;
        }
    }
}

