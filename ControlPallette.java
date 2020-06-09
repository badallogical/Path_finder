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



// Representation of control pallete
class ControlPallette extends JPanel {
    int box_offset;
    Color shadow_color;
    Color box_color;
    int shadow_gap;

    // components
    Creator mapCreator;
    Pallete colorPallete;
    Cleaner mapCleaner;

    int x = (int) Path_finder.size.getWidth() - 100;
    int y = 100;
    static Dimension size;

    public ControlPallette() {
        // Initialize
        size = new Dimension(100, 400);
        box_offset = 10;
        shadow_color = new Color(50, 50, 50, 150);
        box_color = new Color(208, 225, 47);
        shadow_gap = 7;

        // map creator
        mapCreator = new Creator();
        mapCreator.setLocation(20, 30);

        // map cleaner
        mapCleaner = new Cleaner();
        mapCleaner.setLocation(20,100);

        // color pallete
        colorPallete = new Pallete();
        colorPallete.setLocation(10, 160);

        // add components
        add(mapCreator);
        add(mapCleaner);
        add(colorPallete);

        // configuration
        setBounds(x, y, (int) size.getWidth(), (int) size.getHeight());
        // setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);
        setVisible(true);
    }

    // panel design
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // high quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // design
        g2d.setColor(shadow_color);
        g2d.fillRoundRect(box_offset + shadow_gap, box_offset + shadow_gap, (int) size.getWidth() - 2 * box_offset,
                (int) size.getHeight() - 2 * box_offset, 20, 20);

        g2d.setColor(box_color);
        g2d.fillRoundRect(box_offset, box_offset, (int) size.getWidth() - 2 * box_offset,
                (int) size.getHeight() - 2 * box_offset, 20, 20);

        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(box_offset, box_offset, (int) size.getWidth() - 2 * box_offset,
                (int) size.getHeight() - 2 * box_offset, 20, 20);
    }

}