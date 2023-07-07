package Environment;

import Engine.Game;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private final Tile[][] map;

    public Map(int x, int y){

        map = new Tile[y][x];

        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                map[i][j] = new Tile();
            }
        }

    }

    public void render(Graphics2D g){
        int counter = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                Color c = new Color(100, 97, 97);
                if(counter % 2 == 0) c = new Color(168, 161, 161);

                map[i][j].render(g, 85+i*Tile.SIZE, 50+j*Tile.SIZE, c);
                counter++;
            }
        }
    }

    public static Point getCoordinate(int x, int y){
        return new Point(85+x*Tile.SIZE, 50+y*Tile.SIZE);
    }

    public static boolean[] getPossibleMoves(int x, int y, int size){
        boolean[] moves = new boolean[4];
        moves[0] = (x > 0); // Left
        moves[1] = (x+size < Game.MAP_SIZE); // Right
        moves[2] = (y+size < Game.MAP_SIZE); // Down
        moves[3] = (y > 0); // UP
        return moves;
    }

}
