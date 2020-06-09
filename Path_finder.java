package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Path_finder extends JFrame {

    static Dimension size;
    static Path_finder ref;

    // components
    static RoadMap map;
    NavBar navigaionBar;
    MenuRibbon menuBar;
    ControlPallette controlPallete;

    public Path_finder(int width, int height) {

        // initialize
        size = new Dimension(width, height);
        map = new RoadMap();
        navigaionBar = new NavBar();
        menuBar = new MenuRibbon();
        controlPallete = new ControlPallette();
        ref = this;

        // add components
        setJMenuBar(menuBar);
        map.setLocation(0, (int) navigaionBar.size.getHeight());
        navigaionBar.setLocation(0, 0);
        add(map);
        add(navigaionBar);
        add(controlPallete);

        // configurations
        setResizable(false);
        setTitle("Path_Finder");
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Path_finder app = new Path_finder(800, 600);
                
            }
        });
    }

}