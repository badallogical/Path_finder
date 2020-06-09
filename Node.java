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
import java.util.Vector;



public class Node extends JLabel{
    static int node_count;
    static final int icon_size = 40;
    static final int icon_scale = 20;

    private int node_id;
    private int x, y;

    private Dimension size;

    Node(int x, int y) {
        
        // Initialization
        node_count++;
        node_id = node_count - 1;
        size = new Dimension(icon_size, icon_size);
        this.x = x - icon_size / 2;
        this.y = y - icon_size;
        
     

     
        // configuration
        setIcon( new ImageIcon(NodeAssets.icon1));
        setBounds(x,y, icon_size,icon_size);
        setVisible(true);
    }



 

    public static ImageIcon resizeIcon(ImageIcon img){
        return new ImageIcon(img.getImage().getScaledInstance( icon_size +  icon_scale, icon_size + icon_scale, Image.SCALE_SMOOTH));
    }

    public static ImageIcon resizeIcon(Image img){
        return new ImageIcon( img.getScaledInstance(icon_size + icon_scale , icon_size + icon_scale, Image.SCALE_SMOOTH));
    }

    public static ImageIcon resizeIcon(ImageIcon img, int widht, int height){
        return new ImageIcon( img.getImage().getScaledInstance(widht, height,Image.SCALE_SMOOTH));
    }


    public int getNodeId() {
        return node_id;
    }

   
    public Dimension getSize() {
        return size;
    }

    
    public int getX() {
        return x;
    }

 
    public int getY() {
        return y;
    }

    public static void reset(){
        
        Vector<Node> nodes = Path_finder.map.getNodes();
        for( int i = 0; i < Node.node_count; i++){
            nodes.get(i).setVisible(false);
        }
        node_count = 0;
    }

}