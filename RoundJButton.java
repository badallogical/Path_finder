package source.util;


import source.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class RoundJButton extends JButton{

    Color visitedColor = null;
    Color defaultColor = null;

    public RoundJButton( String val) {
        setText(val);
        setOpaque(false); // As suggested by @AVD in comment.
        setContentAreaFilled(false);
        setFocusPainted(false);
        
        addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // TODO: make proper login system
                if( e.getActionCommand().equals("Login")){
                    System.out.println("Login :" + Login.login_hit );    
                    Login.login_hit = true;
                }
            }
        });

        addMouseListener( new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                visitedColor = new Color(200,200,200,150);
            }

            @Override
            public void mouseExited(MouseEvent e){
                super.mouseExited(e);
                visitedColor = null;
            }

        });
    }

    protected void paintComponent(Graphics g) {
         if( defaultColor == null )
             defaultColor = getBackground();
         g.setColor(( visitedColor == null) ? defaultColor : visitedColor );
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
         super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
    }
}
