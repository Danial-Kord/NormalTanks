package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TankHuman extends MovableTank{

    private boolean mousePress,mouseRealised;
    public boolean gameOver;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private boolean stop;
    ////////////////
    private int tirSpeed;
    private boolean pause;
    /////////////////////
    public TankHuman(int locX,int locY){
        super(locX,locY);
        pause=false;
        gameOver = false;
        mousePress = false;
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        stop = false;
        tirSpeed = 20;
    }

    public void setTirSpeed(int tirSpeed) {
        this.tirSpeed = tirSpeed;
    }

    public int getTirSpeed() {
        return tirSpeed;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public KeyListener getKeyListener() {
        return keyHandler;
    }
    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }

    public void setMouseRealised(boolean mouseRealised) {
        this.mouseRealised = mouseRealised;
    }

    public boolean isMouseRealised() {
        return mouseRealised;
    }
    public void update(ArrayList<Wall> walls) {
        super.update(walls);
        up =false;
        down=false;
        left=false;
        right=false;
        if (keyUP ) {
            if(locY-1>80)
            locY -= 1;
            deltaY+=-4;
            if(check(walls,"down")) {
                deltaY += 4;
                if(locY-1>80)
                locY += 1;
            }
        }
        if (keyDOWN ) {
            if(locY+1<816)
            locY +=1;
            deltaY+=4;
            if(check(walls,"up")) {
                deltaY += -4;
                if(locY+1<816)
                locY -=1;
            }
        }
        if (keyLEFT ) {
            if(locX-1>200)
            locX -= 1;
            deltaX+=-4;
            if(check(walls,"right")) {
                deltaX += +4;
                if(locX-1>200)
                locX += 1;
            }
        }
        if (keyRIGHT ) {
            if(locX+1<1400)
            locX += 1;
            deltaX+=4;
            if(check(walls,"left")) {
                deltaX += -4;
                if(locX+1<1400)
                locX -= 1;
            }
        }


//
//        if(up && keyDOWN){
//            locY-=1;
//            deltaY+=-8;
//        }
//
//        if(down && keyUP){
//            locY+= 1;
//            deltaY+=8;
//        }
//        if(right && keyLEFT){
//            locX+= 1;
//            deltaX+=8;
//        }
//        if(left && keyRIGHT) {
//            locX -= 1;
//            deltaX += -8;
//        }


//        if(!keyDOWN || !keyUP)
//            deltaY+=0;
//        if(!keyLEFT || !keyRIGHT)
//            deltaX+=0;
//        locX = Math.max(locX, 0);
//        locX = Math.min(locX, GameFrame.GAME_WIDTH );
//        locY = Math.max(locY, 0);
//        locY = Math.min(locY, GameFrame.GAME_HEIGHT);
        mouseX = MouseInfo.getPointerInfo().getLocation().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y;
//        up=false;
//        down=false;
//        left=false;
//        right=false;
    }
//    public boolean check(ArrayList<Wall>walls,ArrayList<Tank>tanks,String jahat){
//        if(super.check(walls,jahat))
//        {
//
//        }
////            if (Math.pow(x1 + 50 - this.getLocX() - this.getTank().getWidth() / 2, 2) + Math.pow(y1 + 50
////                    - this.getLocY() - this.getTank().getHeight() / 2, 2) < 32000) {
//        }
//        return false;
//    }
    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if(dead)
            gameOver=true;
    }
    public boolean isMousePress() {
        return mousePress;
    }
    class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
                    ///////////
                case KeyEvent.VK_ESCAPE:
                    pause = !pause;
                    break;
                    /////////////
            }
        }

    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 3){

                if(stateHeavyGun==true){
                    stateHeavyGun=false;
                    try {
                        looleh= ImageIO.read(new File("Src//Images//tankGun02.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    stateHeavyGun=true;
                    try {
                        looleh= ImageIO.read(new File("Src//looleh.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }else{
                mouseX = e.getX();
                mouseY = e.getY();
                mousePress = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
            if(e.getButton() != 3)
            mouseRealised = true;
            mouseX = e.getX();
            mouseY = e.getY();
//            System.out.println(""+mouseX +".............." +mouseY);
//            System.out.println();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
}
