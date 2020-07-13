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


class Pallete extends JPanel implements ActionListener {

    static Dimension size;
    JButton colorPalleteButton;
    boolean colorPalletedSeleted = false;
    JButton[] colorButtons;
    int colors = 6;

    // control variable
    Color selected_color;

    Pallete() {
        // Initialization
        size = new Dimension((int) ControlPallette.size.getWidth() - 20, 230);
        selected_color = new Color(0,0,0);

        // colors
        int colorButtonSize = 20;
        colorButtons = new JButton[colors];
        colorButtons[0] = new JButton();
        colorButtons[0].setActionCommand("RED");
        colorButtons[0].setBackground(Color.RED);
        colorButtons[0].addActionListener(this);
        colorButtons[0].setBounds(30, 10 + (int) size.getWidth() - 10, colorButtonSize, colorButtonSize);

        colorButtons[1] = new JButton();
        colorButtons[1].setBackground(Color.GREEN);
        colorButtons[1].setActionCommand("GREEN");
        colorButtons[1].addActionListener(this);
        colorButtons[1].setBounds(30, 10 + (int) size.getWidth() - 10 + colorButtonSize, colorButtonSize,
                colorButtonSize);

        colorButtons[2] = new JButton();
        colorButtons[2].setBackground(Color.BLUE);
        colorButtons[2].setActionCommand("BLUE");
        colorButtons[2].addActionListener(this);
        colorButtons[2].setBounds(30, 10 + (int) size.getWidth() - 10 + 2 * colorButtonSize, colorButtonSize,
                colorButtonSize);

        colorButtons[3] = new JButton();
        colorButtons[3].setBackground(Color.YELLOW);
        colorButtons[3].setActionCommand("YELLOW");
        colorButtons[3].addActionListener(this);
        colorButtons[3].setBounds(30, 10 + (int) size.getWidth() - 10 + 3 * colorButtonSize, colorButtonSize,
                colorButtonSize);

        colorButtons[4] = new JButton();
        colorButtons[4].setBackground(Color.ORANGE);
        colorButtons[4].setActionCommand("ORANGE");
        colorButtons[4].addActionListener(this);
        colorButtons[4].setBounds(30, 10 + (int) size.getWidth() - 10 + 4 * colorButtonSize, colorButtonSize,
                colorButtonSize);

        colorButtons[5] = new JButton();
        colorButtons[5].setBackground(Color.PINK);
        colorButtons[5].setActionCommand("PINK");
        colorButtons[5].addActionListener(this);
        colorButtons[5].setBounds(30, 10 + (int) size.getWidth() - 10 + 5 * colorButtonSize, colorButtonSize,
                colorButtonSize);

        // colorPallete button
        ImageIcon icon1 = new ImageIcon("Assets/color-palette.png");
        ImageIcon icon2 = new ImageIcon("Assets/color-circle.png");
        colorPalleteButton = new JButton(Node.resizeIcon(icon1, 50, 50));
        colorPalleteButton.setBounds(5, 10, (int) size.getWidth() - 10, (int) size.getWidth() - 10);
        colorPalleteButton.setOpaque(false);
        colorPalleteButton.setFocusPainted(false);
        colorPalleteButton.setBorderPainted(false);
        colorPalleteButton.setContentAreaFilled(false);
        colorPalleteButton.setVisible(true);
        colorPalleteButton.setMargin(new Insets(10, 10, 10, 10));

        // mouseListeners
        colorPalleteButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                colorPalleteButton.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorPalleteButton.setBorderPainted(false);
            }
        });

        // actionListener
        colorPalleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (colorPalletedSeleted) {
                    // reset
                    colorPalletedSeleted = false;
                    colorPalleteButton.setIcon(Node.resizeIcon(icon1, 50, 50));
                    hideColor();
                } else {
                    // selected
                    colorPalletedSeleted = true;
                    colorPalleteButton.setIcon(Node.resizeIcon(icon2, 50, 50));
                    showColor();
                }
            }
        });

        // add componenets
        add(colorPalleteButton);
        for (int i = 0; i < colors; i++) {
            add(colorButtons[i]);
        }

        // configuration
        hideColor();
        // setBorder( BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(false);
        setLayout(null);
        setSize((int) size.getWidth(), (int) size.getHeight());
        setVisible(true);
    }

    // action listener of colors
    public void actionPerformed(ActionEvent e) {
        switch( e.getActionCommand() ){
            case "RED":
                selected_color = Color.RED;
                break;
            case "GREEN":
                selected_color = Color.GREEN;
                break;
            case "BLUE":
                selected_color = Color.BLUE;
                break;
            case "YELLOW":
                selected_color = Color.YELLOW;
                break;
            case "ORANGE":
                selected_color = Color.ORANGE;
                break;
            case "PINK":
                selected_color = Color.PINK;
                break;
            default:
                System.out.println("error in colors");
        }

        // inform RoadMap
        RoadMap.current_path_color = selected_color;
    }

    void hideColor() {
        for (int i = 0; i < colors; i++) {
            colorButtons[i].setVisible(false);
        }
    }

    void showColor() {
        for (int i = 0; i < colors; i++) {
            colorButtons[i].setVisible(true);
        }
    }
}