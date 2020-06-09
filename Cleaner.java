package source;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cleaner extends JButton {

    // control variable
    boolean selected = false;
    Color selectionColor;
    Dimension size;
    int icon_size = 50;

    Cleaner temp;

    Cleaner() {
        // Initialization
        temp = this;
        size = new Dimension(Creator.size.width, Creator.size.height);
        selectionColor = new Color(103, 139, 216, 150);

        // Icon
        ImageIcon icon = new ImageIcon("Assets/brush.png");
        setIcon(Node.resizeIcon(icon, icon_size, icon_size));

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    selected = true;

                    clearn();

                    setOpaque(false);
                    
                    selected = false;
                }

        });

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                temp.setBorderPainted(true);
                temp.setOpaque(true);
        
             
            }

            public void mouseExited(MouseEvent e) {
                temp.setBorderPainted(false);
                temp.setOpaque(false);
               
            }
        });

        // configuration
        setSize(size.width, size.height);
        setBackground(selectionColor.brighter());
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        setOpaque(false);
        setVisible(true);
    }

    public void clearn(){
        Path_finder.map.reset();
    }
}