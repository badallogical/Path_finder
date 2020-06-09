package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;

import source.Dijkstra;

import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.lang.model.util.ElementScanner6;

import java.io.File;
import java.io.IOException;
import java.util.*;

class Player extends JPanel {
    static JButton play;
    static boolean playState = false;
    static Dimension size;
    static String activeAlgo;

    // play button
    static ImageIcon playIcon = new ImageIcon("Assets/play.png");
    static ImageIcon pauseIcon = new ImageIcon("Assets/pause.png");

    float speed;
    static ActionListener playerAction;

    Player() {
        // Initialize
        playerAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // reset all algorithm status
                reset();

                if (playState) {
                    // paused
                    playState = false;
                    JButton b = (JButton) e.getSource();
                    b.setIcon(Node.resizeIcon(playIcon, 40, 40));

                } else {

                    // allow only if either ticked or creator is off
                    if (TickButton.tickState || !Creator.selected) {

                        // verify to check the configuration for the active algo
                        if (verify()) {
                            // playing
                            playState = true;
                            JButton b = (JButton) e.getSource();
                            b.setIcon(Node.resizeIcon(pauseIcon, 40, 40));
                            b.updateUI();

                        }

                        // inform Road Map to start
                        RoadMap.currentAlgo = activeAlgo;
                        Path_finder.map.repaint();
                    } else {
                        // mesage to tick done
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                JOptionPane.showMessageDialog(Path_finder.map, "Please tick the done icon first ", "",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
                    }

                }
            }
        };

        size = new Dimension(100, (int) NavBar.size.getHeight());
        activeAlgo = "Dijkstra"; // default

        play = new JButton(Node.resizeIcon(playIcon, 40, 40));
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        play.setBounds(10, 3, 50, 50);
        play.setMargin(new Insets(0, 0, 0, 10));

        // add components
        add(play);

        // add listners
        play.addActionListener(playerAction);

        // configuration
        setSize((int) size.getWidth(), (int) size.getHeight());
        // setBorder( BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(false);
        setLayout(null);
        setVisible(true);
    }

    static void setAlgo(String s) {
        activeAlgo = s;
        System.out.println("activeALgo " + s);
    }

    private boolean verify() {
        switch (activeAlgo) {
            case "Dijkstra":
                if (Dijkstra.src == -1 && Dijkstra.dest == -1) {
                    Dijkstra.inform();
                    return false;
                } else
                    break;

            case "Breadth-First Search":
                if (BFS.dest_node == -1 && BFS.root == -1) {
                    BFS.inform();
                    return false;
                } else {
                    break;
                }

            case "Depth-First Search":
                if (DFS.dest_node == -1 && BFS.root == -1) {
                    DFS.inform();
                    return false;
                } else {
                    break;
                }

        }
        return true;
    }

    static public void reset() {
        switch (activeAlgo) {
            case "Dijkstra":
                Dijkstra.reset();
                break;

            case "Breadth-First Search":
                BFS.reset();
                break;

            case "Depth-First Search":
                DFS.reset();
                break;
        }



        // TODO : need to find the safer way to destroy a thread ( refer to article )
        if( RoadMap.runningAlgo != null ){
            RoadMap.runningAlgo = null;
        }
    }

    static public void pause() {
        playState = false;
        play.setIcon(Node.resizeIcon(playIcon, 40, 40));
    }
}