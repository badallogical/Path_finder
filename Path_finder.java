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
	public static Dimension size;
    public static Path_finder ref;

    // components
    public static RoadMap map;
    NavBar navigaionBar;
    MenuRibbon menuBar;
    ControlPallette controlPallete;

    //login dashboard
    Login login;

    public Path_finder(int width, int height) {

        // Initialize
        size = new Dimension(width, height);
        map = new RoadMap();
        navigaionBar = new NavBar();
        menuBar = new MenuRibbon();
        controlPallete = new ControlPallette();
        ref = this;
        login = new Login();
        

        // Configurations
        setResizable(false);
        setTitle("Path Finder");
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void login(){
        add( login );
        login.setLocation((int)(size.getWidth()/2 - login.size.getWidth()/2), (int)(size.getHeight()/2 - login.size.getHeight()/2 - 50));
   
        // Thread login_thread = new Thread( new Runnable(){
        //     public void run(){
        //         // login user
        //         while( Login.login_pass != true ){
        //             //System.out.println(" ");
        //         }

        //         System.out.println("sign in successfull");
        //         ref.sign_in();
                
        //         login.setVisible(false);
        //         login.updateUI();
        //     }
        // });
        // login_thread.start();
    
            
        SwingWorker sw = new SwingWorker(){
            @Override
            protected String doInBackground() throws Exception{
                while( !Login.login_hit ){
                   Thread.sleep(10);
                   System.out.println(Login.login_hit);
                }
               
                return String.valueOf(Login.login_hit);
            }

            @Override
            protected void done(){
                if( Login.login_hit == true ){
                System.out.println("done is called");
                sign_in();
                }
            } 
        };

        sw.execute();
    }

    private void sign_in(){
        System.out.println("sing is king");

           login.setVisible(false);
           login.updateUI();

           // add components
           setJMenuBar(menuBar);
           map.setLocation(0, (int) navigaionBar.size.getHeight());
           navigaionBar.setLocation(0, 0);
           add(map);
           add(navigaionBar);
           add(controlPallete);

           map.updateUI();
           navigaionBar.updateUI();
           controlPallete.updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Path_finder app = new Path_finder(800, 600);
                app.login();
            }
        });
    }

}