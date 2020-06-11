package source.util;


import source.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class RoundJButton extends JButton{
   
    public RoundJButton( String val) {
        setText(val);
        setOpaque(false); // As suggested by @AVD in comment.
        setContentAreaFilled(false);
        setFocusPainted(true);
        
        addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                if( e.getActionCommand().equals("Login")){
                    System.out.println("Login :" + Login.login_hit );    
                    Login.login_hit = true;
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
         super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
    }
}
