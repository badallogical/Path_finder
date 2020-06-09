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



public class TickButton extends JButton {

    static Dimension size;
    static boolean tickState = false;

    static ActionListener tickAction;

            // tick logo
            ImageIcon tickicon = Node.resizeIcon(new ImageIcon("Assets/tick.png"), 40, 40);
            ImageIcon doneicon = Node.resizeIcon(new ImageIcon("Assets/done.png"), 40, 40);
            

    TickButton() {
        // Initialize
        size = new Dimension(50, 40);
        tickAction = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (TickButton.tickState) {
                    TickButton.tickState = false;
                    setIcon(doneicon);

                    // for explicit action event
                    updateUI();
                } else {
                    TickButton.tickState = true;
                    setIcon(tickicon);

                    // infrom pallete creator option and RoadMap
                    if( Creator.selected == true)
                        Creator.creatorAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Action_performed" ));
                }
            
               
            }
        };



        // add actionListner
        addActionListener(tickAction);
            
        

        // configuration
        setSize((int) size.getWidth(), (int) size.getWidth());
        setContentAreaFilled(false);
        setIcon(Node.resizeIcon(doneicon, 40, 40));
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setVisible(true);

    }

    boolean getStatus() {
        return TickButton.tickState;
    }
}