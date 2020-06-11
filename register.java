package source;

import source.util.*;
import java.awt.*;
import javax.swing.*;

public class register extends JPanel{
    
    public static Dimension size;
    Color shadow_color;
    int offset = 10;

    // components
    RoundJTextField name;
    RoundJPasswordField password;
    RoundJPasswordField confirm_password;

    public register(final int width,final int height){
        // initialization
        shadow_color = new Color( 50,50,50,150);
        size = new Dimension( width, height );
        name = new RoundJTextField(200,"Name");
        password = new RoundJPasswordField(200, "Password");
        confirm_password = new RoundJPasswordField(200, "Password");

        //add components;
        add( name );
        name.setBounds(offset + 100, offset + 50, 200, 30);
        name.setFont( new Font("Arial", Font.BOLD, 15));
        
        add( password );
        password.setBounds(offset + 100, offset + 50, 200, 30 );
        password.setFont( new Font("Arial", Font.BOLD, 15));

        add( confirm_password );
        confirm_password.setBounds( offset + 100, offset + 50, 200, 30);
        confirm_password.setFont( new Font("Arial", Font.BOLD, 15));

        setBorder( BorderFactory.createLineBorder(Color.BLUE));
        setLayout(null);
        setSize(size);
        setVisible(true);
    }

}