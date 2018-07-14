/*** In The Name of Allah ***/
package com.company;

import java.io.File;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 *
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameLoop implements Runnable {

    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 120;


    private static GameFrame canvas;
    private static GameState state;

    public GameLoop(GameFrame frame) {
        canvas = frame;
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init() {
        state = new GameState();
        // Perform all initializations ...
        if(GameFrame.inMenu){
            canvas.addMouseListener(state.getMouseListener());
            canvas.addKeyListener(state.getKeyListener());
        }
        else {
            canvas.addKeyListener(state.getTank().getKeyListener());
            canvas.addMouseListener(state.getTank().getMouseListener());
            canvas.addMouseMotionListener(state.getTank().getMouseMotionListener());
//		canvas.addMouseListener(state.getTank().getMouseListener());
        }
        }

    public static void setState(GameState state) {
        GameLoop.state = state;
    }

    @Override
    public void run() {
        while (GameFrame.inMenu){
            try {
                long start = System.currentTimeMillis();
                //
                state.update();
                canvas.render(state);
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            }
        }
        SoundsHandeler.backGroundSoundStop();
        SoundsHandeler.playSoundBackGround(new File("music\\18.-Manhattan-Assault.wav"));

//        System.out.println("این جا مونده");
        canvas.addKeyListener(state.getTank().getKeyListener());
        canvas.addMouseListener(state.getTank().getMouseListener());
        canvas.addMouseMotionListener(state.getTank().getMouseMotionListener());
        boolean gameOver = false;
        while (!gameOver) {
            try {
                long start = System.currentTimeMillis();
                //
                state.update();
                canvas.render(state);
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            }
        }
    }
}
