package source;

import java.awt.*;
import java.io.Serializable;

// helper class
public class Path  implements Serializable{
    public static int path_style = 0;
    public static final int plain_style = 0;
    public static final int dashed_style = 1;

    public int link_to;
    public Color path_color;
    public int alpha = 200;
    public int distance;

    Path(int link_to, Color path_color, int distance) {
        this.link_to = link_to;
        this.path_color = new Color(path_color.getRed(), path_color.getGreen(), path_color.getBlue(), alpha);
        this.distance = distance;
    }
}