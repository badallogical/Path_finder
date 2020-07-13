package source;

import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;

abstract public class BFS {
    static int dest_node = -1;
    static Vector<Vector<Path>> graph;
    static int root = -1;

    static boolean running = false;
    static boolean found = false;
    static int node = -1;

    // algorithm
    static void run() {
        running = true;

        // get the graph and inputs
        graph = Path_finder.map.graph;

        // input the root and dest Node as mentioned by the popup

        // traverse
        Vector<Integer> queue = new Vector<Integer>();

        // insert the root in the queue
        queue.add(root);

        node = -1;
        while (!queue.isEmpty()) {

            node = queue.remove(0);

            // highlist that node
            System.out.println("->" + node);
            Path_finder.map.repaint();
           

            // delay
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= Speedometer.speed)
                ;

            // if found
            if (node == dest_node) {
                found = true;
                finish();
                break;
            }

            // add the adjencencies of the node
            Iterator it = graph.get(node).iterator();
            while (it.hasNext()) {
                // push to queue
                queue.add(((Path) it.next()).link_to);
            }
        }

        if( found == false){
            JOptionPane.showMessageDialog( Path_finder.map, "Sorry sir, It's not possible");
        }

        // auto reset
        resetStatus();

    }

    static void inform() {
        JOptionPane.showMessageDialog(Path_finder.map, "Select the root and destination node", "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    static void finish(){
        JOptionPane.showMessageDialog(Path_finder.map, "Congratulation Sir :) ", " ", JOptionPane.INFORMATION_MESSAGE);
    }

    static void resetStatus() {
        root = -1;
        dest_node = -1;
        running = false;

        // auto reset player
        Player.pause();

    }

    static void reset(){
        root = -1;
        dest_node = -1;
        running = false;

        Vector<Node> nodes = Path_finder.map.getNodes();
        for( int i = 0; i < Node.node_count; i++){
            nodes.get(i).setIcon( new ImageIcon( NodeAssets.icon1));
        }

        // auto reset player
        Player.pause();

        Path_finder.map.repaint();
    }
}

