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
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
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

    //این متغیر ها برای شیف پیدا کردن گویی و بازی هستند
    public static Boolean inMenu = true;

    /////
    public static final int GAME_HEIGHT = 1000;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
    private long lastRender;
    private ArrayList<Float> fpsHistory;
    private BufferStrategy bufferStrategy;
    private static File photoHeavy = new File("src\\Images\\NumberOfHeavyBullet2.png");
    private static File photoCheap = new File("src\\Images\\NumberOfMachinGun2.png");
    private static File heart = new File("heart.png");
    private BufferedImage soili;
    //بولین های مربوط به گویی
    private int num = 0;
    public static Boolean loneWolfClick = false;
    public static Boolean start = false;
    public static Boolean join = false;
    public static Boolean server = false;
    public static Boolean eseay = false;
    public static Boolean meduim = false;
    public static Boolean hard = false;
    public static Boolean serverClick = false;
    //عکس های نیو شده در گویی
    private static Image tank;
    private static Image tank33;
    private static Image starDark;
    private static Image internetDark;
    private static Image serverNetworkDark;
    private static Image outBreak;
    private static Image silver;
    private static Image startImage;
    private static Image startImage2;
    private static Image outBreakDark;
    private static Image copper;
    private static Image internetImage;
    private static Image copperLight;
    private static Image silverLight;
    private static Image internetImage2;

    public GameFrame(String title) {
        super(title);
        setImage();
        try {
            soili = ImageIO.read(new File("src\\Images\\Area.png"));
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
            /////////

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

        if(inMenu){
            guiSystem(g2d);
            return;
        }
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

        //enemy tanks
        for (int i = 0; i < state.getMilitaryTools().size(); i++) {
            double rotate = Math.atan((double) (state.getTank().locY - state.getMilitaryTools().get(i).locY) /
                    (double) Math.abs(state.getTank().locX - state.getMilitaryTools().get(i).locX));
            if (state.getTank().locX - state.getMilitaryTools().get(i).locX < 0 && state.getTank().locY - state.getMilitaryTools().get(i).locY > 0) {
                rotate = Math.PI - rotate;
            }
            if (state.getTank().locX - state.getMilitaryTools().get(i).locX < 0 && state.getTank().locY - state.getMilitaryTools().get(i).locY < 0) {
                rotate = -Math.PI - rotate;
            }
            AffineTransform tx2 = AffineTransform.getRotateInstance(rotate, 100, 100);
            AffineTransformOp op3 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
            if (state.getMilitaryTools().get(i) instanceof PoisenosStaticTank){
                PoisenosStaticTank temp = (PoisenosStaticTank) state.getMilitaryTools().get(i);
                g2d.drawImage(temp.getRedZone(), state.getMilitaryTools().get(i).locX, state.getMilitaryTools().get(i).locY , null);
            }
            g2d.drawImage(state.getMilitaryTools().get(i).getTank(), state.getMilitaryTools().get(i).locX, state.getMilitaryTools().get(i).locY, null);
            if (state.getMilitaryTools().get(i) instanceof SimpleStaticTank){
                Tank temp = (StaticTank)state.getMilitaryTools().get(i);
                g2d.drawImage(op3.filter(temp.getLooleh(), null), state.getMilitaryTools().get(i).locX - 24, state.getMilitaryTools().get(i).locY - 24, null);
            }
            else
            if(state.getMilitaryTools().get(i) instanceof Tank){
                Tank temp = (Tank) state.getMilitaryTools().get(i);
                g2d.drawImage(op3.filter(temp.getLooleh(), null), state.getMilitaryTools().get(i).locX, state.getMilitaryTools().get(i).locY, null);
            }
        }
//        if(!state.soosk.dead)
//        g2d.drawImage(state.soosk.getImage(), state.soosk.getLocX(), state.soosk.getLocY(), null);
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
            g2d.drawImage(state.getGrasses().get(i).getGrass(), state.getGrasses().get(i).getLocX()
                    , state.getGrasses().get(i).getLocY(), null);
        }

        for (int i = 0; i < state.getPrizes().size(); i++) {
            if (state.getPrizes().get(i).getVisible()) {
                g2d.drawImage(state.getPrizes().get(i).image, state.getPrizes().get(i).getLocX(), state.getPrizes().get(i).getLocY(), this);
            }
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
     *
     * @param g2d   for painting
     * @param state it is game state for access to out tank in game
     */
    private void healthPaint(Graphics2D g2d, GameState state) {
        int i = 0;
        for (i = 0; i * 100 <= state.getTank().getHealth(); i++) {
            try {
                g2d.drawImage(ImageIO.read(heart), GAME_WIDTH - 40 - 40 * i, 30, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str2 = "" + state.getTank().getHealth();
        g2d.setColor(Color.yellow);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(30.0f));
        int strWidth = g2d.getFontMetrics().stringWidth(str2);
        g2d.drawString(str2, GAME_WIDTH - 70 - 40 * i, 60);
    }

    /**
     * this method paint soil in the back ground
     *
     * @param g2d
     */

    private void paintBackGround(Graphics2D g2d, GameState state) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 20; j++) {
                g2d.drawImage(soili, (int) 85 * i, (int) 91 * j, this);
            }
    }

    /**
     * this method make all photos for all of the variable
     * help speed of the program
     */
    private void setImage() {
        try {
            tank = ImageIO.read(new File("tank.jpg"));
            tank33 = ImageIO.read(new File("tank33.png"));
            starDark = ImageIO.read(new File("startdark.png"));
            internetDark = ImageIO.read(new File("internetdark.png"));
            serverNetworkDark = ImageIO.read(new File("server_networkdark.png"));
            outBreak = ImageIO.read(new File("outbreak.png"));
            silver = ImageIO.read(new File("silver.png"));
            startImage = ImageIO.read(new File("start.png"));
            startImage2 = ImageIO.read(new File("start2.png"));
            outBreakDark = ImageIO.read(new File("outbreakdark.png"));
            copper = ImageIO.read(new File("copper.png"));
            internetImage = ImageIO.read(new File("internet.png"));
            copperLight = ImageIO.read(new File("copperLight.png"));
            silverLight = ImageIO.read(new File("silverlight.png"));
            internetImage2 = ImageIO.read(new File("internet2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void guiSystem(Graphics2D g2d) {
        //////
        ///////
        g2d.drawImage(tank, 0, 0, this);

        //وقتی روی استارت کلیک می کنید
        if (loneWolfClick) {
            try {
                g2d.drawImage(tank33, 0, 0, this);
                g2d.drawImage(starDark, 10, 10, this);
                g2d.drawImage(internetDark, 160, 10, this);
                g2d.drawImage(serverNetworkDark, 330, 55, this);
                g2d.drawImage(outBreak, 1100, 380, this);
                g2d.drawImage(silver, 825, 380, this);
                g2d.drawImage(copper, 550, 365, this);
                if (eseay) {
                    g2d.drawImage(copperLight, 550, 365, this);
                } else if (meduim) {
                    g2d.drawImage(silverLight, 825, 380, this);

                } else if (hard) {
                    g2d.drawImage(outBreakDark, 1100, 380, this);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //وقتی روی سرور کلیک میکنید
        if (serverClick) {
            try {
                g2d.drawImage(tank33, 0, 0, this);
                g2d.drawImage(starDark, 10, 10, this);
                g2d.drawImage(internetDark, 160, 10, this);
                g2d.drawImage(serverNetworkDark, 330, 55, this);
                num++;
                String address = "wait animation\\";
                address = address + (num % 8 + 1) + ".png";
                Thread.sleep(70);
//                System.out.println(address);
                g2d.drawImage(ImageIO.read(new File(address)), 650, 55, this);
                //یافتن ip
                String ip = "";
                try (final DatagramSocket socket = new DatagramSocket()) {
                    try {
                        socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ip = socket.getLocalAddress().getHostAddress();
//                    System.out.println(ip);
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                //نوشته ی توی صفحه
                String str = "Looking for teamate _ your IP is " + ip;
                g2d.setColor(Color.yellow);
                g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
                int strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2 + 50);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if (start) {
                g2d.drawImage(startImage2, 10, 10, this);
//                System.out.println("2");

            } else {
                g2d.drawImage(startImage, 10, 10, this);

            }
            if (join) {
                try {
                    g2d.drawImage(internetImage2, 160, 10, this);
//                System.out.println("2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                g2d.drawImage(internetImage, 160, 10, this);

            }
            if (server) {
                try {
                    g2d.drawImage(ImageIO.read(new File("server_network2.png")), 330, 55, this);
//                System.out.println("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    g2d.drawImage(ImageIO.read(new File("server_network.png")), 330, 55, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*		g2d.drawImage(image,state.locX,state.locY,null);*/
        }
    }

/////////////////////////////////////////////////////////////////////////
}
