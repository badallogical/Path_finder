package source;



import source.util.*;

import java.awt.event.*;
import javax.swing.border.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {

    public static final Dimension size;
    public static boolean login_hit = false;
    Color shadow_color;
    int offset = 10;


    RoundJTextField user_field;
    RoundJPasswordField passwd_field;
    RoundJButton login_bt;
    RoundJButton register_link;
    

    static{
        size = new Dimension(400,400);
    }

    public Login(){
        // Initialization
        shadow_color = new Color(50, 50, 50, 150);
        user_field = new RoundJTextField(200,"User Name");
        passwd_field = new RoundJPasswordField(200,"Password");
        login_bt = new RoundJButton("Login");
        register_link = new RoundJButton("Create New Accout ?");
       
         
        // add components;
        add( user_field );
        user_field.setBounds(offset + 100 , offset + 50, 200, 30);
        user_field.setFont( new Font("Arial", Font.BOLD, 15));

        add( passwd_field );
        passwd_field.setBounds(offset + 100, offset + 50*2 , 200 , 30);
        passwd_field.setFont( new Font("Arial", Font.BOLD, 15));

        add( login_bt );
        login_bt.setBounds(offset + 100 , offset + 50 * 4, 200, 30);
        login_bt.setFont( new Font("Arial", Font.BOLD, 15));

        add( register_link );
        register_link.setBounds(offset + 200, offset + 50 * 6, 150, 30);
        register_link.setFont( new Font("Arial", Font.BOLD, 10) );


        //setBorder( BorderFactory.createLineBorder(Color.BLUE));
        setLayout( null );
        setSize( size );
        setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor( shadow_color );
        ((Graphics2D)g).fillRoundRect(2 * offset, 2 * offset, (int)size.getWidth() - 2 * offset, (int)size.getHeight() - 2 * offset, 50, 50);
        
        Paint p = new GradientPaint(0.0f, 0.0f, new Color(200, 50, 100),
        getWidth(), getHeight(), new Color(200, 50, 100), true);

        ((Graphics2D)g).setPaint(p);
        
        ((Graphics2D)g).fillRoundRect(offset, offset, (int)size.getWidth() - 2 * offset, (int)size.getHeight() - 2 * offset, 50, 50);
    }
}







