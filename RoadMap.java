package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;

import source.Dijkstra;

import javax.swing.Timer;

import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

// Representation of Map
public class RoadMap extends JPanel implements Serializable {

    static Dimension size;

    Vector<Node> nodes;
    Vector<Vector<Path>> graph;

    transient int selected_node = -1;
    transient int highlight_node = -1;

    static Color current_path_color;
    static String currentAlgo = null;

    // control variable
    public static boolean active = false;

    // running algo thread
    static Thread runningAlgo;

    RoadMap() {
        // Initialization
        size = new Dimension(700, 482);
        nodes = new Vector<Node>();
        graph = new Vector<Vector<Path>>();
        current_path_color = new Color(50, 50, 50, 150);

        RoadMap ref = this;

        // add listeners
        addMouseListener(new MouseAdapter() {
            // click event
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouse clicked");
                if (active) {
                    for (int i = 0; i < Node.node_count; i++) {
                        if (e.getX() >= nodes.get(i).getX() && e.getX() <= nodes.get(i).getX() + Node.icon_size
                                && e.getY() >= nodes.get(i).getY()
                                && e.getY() <= nodes.get(i).getY() + Node.icon_size) {
                            if (selected_node == -1) {
                                selected_node = i;

                                // draw selected node
                                repaint();
                            } else {
                                // draw Link
                                if (i != selected_node) {
                                    int distance = find_distance(nodes.get(i), nodes.get(selected_node));
                                    graph.get(selected_node).add(new Path(i, current_path_color, distance));
                                    System.out.println("distance ; " + distance);
                                    repaint();

                                }

                                // reset / deselection
                                nodes.get(selected_node).setIcon(new ImageIcon(NodeAssets.icon1));
                                selected_node = -1;

                            }
                            return;
                        }
                    }

                    // add new node
                    // nodes.add(new Node(e.getX(), e.getY()));
                    // nodes.lastElement().setLocation(e.getX() - NodeAssets.icon_size/2 , e.getY()
                    // - NodeAssets.icon_size);
                    // graph.add(new Vector<Path>());
                    // System.out.println("nodes:" + Node.node_count);

                    nodes.add(new Node(e.getX(), e.getY()));
                    ref.add(nodes.lastElement());
                    nodes.lastElement().updateUI();
                    graph.add(new Vector<Path>());

                    repaint(nodes.get(Node.node_count - 1).getX(), nodes.get(Node.node_count - 1).getY(),
                            Node.icon_size, Node.icon_size);
                } else {
                    if (currentAlgo != null) {
                        if (currentAlgo == "Dijkstra") {
                            if (Dijkstra.src == -1 || Dijkstra.dest == -1) {
                                // get the sources node or destination node
                                for (int i = 0; i < Node.node_count; i++) {
                                    if (e.getX() >= nodes.get(i).getX()
                                            && e.getX() <= nodes.get(i).getX() + Node.icon_size
                                            && e.getY() >= nodes.get(i).getY()
                                            && e.getY() <= nodes.get(i).getY() + Node.icon_size) {

                                        if (Dijkstra.src == -1) {
                                            Dijkstra.src = i;
                                            nodes.get(Dijkstra.src).setIcon(new ImageIcon(NodeAssets.source_icon));
                                            System.out.println("src:" + Dijkstra.src);
                                        } else if (Dijkstra.dest == -1) {
                                            Dijkstra.dest = i;
                                            nodes.get(Dijkstra.dest).setIcon(new ImageIcon(NodeAssets.end_icon));
                                            System.out.println("dest:" + Dijkstra.dest);
                                        }

                                        repaint(nodes.get(i).getX(), nodes.get(i).getY(), nodes.get(i).icon_size,
                                                nodes.get(i).icon_size);

                                    }
                                }
                            }
                        } else if (currentAlgo == "Breadth-First Search") {
                            if (BFS.root == -1 || BFS.dest_node == -1) {
                                for (int i = 0; i < Node.node_count; i++) {
                                    if (e.getX() >= nodes.get(i).getX()
                                            && e.getX() <= nodes.get(i).getX() + Node.icon_size
                                            && e.getY() >= nodes.get(i).getY()
                                            && e.getY() <= nodes.get(i).getY() + Node.icon_size) {

                                        if (BFS.root == -1) {
                                            BFS.root = i;
                                            nodes.get(BFS.root).setIcon(new ImageIcon(NodeAssets.root_icon));
                                            System.out.println("root:" + i);
                                        } else if (BFS.dest_node == -1) {
                                            BFS.dest_node = i;
                                            nodes.get(BFS.dest_node).setIcon(new ImageIcon(NodeAssets.dest_icon));
                                            System.out.println("dest:" + i);
                                        }

                                        repaint(nodes.get(i).getX(), nodes.get(i).getY(), nodes.get(i).icon_size,
                                                nodes.get(i).icon_size);

                                    }
                                }
                            }
                        } else if (currentAlgo == "Depth-First Search") {
                            if (DFS.root == -1 || DFS.dest_node == -1) {
                                // check for root or dest_node
                                for (int i = 0; i < Node.node_count; i++) {
                                    if (e.getX() >= nodes.get(i).getX()
                                            && e.getX() <= nodes.get(i).getX() + Node.icon_size
                                            && e.getY() >= nodes.get(i).getY()
                                            && e.getY() <= nodes.get(i).getY() + Node.icon_size) {

                                        if (DFS.root == -1) {
                                            DFS.root = i;
                                            nodes.get(DFS.root).setIcon(new ImageIcon(NodeAssets.root_icon));
                                            System.out.println("root:" + i);
                                        } else if (DFS.dest_node == -1) {
                                            DFS.dest_node = i;
                                            nodes.get(DFS.dest_node).setIcon(new ImageIcon(NodeAssets.dest_icon));
                                            System.out.println("dest:" + i);
                                        }

                                        repaint(nodes.get(i).getX(), nodes.get(i).getY(), nodes.get(i).icon_size,
                                                nodes.get(i).icon_size);

                                    }
                                }
                            }
                        }

                    }

                }
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            // hover
            public void mouseMoved(MouseEvent e) {

                if (active) {
                    // identify the nodes
                    boolean mark = false;
                    for (int i = 0; i < Node.node_count; i++) {

                        if (e.getX() >= nodes.get(i).getX() && e.getX() <= nodes.get(i).getX() + Node.icon_size
                                && e.getY() >= nodes.get(i).getY()
                                && e.getY() <= nodes.get(i).getY() + Node.icon_size) {

                            // pop that node
                            highlight_node = i;
                            mark = true;
                            repaint(nodes.get(i).getX() - 5, nodes.get(i).getY() - 10, 50, 50);
                        }
                    }

                    // if no one is hovered
                    if (mark == false) {
                        // unhighlight the previous first
                        if (highlight_node != -1) {
                            Node node = nodes.get(highlight_node);
                            node.setIcon(new ImageIcon(NodeAssets.icon1));

                        }

                        highlight_node = -1;
                        repaint();
                    }

                }
            }

            public void mouseDragged(MouseEvent e) {

            }

        });

        // configuration
        setSize((int) size.getWidth(), (int) size.getHeight());
        setBackground(Color.WHITE);
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder("Map"));
        // setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);
    }


    public void reload(){

        // refresh nodes
        Node.node_count = 0;
        for( Node node : nodes ){
            this.add( node );
            node.setIcon( new ImageIcon(NodeAssets.icon1));
            node.setBounds( node.getX(), node.getY(), node.getWidth(), node.getHeight());
            node.setVisible(true);
            node.updateUI();
            Node.node_count++;
        }

        // refresh graph
        repaint();
    }

    // find the distance of two nodes
    private int find_distance(Node i, Node t) {
        return (int) Math.sqrt(Math.pow(Math.abs(t.getX() - i.getX()), 2) + Math.pow(Math.abs(t.getY() - i.getY()), 2));
    }

    public void paintComponent(Graphics g) {
        System.out.println("active : " + active);
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final float[] dashed = new float[] { 10.0f, 25.0f };

        // // // refresh nodes

        // g.drawImage(Node.icon1, nodes.get(i).getX(), nodes.get(i).getY(), null);

        // // refresh paths
        if (graph != null) {
            for (int i = 0; i < nodes.size(); i++) {
                Iterator it = graph.get(i).iterator();
                while (it.hasNext()) {
                    Path terminal_node = (Path) it.next();
                    ((Graphics2D) g).setColor(terminal_node.path_color);
                    
                    
                    if( Path.path_style == Path.dashed_style){
                        ((Graphics2D) g).setStroke(
                            new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0.0f, dashed, 0.0f));
                    }
                    else{
                        ((Graphics2D) g).setStroke(
                            new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    }

                    ((Graphics2D) g).drawLine(nodes.get(i).getX() + Node.icon_size / 2,
                    nodes.get(i).getY() + Node.icon_size,
                    nodes.get(terminal_node.link_to).getX() + Node.icon_size / 2,
                    nodes.get(terminal_node.link_to).getY() + Node.icon_size);

                }
            }
        }

        highlight_node();

        selected_node();

        // if playing
        if (currentAlgo != null) {
            if (currentAlgo == "Dijkstra") {
                System.out.println("algo is road map : " + currentAlgo);
                System.out.println("src : " + Dijkstra.src);

                if (Dijkstra.src != -1 && Dijkstra.dest != -1) {
                    // auto play the player
                    Player.playState = true;
                    Player.play.setIcon(Node.resizeIcon(Player.pauseIcon, 40, 40));

                    // run the algo
                    if (Player.playState && !Dijkstra.running) {
                        runningAlgo = new Thread(new Runnable() {
                            public void run() {
                                Dijkstra.run(g);
                            }
                        });
                        runningAlgo.start();

                    }
                }

                // if already running
                if (Dijkstra.closest_node != -1) {
                    if (Dijkstra.found == false) {
                        System.out.println("called " + Dijkstra.closest_node);
                        g.setColor(new Color(200, 0, 0, 150));
                        // g.fillOval(
                        // Path_finder.map.nodes.get(Dijkstra.closest_node).getX() + Node.icon_size / 2
                        // - Dijkstra.spot_size / 2,
                        // Path_finder.map.nodes.get(Dijkstra.closest_node).getY() + Node.icon_size
                        // - Dijkstra.spot_size / 2,
                        // Dijkstra.spot_size, Dijkstra.spot_size);

                        ImageIcon img = new ImageIcon("Assets/spot.png");
                        g.drawImage(Node.resizeIcon(img, Dijkstra.spot_size, Dijkstra.spot_size).getImage(),
                                Path_finder.map.nodes.get(Dijkstra.closest_node).getX() + Node.icon_size / 2
                                        - Dijkstra.spot_size / 2,
                                Path_finder.map.nodes.get(Dijkstra.closest_node).getY() + Node.icon_size
                                        - Dijkstra.spot_size / 2,
                                null);
                    } else if (Dijkstra.found == true) {
                        // show the path
                        ((Graphics2D) g)
                                .setStroke(new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                        for (int i = 1; i <= Dijkstra.n; i++) {
                            g.setColor(new Color(10, 200, 20, 150));
                            ((Graphics2D) g).drawLine(nodes.get(Dijkstra.stack.get(i - 1)).getX() + Node.icon_size / 2,
                                    nodes.get(Dijkstra.stack.get(i - 1)).getY() + Node.icon_size,
                                    nodes.get(Dijkstra.stack.get(i)).getX() + Node.icon_size / 2,
                                    nodes.get(Dijkstra.stack.get(i)).getY() + Node.icon_size);
                        }
                    }
                }

            } else if (currentAlgo == "Breadth-First Search") {

                // run the BFS
                if (BFS.root != -1 && BFS.dest_node != -1) {

                    // auto play the player
                    Player.playState = true;
                    Player.play.setIcon(Node.resizeIcon(Player.pauseIcon, 40, 40));

                    if (Player.playState && !BFS.running) {

                        runningAlgo = new Thread(new Runnable() {
                            public void run() {
                                BFS.run();
                            }
                        });
                        runningAlgo.start();
                    }

                    // if already running mantain
                    if (BFS.node != -1) {
                        System.out.println(BFS.node);
                        nodes.get(BFS.node).setIcon(new ImageIcon(NodeAssets.visited));
                    }
                }
            } else if (currentAlgo == "Depth-First Search") {

                // run the DFS
                if (DFS.root != -1 && DFS.dest_node != -1) {

                    // auto play the player
                    Player.playState = true;
                    Player.play.setIcon(Node.resizeIcon(Player.pauseIcon, 40, 40));

                    if (Player.playState && !DFS.running) {
                        runningAlgo = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                DFS.run();
                            }
                        });

                        runningAlgo.start();
                    }

                    // if already running maintain
                    if (DFS.node != -1) {
                        nodes.get(DFS.node).setIcon(new ImageIcon(NodeAssets.visited));
                    }
                }
            }
        }

    }

    public void highlight_node() {

        // highlight
        if (highlight_node >= 0) {
            System.out.println("Highlight called");
            Node node = nodes.get(highlight_node);
            node.setIcon(new ImageIcon(NodeAssets.icon2));
        }
    }

    public void selected_node() {
        // selection
        if (selected_node >= 0) {
            nodes.get(selected_node).setIcon(new ImageIcon(NodeAssets.icon3));
        }
    }

    public Vector<Node> getNodes() {
        return nodes;
    }

    public Vector<Vector<Path>> getGraph(){
        return graph;
    }

    public void showGraph() {
        for (int i = 0; i < Node.node_count; i++) {
            System.out.println("Node " + i + ":->");
            Iterator it = graph.get(i).iterator();
            while (it.hasNext()) {
                System.out.println(" ->" + ((Path) it.next()).link_to);
            }
        }
    }

    public void reset() {
       
        current_path_color = new Color(50, 50, 50, 150);
        graph = new Vector<Vector<Path>>();
        runningAlgo = null;
        selected_node = -1;
        highlight_node = -1;
        currentAlgo = null;

        // reset algos
        Player.reset();

        // reset map nodes
        Node.reset();
        nodes = new Vector<Node>();

        repaint();
    }

    public void setGraph(Vector<Vector<Path>> graph ){
        this.graph = graph;
    }

    public void setNodes( Vector<Node> nodes ){
        this.nodes = nodes;
    }

    // TODO : reseting map
}