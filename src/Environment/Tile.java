package Environment;

import java.awt.*;

public class Tile {

    public static int SIZE = 16;
    public boolean path;
    public Tile(){
        this.path = true;
    }

    public void render(Graphics2D g, int x, int y, Color c){

        if(!path) c = new Color(0,0,0);
        g.setColor(c);

        g.fillRect(x, y, SIZE, SIZE);

    }

}
