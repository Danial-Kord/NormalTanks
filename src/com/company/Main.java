/*** In The Name of Allah ***/
package com.companys;

import com.company.GameFrame;
import com.company.GameLoop;
import com.company.ThreadPool;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {

    public static void main(String[] args) {
        // Initialize the global thread-pool
        ThreadPool.init();
        // Show the game menu ...
        // After the player clicks 'PLAY' ...
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BufferedImage cursorImg = new BufferedImage(15,15,BufferedImage.TYPE_INT_ARGB);
                Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg,new Point(0,0),"blank cursor");


                GameFrame frame = new GameFrame("Game Title");
                frame.getContentPane().setCursor(blankCursor);
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                game.init();
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });
    }
}
