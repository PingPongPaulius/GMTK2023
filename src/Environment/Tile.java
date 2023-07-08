package Environment;

import java.awt.*;

public class Tile {

    public Color c;

    public static int SIZE = 32;
    public boolean empty;
    public Tile(){
        this.empty = true;
    }

    public void render(Graphics2D g, int x, int y){

        g.setColor(c);

        g.fillRect(x, y, SIZE, SIZE);

    }

}
