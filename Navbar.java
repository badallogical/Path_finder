package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


// Representation of NavBar
class NavBar extends JPanel {

    // design properties
    static Dimension size;
    int shadow_gap = 10;
    Color shadow_color;
    final Color bg;   // abstract bg
    final Color bg_color = Color.white; // panel bg
    int box_offset = 10;

    // panel components
    AlgoSelector algoSelector;
    Player play;
    Speedometer speedLevel;
    TickButton done;


    public NavBar() {
        // Initialize the size
        size = new Dimension((int) Path_finder.size.getWidth(), 60);
        bg = new Color(255, 170, 170);
        shadow_color = new Color(50, 50, 50, 150);

        // Algo Icon
        algoSelector = new AlgoSelector();
        algoSelector.setLocation(30, 0);

        // Play Icon
        play = new Player();
        play.setLocation(300, 0);

        // speed icon
        speedLevel = new Speedometer();
        speedLevel.setLocation(400, 0);

        // TickButton
        done = new TickButton();
        done.setLocation(650, 2);

        // configuration
        setSize((int) size.getWidth(), (int) size.getHeight());
        setLayout(null);
        // setBorder( BorderFactory.createLineBorder(Color.BLACK,2,true));

        // add components
        add(algoSelector);
        add(play);
        add(speedLevel);
        add(done);

        // add action listeners
        setVisible(true);
    }

    // set design in Navbar
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // smoothing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background
        g2d.setColor(bg_color);
        g2d.fillRect(0,0,(int)getWidth(), (int)getHeight());

        // design panel
        g2d.setColor(shadow_color);
        g2d.fillRoundRect(box_offset + shadow_gap, 2 + shadow_gap, (int) size.getWidth() - 40,
                (int) size.getHeight() - 10, 20, 20);
        g2d.setColor(bg);
        g2d.fillRoundRect(box_offset, 2, (int) size.getWidth() - 40, (int) size.getHeight() - 10, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.drawRoundRect(box_offset, 2, (int) size.getWidth() - 40, (int) size.getHeight() - 10, 20, 20);
    }


    
}