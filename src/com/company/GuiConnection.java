package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * this jfram get information for connectin to ip address
 */
public class GuiConnection {
    JFrame jFrame = new JFrame();
    JTextField ipAddress;
    GameState state;
    public GuiConnection(GameState state) {
        this.state = state;
        jFrame.setLocation(800, 200);
        ipAddress = new JTextField();
        ImageIcon water = new ImageIcon("images.png");
        JButton jButton = new JButton(water);
        ipAddress.setPreferredSize(new Dimension(300, 60));
        jButton.addMouseListener(new Connect());
        jFrame.setPreferredSize(new Dimension(300, 300));
        jFrame.setSize(300, 400);
        JPanel surface = new JPanel();
        surface.setBorder(new EmptyBorder(5, 5, 5, 5));
        jFrame.setContentPane(surface);
        surface.setLayout(new BorderLayout(5, 5));
        surface.add(ipAddress, BorderLayout.NORTH);
        surface.add(jButton, BorderLayout.CENTER);
        jFrame.setVisible(true);
    }

    private class Connect implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            Client client = new Client(ipAddress.getText(),state);
            ThreadPool.execute(client);
            jFrame.setVisible(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
