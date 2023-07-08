package Environment;

import Engine.Game;

import java.awt.*;

public class Map {

    public final static Tile[][] map = new Tile[Game.MAP_SIZE][Game.MAP_SIZE];

    public Map(){
        int counter = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                Color c = new Color(100, 97, 97);
                if(counter % 2 == 0) c = new Color(168, 161, 161);
                Tile tile = new Tile();
                tile.c = c;
                map[i][j] = tile;
                counter++;
            }
        }
    }

    public void render(Graphics2D g){

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j].render(g, 20+i*Tile.SIZE, 50+j*Tile.SIZE);
            }
        }
    }

    public static Point getCoordinate(int x, int y){
        return new Point(85+x*Tile.SIZE, 50+y*Tile.SIZE);
    }

    public static boolean[] getPossibleMoves(int x, int y, int size){
        boolean[] moves = new boolean[4];

        moves[0] = (x > 0) && map[x-1][y].empty;// Left
        moves[1] = (x+size < Game.MAP_SIZE) && map[x+1][y].empty;// Right
        moves[2] = (y+size < Game.MAP_SIZE) && map[x][y+1].empty;; // Down
        moves[3] = (y > 0) && map[x][y-1].empty; // UP

        return moves;
    }

}
