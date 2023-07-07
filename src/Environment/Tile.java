package Environment;

import java.awt.*;

public class Tile {

    public static int SIZE = 16;

    public Tile(){

    }

    public void render(Graphics2D g, int x, int y, Color c){

        g.setColor(c);
        g.fillRect(x, y, SIZE, SIZE);

    }

}
