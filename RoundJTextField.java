package source.util;

import java.awt.color.*;
import javax.swing.*;
import java.awt.Graphics;

public class RoundJTextField extends JTextField {
   
    public RoundJTextField(int size, String val) {
        super(size);
        setText(val);
        setOpaque(false); // As suggested by @AVD in comment.
    }

    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
   
}