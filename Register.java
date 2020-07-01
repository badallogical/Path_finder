package source;

import source.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Register extends JPanel{
    
    public static Dimension size;
    Color shadow_color;
    int offset = 10;

    // components
    RoundJTextField name;
    RoundJPasswordField password;
    RoundJPasswordField confirm_password;

    JLabel lname, lpass, lconfirm;

    public Register(final int width,final int height){
        // initialization
        shadow_color = new Color( 50,50,50,150);
        size = new Dimension( width, height );
        name = new RoundJTextField(200,"Name");
        password = new RoundJPasswordField(200, "Password");
        confirm_password = new RoundJPasswordField(200, "Password");
        lname = new JLabel("Name");
        lpass = new JLabel("Password");
        lconfirm = new JLabel("Confirm Password");

        //add components;
        add(lname);
        lname.setBounds( offset + 100, offset + 20 , 100, 20 );
        lname.setFont( new Font("Ubuntu", Font.BOLD, 20 ));

        add( name );
        name.setBounds(offset + 100, offset + 50, 200, 30);
        name.setFont( new Font("Arial", Font.BOLD, 15));
        
        add( password );
        password.setBounds(offset + 100, offset + 2 * 50, 200, 30 );
        password.setFont( new Font("Arial", Font.BOLD, 15));

        add( confirm_password );
        confirm_password.setBounds( offset + 100, offset + 3 *  50, 200, 30);
        confirm_password.setFont( new Font("Arial", Font.BOLD, 15));

        setBorder( BorderFactory.createLineBorder(Color.BLUE));
        setLayout(null);
        setSize(size);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g ){
        super.paintComponent(g);

        ((Graphics2D)g).setRenderingHint(  RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        
        g.setColor( shadow_color );
        ((Graphics2D)g).fillRoundRect( 2 * offset, 2 * offset, (int)size.getWidth() - 2 * offset , (int)size.getHeight() - 2 * offset, 50 , 50);
        
        // set linear gradient 
        Paint p = new GradientPaint( 0.0f, 0.0f, new Color( 50,200,10), getHeight(), getWidth(), new Color( 50,200,10), true);
        ((Graphics2D)g).setPaint( p );

        ((Graphics2D)g).fillRoundRect( offset, offset, (int)size.getWidth() - 2 * offset, (int)size.getHeight() - 2 * offset, 50, 50 );
    }

}