package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


class Speedometer extends JPanel {

    static Dimension size;
    JSlider speedLevel;
    JLabel speedLogo;
    
    static int max_speed = 5000;
    static int speed = max_speed / 2;

    Speedometer() {
        // Initialization
        size = new Dimension(200, (int) NavBar.size.getHeight());

        // speed logo
        ImageIcon icon = new ImageIcon("Assets/speed.png");
        speedLogo = new JLabel(Node.resizeIcon(icon, 40, 40));
        speedLogo.setBounds(10, 0, 50, 50);

        // configure speedLevel
        speedLevel = new JSlider(0, max_speed);
        speedLevel.setBounds(60, 0, 100, 50);
        speedLevel.setOpaque(false);
        speedLevel.setCursor(Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        speedLevel.addChangeListener( new ChangeListener(){
        
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                speed = max_speed - speedLevel.getValue() + 10;        // ??????????    
            }
        });

        

        // add components
        add(speedLogo);
        add(speedLevel);

        // Configuration
        setSize((int) size.getWidth(), (int) size.getHeight());
        // setBorder( BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(false);
        setLayout(null);
        setVisible(true);
    }
}