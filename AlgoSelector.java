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



class AlgoSelector extends JPanel {

    static Dimension size;
    JLabel algoLogo;
    JComboBox<String> algoSelect;

    AlgoSelector() {
        // Initialization
        size = new Dimension(230, 50);

        // logo
        ImageIcon icon = new ImageIcon("Assets/algo.png");
        algoLogo = new JLabel(Node.resizeIcon(icon, 40, 40));
        algoLogo.setBounds(10, 0, 50, 50);

        // algo selector
        String[] algos = new String[] { "Dijkstra", "Breadth-First Search", "Depth-First Search" };
        algoSelect = new JComboBox<String>(algos);
        algoSelect.setBounds(60, 15, size.width - 60, 30);


        // addListener
        algoSelect.addItemListener( new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                // inform player 
                Player.setAlgo((String)algoSelect.getSelectedItem());
            }
        });

        // configuration
        setSize(size);
        setLayout(null);
        setOpaque(false);
        // setBorder( BorderFactory.createLineBorder(Color.BLACK));

        // add component
        add(algoLogo);
        add(algoSelect);

        // visible
        setVisible(true);
    }
}