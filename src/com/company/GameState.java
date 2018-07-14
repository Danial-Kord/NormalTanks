package com.company;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
    private Changes changes;
    private ArrayList<MilitaryTool>militaryTools = new ArrayList<MilitaryTool>();
    public Soosk soosk = new Soosk(1000,200);
    private ArrayList<TankHuman>tankHumens;
    public ArrayList<Prizes> getPrizes() {
        return prizes;
    }

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;
    //متغیر های مربوط به گویی
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    public int locX, locY, diam;
    public static boolean gameOver;
    private boolean mousePress;
    private int mouseX, mouseY;
    private static boolean changed;
    private boolean win = false;
    /////////////////
    private boolean client=false;
    private boolean server=false;
    public static ArrayList<MilitaryTool>changedMilitaries = new ArrayList<MilitaryTool>();//TODO
    public static ArrayList<Building>changedBuildings = new ArrayList<Building>();
    public static ArrayList<Tir>changedTirs = new ArrayList<Tir>();
    ///////////////////////
    public GameState() {
        tankHumens = new ArrayList<TankHuman>();
        locX = 100;
        locY = 100;
        diam = 32;
        gameOver = false;
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        mouseHandler = new MouseHandler();
        keyHandler = new KeyHandler();
        changes = new Changes();
        //
        // Initialize the game state and all elements ...
        //
        buildings = new ArrayList<Building>();
        grasses = new ArrayList<Grass>();
        walls = new ArrayList<Wall>();
        tank = new TankHuman(1200, 800);
        tank.setName("me");
        tankHumens.add(tank); 
        tirs = new ArrayList<Tir>();
        tanks = new ArrayList<Tank>();
        prizes.add(new Canonshells(1400, 860));
        prizes.add(new Machingun(1400, 890));
        prizes.add(new Repair(1400, 940));
        prizes.add(new Star(1400, 960));
        prizes.add(new Star(500,3500));
        mapCreator();
        for (int i=0;i<tanks.size();i++)
            militaryTools.add(tanks.get(i));
        militaryTools.add(soosk);
        soosk.setName(soosk.getKind());
        /////////////////////////////////
        for (int i = 0; i < walls.size(); i++)
            buildings.add(walls.get(i));
        for (int i = 0; i < prizes.size(); i++)
            buildings.add(prizes.get(i));
        for (int i = 0; i < grasses.size(); i++)
            buildings.add(grasses.get(i));
    }

    ///////////////////////////
