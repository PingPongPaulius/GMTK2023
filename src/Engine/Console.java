package Engine;

import Environment.Tile;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Console {

    public static ArrayList<String> queue = new ArrayList<>();

    public Console(){
        queue = new ArrayList<>();
    }

    public static void render(Graphics2D g){
        while (queue.size() > 5) queue.remove(0);
        int y = 0;
        for(String info: queue) {
            g.drawString(info, 20, 80 + Game.MAP_SIZE * Tile.SIZE + y);
            y+=15;
        }
    }

    public static void log(String s){
        queue.add(s);
    }

}
