package Environment;

import Engine.Game;
import Tokens.Character;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Map {

    public final static Tile[][] map = new Tile[Game.MAP_SIZE][Game.MAP_SIZE];

    public Map(){
        int counter = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                Color c = new Color(100, 97, 97);
                if(counter % 2 == 0) c = new Color(168, 161, 161);
                Tile tile = new Tile(i,j);
                tile.c = c;
                map[i][j] = tile;
                counter++;
            }
        }
    }

    public static Character BFS_Closest_Enmy(int x, int y, Character t){

        Tile root = map[x][y];
        ArrayList<Tile> visited = new ArrayList<>();
        Queue<Tile> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){

            Tile tile = queue.poll();
            visited.add(tile);
            ArrayList<Tile> tiles = getAdjacentTiles(tile.point.x,tile.point.y);
            Collections.shuffle(tiles);

            for(Tile newTile: tiles){
                if(!newTile.isEmpty() && newTile.contents.get().isEnemy(t)){
                    return newTile.contents.get();
                }
                else if(!visited.contains(newTile)){
                    queue.add(newTile);
                    visited.add(newTile);
                }
            }
        }
        return null;
    }

    public static Character BFS_Closest_Friend(int x, int y, Character t){

        Tile root = map[x][y];
        ArrayList<Tile> visited = new ArrayList<>();
        Queue<Tile> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){

            Tile tile = queue.poll();
            visited.add(tile);
            ArrayList<Tile> tiles = getAdjacentTiles(tile.point.x,tile.point.y);
            Collections.shuffle(tiles);

            for(Tile newTile: tiles){
                if(!newTile.isEmpty()){
                    Character c = newTile.contents.get();

                    if(!c.isEnemy(t) && c!=t) {
                        return newTile.contents.get();
                    }
                }
                else if(!visited.contains(newTile)){
                    queue.add(newTile);
                    visited.add(newTile);
                }
            }
        }
        return null;
    }

    public static Character BFS_Character(int x, int y, Character t){

        Tile root = map[x][y];
        ArrayList<Tile> visited = new ArrayList<>();
        Queue<Tile> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){

            Tile tile = queue.poll();
            visited.add(tile);
            ArrayList<Tile> tiles = getAdjacentTiles(tile.point.x,tile.point.y);
            Collections.shuffle(tiles);

            for(Tile newTile: tiles){
                if(!newTile.isEmpty() && newTile.contents.get() == t){
                    return newTile.contents.get();
                }
                else if(!visited.contains(newTile)){
                    queue.add(newTile);
                    visited.add(newTile);
                }
            }
        }
        return null;
    }

    public void render(Graphics2D g){

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j].render(g, 20+i*Tile.SIZE, 50+j*Tile.SIZE);
            }
        }
    }

    public static int distBetween(Character a, Character b){

        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());

    }

    public static Point getCoordinate(int x, int y){
        return new Point(20+x*Tile.SIZE, 50+y*Tile.SIZE);
    }

    public static Tile getTile(int pixelsX, int pixelsY){
        int x = (pixelsX-20)/Tile.SIZE;
        int y = (pixelsY-50)/Tile.SIZE;
        System.out.println(x + " " + y);
        return map[x][y];
    }

    public static boolean[] getPossibleMoves(int x, int y, int size){
        boolean[] moves = new boolean[4];

        moves[0] = (x > 0) && map[x-1][y].isEmpty();// Left
        moves[1] = (x+size < Game.MAP_SIZE) && map[x+1][y].isEmpty();// Right
        moves[2] = (y+size < Game.MAP_SIZE) && map[x][y+1].isEmpty();; // Down
        moves[3] = (y > 0) && map[x][y-1].isEmpty(); // Up

        return moves;
    }

    public static ArrayList<Tile> getAdjacentTiles(int x, int y){
        var moves = new ArrayList<Tile>();
        int size = 1;
        if((x > 0)){
            moves.add(map[x-1][y]);
        }
        if((x+size < Game.MAP_SIZE)){
            moves.add(map[x+1][y]);
        }
        if((y+size < Game.MAP_SIZE)){
            moves.add(map[x][y+1]);
        }
        if((y > 0)){
            moves.add(map[x][y-1]);
        }

        return moves;
    }

}
