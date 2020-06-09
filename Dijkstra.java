package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;

import source.Path_finder;

import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.PathMatcher;
import java.util.*;

abstract public class Dijkstra {
    static int src = -1;
    static int dest = -1;
    static boolean running = false;

    static RoadMap map = Path_finder.map;

    static final int inf = 999999;
    static boolean[] sptNode;
    static int[] dist;
    static int[] parent;

    static int spot_size = 30;
    static int spot_x = -1, spot_y = -1;
    static int speed = 2000;

    static int visited = 0;
    static boolean found = false;

    static int min = inf;
    static int closest_node = -1;
    static int n = 0;
    static Vector<Integer> stack;

    static public void inform() {
        // message to select source and dest
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(Path_finder.map, "Select source node and destination node", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    static void run(Graphics g) {
        running = true;
        sptNode = new boolean[Node.node_count];
        dist = new int[Node.node_count];
        parent = new int[Node.node_count];

        Arrays.fill(dist, inf);
        Arrays.fill(sptNode, false);
        Arrays.fill(parent, -1);

        // mark source node to 0 distance
        dist[src] = 0;

        while (visited != Node.node_count) {

            // find the closest node
            min = inf;
            for (int i = 0; i < Node.node_count; i++) {
                if (sptNode[i] == false && dist[i] < min) {
                    closest_node = i;
                    min = dist[i];
                }
            }

            // add to sptNode
            sptNode[closest_node] = true;

            // show the visit
            Path_finder.map.repaint();
            visited++;

            // delay
            long start = System.currentTimeMillis();
            while( System.currentTimeMillis() - start <= Speedometer.speed );        


            // if found
            if( closest_node == dest){
                found = true;
                break;
            }


            // udpate the closest node adjecencies
            Iterator it = map.graph.get(closest_node).iterator();
            while (it.hasNext()) {
                Path p = (Path) it.next();

                if (sptNode[p.link_to] == false && dist[closest_node] + p.distance < dist[p.link_to]) {
                    // update
                    dist[p.link_to] = dist[closest_node] + p.distance;
                    parent[p.link_to] = closest_node;
                }
            }
            
        }

        // get the shortest path
        if( found ){
            int i = dest;
            stack = new Vector<Integer>();
            while( i != -1){
                stack.insertElementAt(i,0);
                i = parent[i];
            }

            System.out.println("");
            for( n  = 1; n < stack.size(); n++ ){
                //delay
                Path_finder.map.repaint();
                long start = System.currentTimeMillis();
                while( System.currentTimeMillis() - start <= Speedometer.speed );  
            }
        }
        else{
            System.out.println("not found");

            JOptionPane.showMessageDialog(Path_finder.map, "No way sir , Its not reachable", "", JOptionPane.WARNING_MESSAGE);
        }

        
        // auto reset
        resetStatus();

    }

    static public void resetStatus() {
        closest_node = -1;
        src = -1;
        dest = -1;
        found = false;
        visited = 0;
        running = false;
        n = 0;


        //auto reset player
        Player.pause();
    }

    static public void reset(){
        closest_node = -1;
        src = -1;
        dest = -1;
        found = false;
        visited = 0;
        running = false;
        n = 0;

        Vector<Node> nodes = Path_finder.map.getNodes();
        for( int i = 0; i < Node.node_count; i++){
            nodes.get(i).setIcon( new ImageIcon( NodeAssets.icon1));
        }

        //auto reset player
        Player.pause();

        Path_finder.map.repaint();
    }


}
