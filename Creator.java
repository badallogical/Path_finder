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



class Creator extends JButton {

    static Dimension size;
    static boolean selected = false;
    Color selectionColor;

    static ActionListener creatorAction;

    //temp
    Creator temp;

    Creator() {
        // Initialize
        size = new Dimension(60, 60);
        selectionColor = new Color(103, 139, 216, 150);
        temp = this;

        creatorAction = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (selected) {
                    selected = false;
                    setOpaque(false);
                    
                    // for explicit action event 
                    updateUI();
                 
                } else {
                    selected = true;
                    setOpaque(true);

                    // inform the done to reset if active
                    if( TickButton.tickState )
                    TickButton.tickAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "action_performed"));
                }

                // inform RoadMap
                RoadMap.active = selected;
            }
        };


        addMouseListener( new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e) {
               temp.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                temp.setBorderPainted(false);
            }

        });

        // creator logo
        ImageIcon icon = new ImageIcon("Assets/create.png");
        setIcon(Node.resizeIcon(icon, 50, 50));

        // add action listeners
        addActionListener(creatorAction);

        // configure
        setSize((int) size.getWidth(), (int) size.getHeight());
        setBackground(selectionColor.brighter());
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorderPainted(false);
        setVisible(true);
    }

    boolean getStatus() {
        return selected;
    }
}