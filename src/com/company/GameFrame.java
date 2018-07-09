/*** In The Name of Allah ***/
package com.company;

import javafx.scene.shape.QuadCurve;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * The window on which the rendering is performed.
 * This structure uses the modern BufferStrategy approach for
 * double-buffering; actually, it performs triple-buffering!
 * For more information on BufferStrategy check out:
 * http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 * http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();                  // 720p game resolution
    public static final int GAME_WIDTH  = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(); //16 * GAME_HEIGHT / 9;  // wide aspect ratio
    private long lastRender;
    private ArrayList<Float> fpsHistory;
    private BufferStrategy bufferStrategy;
    private static File photoHeavy=new File("src\\Images\\NumberOfHeavyBullet2.png");
    private static File photoCheap=new File("src\\Images\\NumberOfMachinGun2.png");
    private static File heart=new File("heart.png");
    private BufferedImage  soili ;

    public GameFrame(String title) {
        super(title);
        try {
            soili=ImageIO.read(new File("src\\Images\\Area.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);
        //
        // Initialize the JFrame ...
        //
    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        // Get a new graphics context to render the current frame
        Graphics2D graphics = null;
        try {
            graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
            // Do the rendering
            doRendering(graphics, state);
            // Dispose the graphics, because it is no more needed
            graphics.dispose();
        } catch (NullPointerException e) {
            e.getMessage();
        }

        // Display the buffer
        bufferStrategy.show();
        // Tell the system to do the drawing NOW;
        // otherwise it can take a few extra ms and will feel jerky!
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private int j = 0;

    private void doRendering(Graphics2D g2d, GameState state) {

        //
        // Draw all game elements according
        //  to the game 'state' using 'g2d' ...

//        g2d.setColor(Color.GRAY);
//        g2d.setClip((Shape) new QuadCurve());
        g2d.fillRect(0, 0, GAME_WIDTH + state.getTank().locX, GAME_HEIGHT + state.getTank().locY);
//        g2d.setClip(state.getTank().locX,state.getTank().locY, GAME_WIDTH-state.getTank().locX, GAME_HEIGHT-state.getTank().locY);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src\\Images\\Area.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        paintBackGround(g2d,state);
        ///////////////////////////////////////////////////////////////////
        double rotationRequired = Math.atan((double) (state.getTank().getMouseY() - state.getTank().locY - 100) /
                (double) Math.abs(state.getTank().getMouseX() - state.getTank().locX - 100));

        if (state.getTank().getMouseX() - state.getTank().locX - 100 < 0 && state.getTank().getMouseY() - state.getTank().locY - 100 > 0) {
            rotationRequired = Math.PI - rotationRequired;
        }
        if (state.getTank().getMouseX() - state.getTank().locX - 100 < 0 && state.getTank().getMouseY() - state.getTank().locY - 100 < 0) {
            rotationRequired = -Math.PI - rotationRequired;
        }
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 100, 100);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        if (j % 200 == 0) {
        }
        j++;
// Drawing the rotated image at the required drawing locations
        if (!state.getTank().gameOver) {
            if ((state.getTank().isKeyUP() && state.getTank().isKeyRIGHT()) || (state.getTank().isKeyDOWN() && state.getTank().isKeyLEFT())) {
                double rotationRequired3 = -Math.PI / 4;
                AffineTransform tx3 = AffineTransform.getRotateInstance(rotationRequired3, 100, 100);
                AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
                g2d.drawImage(op3.filter(state.getTank().getTank(), null), state.getTank().locX, state.getTank().locY, null);
            } else if ((state.getTank().isKeyUP() && state.getTank().isKeyLEFT()) || (state.getTank().isKeyDOWN() && state.getTank().isKeyRIGHT())) {
                double rotationRequired3 = Math.PI / 4;
                AffineTransform tx3 = AffineTransform.getRotateInstance(rotationRequired3, 100, 100);
                AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
                g2d.drawImage(op3.filter(state.getTank().getTank(), null), state.getTank().locX, state.getTank().locY, null);
            } else if (state.getTank().isKeyUP() || state.getTank().isKeyDOWN()) {
                double rotationRequired3 = Math.PI / 2;
                AffineTransform tx3 = AffineTransform.getRotateInstance(rotationRequired3, 100, 100);
                AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
                g2d.drawImage(op3.filter(state.getTank().getTank(), null), state.getTank().locX, state.getTank().locY, null);
            } else {
                double rotationRequired3 = 0;
                AffineTransform tx3 = AffineTransform.getRotateInstance(rotationRequired3, 100, 100);
                AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
                g2d.drawImage(op3.filter(state.getTank().getTank(), null), state.getTank().locX, state.getTank().locY, null);
            }
        }
        //enemy tanks
        for (int i = 0; i < state.getTanks().size(); i++) {
            double rotate = Math.atan((double) (state.getTank().locY - state.getTanks().get(i).locY) /
                    (double) Math.abs(state.getTank().locX - state.getTanks().get(i).locX));
            if (state.getTank().locX - state.getTanks().get(i).locX < 0 && state.getTank().locY - state.getTanks().get(i).locY > 0) {
                rotate = Math.PI - rotate;
            }
            if (state.getTank().locX - state.getTanks().get(i).locX < 0 && state.getTank().locY - state.getTanks().get(i).locY < 0) {
                rotate = -Math.PI - rotate;
            }
            AffineTransform tx2 = AffineTransform.getRotateInstance(rotate, 100, 100);
            AffineTransformOp op3 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
            g2d.drawImage(state.getTanks().get(i).getTank(), state.getTanks().get(i).locX, state.getTanks().get(i).locY, null);
            if (state.getTanks().get(i) instanceof StaticTank){
                g2d.drawImage(op3.filter(state.getTanks().get(i).getLooleh(), null), state.getTanks().get(i).locX - 24, state.getTanks().get(i).locY - 24, null);
        }
                else {
                g2d.drawImage(op3.filter(state.getTanks().get(i).getLooleh(), null), state.getTanks().get(i).locX, state.getTanks().get(i).locY, null);
            }
        }


        for (int i = 0; i < state.getTirs().size(); i++) {
            double rotationRequired1 = state.getTirs().get(i).getShib();
            AffineTransform tx1 = AffineTransform.getRotateInstance(rotationRequired1, 35, 35);
            AffineTransformOp op1 = new AffineTransformOp(tx1, AffineTransformOp.TYPE_BILINEAR);
            g2d.drawImage(op1.filter(state.getTirs().get(i).moshak, null), state.getTirs().get(i).getLocX(), state.getTirs().get(i).getLocY(), null);
        }
        g2d.drawImage(op.filter(state.getTank().getLooleh(), null), state.getTank().locX, state.getTank().locY, null);

        for (int i = 0; i < state.getWalls().size(); i++) {
            g2d.drawImage(state.getWalls().get(i).getMoshak(), state.getWalls().get(i).getLocX()
                    , state.getWalls().get(i).getLocY(), null);

        }
        for(int i=0;i<state.getGrasses().size();i++){
            g2d.drawImage(state.getGrasses().get(i).getGrass(), state.getGrasses().get(i).getX()
                    , state.getGrasses().get(i).getY(), null);
        }

        // Print FPS info
        long currentRender = System.currentTimeMillis();
        if (lastRender > 0) {
            fpsHistory.add(1000.0f / (currentRender - lastRender));
            if (fpsHistory.size() > 100) {
                fpsHistory.remove(0); // remove oldest
            }
            float avg = 0.0f;
            for (float fps : fpsHistory) {
                avg += fps;
            }
            avg /= fpsHistory.size();
            String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
                    avg, (currentRender - lastRender));
            g2d.setColor(Color.CYAN);

            g2d.setFont(g2d.getFont().deriveFont(18.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            int strHeight = g2d.getFontMetrics().getHeight();
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight + 50);
        }
        lastRender = currentRender;
        // Print user guide
        String userGuide
                = "Use the MOUSE or ARROW KEYS to move the BALL. "
                + "Press ESCAPE to end the game.";
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
        // Draw GAME OVER
        if (state.getTank().gameOver) {
            String str = "GAME OVER";
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
        }
        try {
            g2d.drawImage(ImageIO.read(photoHeavy), 5, 40, this);
            g2d.drawImage(ImageIO.read(photoCheap), 5, 100, this);
            String str = state.getTank().getTankBulletNumber();
            g2d.setColor(Color.yellow);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(30.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, 35, 100);
            String str2 = state.getTank().getTankCheapBullet();
            g2d.setColor(Color.yellow);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(30.0f));
            strWidth = g2d.getFontMetrics().stringWidth(str2);
            g2d.drawString(str2, 35, 170);
            /////////////////////////////////////////////////////////
//            healthPaint(g2d,state);
            //////////////////////////////////////////////////
//            g2d.drawString(str, 8, 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //////////////////////////////////
        healthPaint(g2d,state);

    }
/////////////////////////////////////////////////////////////////////////////////
    /**
     * this method paint some heart for you
     * @param g2d for painting
     * @param state it is game state for access to out tank in game
     */
    private void healthPaint(Graphics2D g2d,GameState state){
        for(int i=0;i<state.getTank().getHealth();i++){
            try {
                g2d.drawImage(ImageIO.read(heart), GAME_WIDTH-40-40*i, 30, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * this method paint soil in the back ground
     * @param g2d
     */
    private void paintBackGround(Graphics2D g2d,GameState state){
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        for(int i=0;i<30;i++)
            for (int j=0;j<20;j++) {
                    g2d.drawImage(soili, (int) 85*i, (int) 91*j, this);
            }
    }

/////////////////////////////////////////////////////////////////////////
}
