package source;

import source.util.FileHandler;

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

// Represent the menu bar
public class MenuRibbon extends JMenuBar implements ActionListener {

    // File menu
     JMenu file;
     JMenuItem Open, Save, exit;

    // help menu
    JMenu help;
    JMenuItem about;
    
    JMenu setting;
    JMenu path_style;
    JMenuItem plain;
    JMenuItem dashed;

    public MenuRibbon() {
        // initialize
         file = new JMenu("File");
         Open = new JMenuItem("Open");
         Save = new JMenuItem("Save");
         exit = new JMenuItem("Exit");




        help = new JMenu("Help");
        about = new JMenuItem("About");
        
        setting = new JMenu("Setting");
        path_style = new JMenu("Path Style");
        plain = new JMenuItem("Plain");
        dashed = new JMenuItem("Dashed");

        // event listeners
        Open.addActionListener(this);
        Save.addActionListener(this);
        exit.addActionListener(this);

        // mode


        // about
        about.addActionListener(this);
        
        //line style
        plain.addActionListener(this);
        dashed.addActionListener(this);


        // configuration
        file.add(Open);
        file.add(Save);
        file.add(exit);

        setting.add(path_style);
        path_style.add(plain);
        path_style.add(dashed);

        help.add(setting);
        help.add(about);
        
        

        this.add(file);
        // this.add(edit);
        this.add(help);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
             case "Open":
                 System.out.println("Open");
                 FileHandler.openFile();
                 break;

             case "Save":
                 System.out.println("Save");
                 FileHandler.saveFile();
                 break;

             case "Exit":
                 System.out.println("Exit");

                // break;
            case "Plain":
                Path.path_style = Path.plain_style;
                break;

            case "Dashed":
                Path.path_style = Path.dashed_style;
                break;

            case "About":
                AboutAction();
                break;
            
        }
    }

    public static void AboutAction(){
        JDialog aboutSection = new JDialog(Path_finder.ref, "About");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        String h1 = "Path Finder";
        JLabel heading = new JLabel("Path Finder");
        heading.setFont( new Font( "Arial", Font.BOLD, 40));
        heading.setVisible(true);
        heading.setBounds(100,30,300,50);
       // heading.setBorder(BorderFactory.createLineBorder(Color.black));

        aboutSection.add(heading);
        
        String info = "It is a algorithmic application design to simulate the behaviour of algorithm of graphs. "+
        "It's main purpose is to make other understand easily about the working of algo easily and even revise quickly after a long time with fun.";

        
        JLabel infoLabel = new JLabel("<html>" 
                +"<h3> Version :  1.1v </h3>"
                +"<h3>  Published Data : 23 May 2020 </h3>"
                +"<h3> Description : </h3> " 
                +"<p>" + info + "</p>" 
                +"<br><h3> Developer : Badal (BCA 4th Semester) </h3>"
                +"</html>"    
        );

        infoLabel.setBounds(50,100,400,200);
     // infoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        aboutSection.add(infoLabel);
        aboutSection.setLayout(null);
        aboutSection.setLocation( screenSize.width/2 - 500/2, screenSize.height/2 - 400/2);
        aboutSection.setSize(500,400);
        aboutSection.setVisible(true);
    }

  

}