//    public GameState(Changes changes){
//        client=true;
//        this.changes = changes;
//        buildings = new ArrayList<Building>();
//        grasses = new ArrayList<Grass>();
//        walls = new ArrayList<Wall>();
//        tank = new TankHuman(1200, 600);
//        tirs = new ArrayList<Tir>();
//        tanks = new ArrayList<Tank>();
//        //building building!!!
//        firstBuilding(changes.getBuildings());
//        for (int i = 0; i < walls.size(); i++)
//            buildings.add(walls.get(i));
//        for (int i = 0; i < prizes.size(); i++)
//            buildings.add(prizes.get(i));
//        for (int i = 0; i < grasses.size(); i++)
//            buildings.add(grasses.get(i));
//        //////////////
//
//    }

    public ArrayList<TankHuman> getTankHumens() {
        return tankHumens;
    }

    public static void setChanged(boolean changed) {
        GameState.changed = changed;
    }

    public static boolean isChanged() {
        return changed;
    }

    public void updateOnlineChanges(){
//        for (int i=0;i<changes.getRecivedBuildings().size();i++){
//            for (int j=0;j<this.buildings.size();j++) {
//                if (changes.getRecivedBuildings().get(i).getFirstX() == this.buildings.get(j).getFirstX() && changes.getRecivedBuildings().get(i).getFirstY() == this.buildings.get(j).getFirstY()){
//                        this.buildings.get(j).setHealth(changes.getRecivedBuildings().get(i).getHealth());
//                    this.buildings.get(j).setDie(changes.getRecivedBuildings().get(i).isDie());
//                }
//            }
//        }
        for(int i=0;i<changes.getRecivedTirs().size();i++){
            //TODO
            Tank temp = null;
            for (int j=0;j<militaryTools.size();j++){
                if(militaryTools.get(j).getName().equals(changes.getRecivedTirs().get(i).getTankSorce()))
                    temp = (Tank) militaryTools.get(j);
            }
            if(changes.getRecivedTirs().get(i).getKind().equals("heavy")){
                //TODO
//                this.tirs.add(new HeavyBullet(changes.getRecivedTirs().get(i).getFirstX(),changes.getRecivedTirs().get(i).getFirstY(),changes.getRecivedTirs().get(i).getDelytaY(),changes.getRecivedTirs().get(i).getDeltaX(),
//                        changes.getRecivedTirs().get(i).getShib(),temp,changes.getRecivedTirs().get(i).getFirstTankX(),changes.getRecivedTirs().get(i).getFirstTankY()));
                this.tirs.add(new HeavyBullet(changes.getRecivedTirs().get(i).getFirstX(),changes.getRecivedTirs().get(i).getFirstY(),changes.getRecivedTirs().get(i).getDelytaY(),changes.getRecivedTirs().get(i).getDeltaX(),
                        changes.getRecivedTirs().get(i).getShib(),temp,tank.getDeltaX(),tank.getDeltaY()));

                SoundsHandeler.playSoundInGame(new File("music\\heavygun.wav").getAbsoluteFile());
                //این جا تیر معمولی خودمون
            }
            if(changes.getRecivedTirs().get(i).getKind().equals("cheap")){
                this.tirs.add(new CheapBullet(changes.getRecivedTirs().get(i).getFirstX(),changes.getRecivedTirs().get(i).getFirstY(),changes.getRecivedTirs().get(i).getDelytaY(),changes.getRecivedTirs().get(i).getDeltaX(),
                        changes.getRecivedTirs().get(i).getShib(),temp,changes.getRecivedTirs().get(i).getFirstTankX(),changes.getRecivedTirs().get(i).getFirstTankY()));
                SoundsHandeler.playSoundInGame(new File("music\\lightgun.wav").getAbsoluteFile());
                //این جا ماشین گان خودمون
            }
        }
        for (int i=0;i<changes.getRecivedMilitaryTools().size();i++) {
            for (int j = 0; j < militaryTools.size(); j++) {
                //TODO client ya server masaleh in ast !!!! :/
                System.out.println(i);
                if (changes.getRecivedMilitaryTools().get(i).getName().equals(militaryTools.get(j).getName()) && !changes.getRecivedMilitaryTools().get(i).getName().equals(tank.getName())){
                    militaryTools.get(j).setHealth(changes.getRecivedMilitaryTools().get(i).getHealth());
                    militaryTools.get(j).setRotate(changes.getRecivedMilitaryTools().get(i).getRotate());
                    militaryTools.get(j).setFirstX(changes.getRecivedMilitaryTools().get(i).getFirstX());
                    militaryTools.get(j).setFirstY(changes.getRecivedMilitaryTools().get(i).getFirstY());
//                    if(changes.getMilitaryTools().get(i).getName().equals("server"))
//                        militaryTools.get(j)
                }
            }
        }
    }

    public void setServer(boolean server) {
        this.server = server;
        if(server) {
            tank = new TankHuman(1200, 800);
            tank.setName("server");
            tankHumens = new ArrayList<TankHuman>();
            tankHumens.add(tank);
            TankHuman tank2 = new TankHuman(1200,500);
            tank2.setName("client");
            militaryTools.add(tank2);
        }
    }

    public void setClient(boolean client) {
        this.client = client;
        if(client) {
            tank = new TankHuman(1200, 500);
            tank.setName("client");
            tankHumens = new ArrayList<TankHuman>();
            tankHumens.add(tank);
            TankHuman tank2 = new TankHuman(1200,800);
            tank2.setName("server");
            militaryTools.add(tank2);
        }
    }

    ///////////////////////////
    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }

    public boolean isWin() {
        return win;
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
                    GameFrame.serverImage = false;
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
        if (GameFrame.serverImage) {
            GameFrame.serverClick = true;
        }
        if (GameFrame.join) {
            GuiConnection guiConnection = new GuiConnection(this);
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
        if(GameFrame.resumeGame){

        }
    }

    public Changes getChanges() {
        return changes;
    }

    /**
     * The method which updates the game state.
     */
    private void mapCreator() {
            tanks.add(new TankAi(300, 100, 100, 0, 1000000));
            tanks.get(tanks.size() - 1).setName(tanks.get(tanks.size() - 1).kind);
        tanks.add(new TankAi(300, 600, 200, 20, 1000000));
        tanks.get(tanks.size() - 1).setName(tanks.get(tanks.size() - 1).kind);
            tanks.add(new TankAi(500,2100,200,200,1000000));
           tanks.get(tanks.size() - 1).setName(tanks.get(tanks.size() - 1).kind);
           tanks.add(new SimpleStaticTank(100, 2800, 1000000));
            tanks.get(tanks.size() - 1).setName(tanks.get(tanks.size() - 1).kind);
            tanks.add(new PoisenosStaticTank(100, 2200, 1000000));
            tanks.get(tanks.size() - 1).setName(tanks.get(tanks.size() - 1).kind);
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
//        walls.add(new Teazel(300, 600));
//        walls.add(new Teazel(400, 600));
//        walls.add(new Teazel(500, 600));
//        walls.add(new Teazel(600, 600));
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
        for(int i=0;i<19;i++){
            for(int j=0;j<8;j++){
                walls.add(new SoftWall(100+100*i,3500+100*j));
            }
        }
        for(int i=0 ;i<19;i++){
            walls.add(new HardWall(100+100*i,4200));
        }
    }

    public void setChanges(Changes changes) {
        this.changes = changes;
    }

    public ArrayList<Grass> getGrasses() {
        return grasses;
    }

    public void update() {
        changed = false;
        //
        // Update the state of all game elements
        //  based on user input and elapsed time ...
        //
//        tank.setStop(false);

        if (GameFrame.inMenu) {
            guiSensourArea();
        }
        else if(GameFrame.pause){
            return;
        }
        else {
            if(militaryTools.size()==0){
                win = true;
                return;
            }
            if(militaryTools.size()==1){
                if(militaryTools.get(0).getName().equals("server") || militaryTools.get(0).getName().equals("client"))
                    win=true;
                return;
            }
            if(server || client){
                updateOnlineChanges();
//                changes.removeRecivedBuilding();
                changes.removeRecivedTirs();
                changes.removeRecivedMilitarytools();
//            changes.removeAll();
            }
            mapMoving();
            tankHit();
            removingTrash();
            BuildingNewBullet();
            bulletHit();

            for (int i = 0; i < prizes.size(); i++) {
                prizes.get(i).task(tank);
            }
            soosk.update(tank);
                if(server || client){
                    changes.update(changedBuildings,changedTirs,tank,this);
                    //TODO
                    changedBuildings = new ArrayList<Building>();
                    changedTirs = new ArrayList<Tir>();
                }
        }

    }

    public boolean isServer() {
        return server;
    }

    public boolean isClient() {
        return client;
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

    public static ArrayList<Building> getChangedBuildings() {
        return changedBuildings;
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
                    changedTirs.add(tirs.get(tirs.size()-1));
                    SoundsHandeler.playSoundInGame(new File("music\\heavygun.wav").getAbsoluteFile());
                    //این جا تیر معمولی خودمون
                }
            }
            else {
                if(tank.getNumberCheapBullet() >0){
                    tirs.add(new CheapBullet(tank.locX+tank.getTank().getWidth()/2,tank.locY+tank.getTank().getHeight()/2,(double) (tank.getMouseY() - tank.locY-100),
                            (double) (tank).getMouseX() - tank.locX-100,rotationRequired,tank,tank.getDeltaX(),tank.getDeltaY()));
                    tirs.get(tirs.size()-1).setTankSorce(tank.name);
                    changedTirs.add(tirs.get(tirs.size()-1));
                    SoundsHandeler.playSoundInGame(new File("music\\lightgun.wav").getAbsoluteFile());
                    //این جا ماشین گان خودمون
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
            if(!client)
            if(militaryTools.get(i) instanceof TankAi || militaryTools.get(i) instanceof SimpleStaticTank){
                Tank temp = (Tank) militaryTools.get(i);
                if(Math.pow(tank.locY-militaryTools.get(i).locY,2)+Math.pow(tank.locX-militaryTools.get(i).locX,2) < militaryTools.get(i).tirRange) {
                    if (militaryTools.get(i).getCount() % 30 == 0) {
                        double rotationRequired = Math.atan((double) (tank.locY+tank.getTank().getWidth()/2 - militaryTools.get(i).locY) /
                                (double) Math.abs((tank.locX +tank.getTank().getHeight()/2 - militaryTools.get(i).locX)));
                        if (tank.locX+tank.getTank().getWidth()/2  - militaryTools.get(i).locX < 0 && tank.locY+tank.getTank().getHeight()/2 - militaryTools.get(i).locY > 0) {
                            rotationRequired = Math.PI - rotationRequired;
                        }
                        if (tank.locX+tank.getTank().getWidth()/2  - militaryTools.get(i).locX < 0 && tank.locY+tank.getTank().getHeight()/2 - militaryTools.get(i).locY < 0) {
                            rotationRequired = -Math.PI - rotationRequired;
                        }
                        tirs.add(new Tir(militaryTools.get(i).locX + militaryTools.get(i).getTank().getWidth() / 2, militaryTools.get(i).locY + militaryTools.get(i).getTank().getHeight() / 2, (double) (tank.locY+ militaryTools.get(i).getTank().getHeight() / 2 - militaryTools.get(i).locY),
                                (double) tank.locX+ militaryTools.get(i).getTank().getWidth() / 2 - militaryTools.get(i).locX, rotationRequired, temp, tank.getDeltaX(), tank.getDeltaY()));
                        tirs.get(tirs.size()-1).setTankSorce(militaryTools.get(i).getName());
                        changedTirs.add(tirs.get(tirs.size()-1));
                        //تانک دشمن
                        SoundsHandeler.playSoundInGame(new File("music\\enemyshot.wav"));

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
                    if(militaryTools.get(j) instanceof Soosk) {
                        Soosk temp =(Soosk) (militaryTools.get(j));
                        if (!temp.isActive())
                            continue;
                    }

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
                                   if(tirs.get(i) instanceof CheapBullet)//TODO
                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-50);
                                if(!(tirs.get(i) instanceof HeavyBullet) && !(tirs.get(i) instanceof CheapBullet))
                                    militaryTools.get(j).setHealth(militaryTools.get(j).getHealth()-100);
                                //TODO
                                changedMilitaries.add(militaryTools.get(j));
                                tirs.get(i).setDie(true);
                                flag = true;
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
                                changedBuildings.add(walls.get(j));
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
            GameFrame.serverImage = true;
        } else {
            GameFrame.serverImage = false;
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
        } else {
            GameFrame.meduim = false;
        }
        if (distance(1300, 500, mouseNowX, mouseNowY, 65)) {
            GameFrame.hard = true;
//            System.out.println(true);
        } else {
            GameFrame.hard = false;
        }
         if(distance(610,131,mouseNowX,mouseNowY,60)){
            GameFrame.resumeGame = true;
         }
         else {
            GameFrame.resumeGame = false;
         }

    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        GameState.gameOver = gameOver;
    }
}

