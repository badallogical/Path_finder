package source;

import java.awt.*;
import javax.imageio.*;
import java.io.IOException;
import java.io.File;

public class NodeAssets {

    static Image icon1, icon2, icon3;
    static Image source_icon, end_icon;
    static Image root_icon, dest_icon, visited;

    static {
        // load image
        try {
            icon1 = ImageIO.read(new File("Assets/locate.png"));
            icon1 = icon1.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);
            icon2 = ImageIO.read(new File("Assets/locate-hl.png"));
            icon2 = icon2.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);
            icon3 = ImageIO.read(new File("Assets/locate-sl.png"));
            icon3 = icon3.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);

            source_icon = ImageIO.read(new File("Assets/src.png"));
            source_icon = source_icon.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);
            end_icon = ImageIO.read(new File("Assets/end.png"));
            end_icon = end_icon.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);

            // for BFS
            root_icon = ImageIO.read(new File("Assets/root.png"));
            root_icon = root_icon.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);
            dest_icon = ImageIO.read(new File("Assets/destination.png"));
            dest_icon = dest_icon.getScaledInstance(Node.icon_size, Node.icon_size, Image.SCALE_SMOOTH);
            visited = ImageIO.read(new File("Assets/visited.png"));
            visited = visited.getScaledInstance(Node.icon_size , Node.icon_size , Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}