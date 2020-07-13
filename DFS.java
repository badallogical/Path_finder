package source;

import java.util.Vector;
import java.util.Iterator;
import javax.swing.*;

abstract public class DFS {
    static int dest_node = -1;
    static int root = -1;
    static Vector<Vector<Path>> graph;
    

    static boolean running = false;
    static boolean found = false;
    static int node = -1;

    static void run() {
        
        // running state
        running = true;

        // get the graph
        graph = Path_finder.map.graph;

        Vector<Integer> stack = new Vector<Integer>();

        stack.add(root);

        while (!stack.isEmpty()) {
            // pop from stack and visit
            node = stack.remove(stack.indexOf(stack.lastElement()));

            System.out.println(node);

            // visit that node
            System.out.println("-> " + node);
            Path_finder.map.repaint();
            

            // delay
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= Speedometer.speed);

            // if found
            if (node == dest_node) {
                found = true;
                finish();
                break;
            }

            // add the adjencencies to stack
            Iterator it = graph.get(node).iterator();
            while (it.hasNext()) {
                // push to stack
                stack.add(((Path) it.next()).link_to);
            }
        }

        if( found == false){
            JOptionPane.showMessageDialog( Path_finder.map, "Sorry sir , It's not possible ", " ", JOptionPane.INFORMATION_MESSAGE);
        }

        // auto reset
        resetStatus();
    }

    static void inform() {
        JOptionPane.showMessageDialog(Path_finder.map, "Select the root and destination node", "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    static void finish(){
        JOptionPane.showMessageDialog(Path_finder.map, "Congratulaion Sir :)","", JOptionPane.INFORMATION_MESSAGE);
    }

    static void resetStatus() {
        root = -1;
        dest_node = -1;
        node = -1;
        running = false;

        // auto reset player
        Player.pause();
    }

    static void reset(){
        root = -1;
        dest_node = -1;
        running = false;
        node = -1;

        Vector<Node> nodes = Path_finder.map.getNodes();
        for( int i = 0; i < Node.node_count; i++){
            nodes.get(i).setIcon( new ImageIcon( NodeAssets.icon1));
        }

        // auto reset player
        Player.pause();

        Path_finder.map.repaint();
    }
}