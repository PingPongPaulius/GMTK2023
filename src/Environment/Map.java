package Environment;

import java.awt.*;

public class Map {

    private Tile[][] map;

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
                Color c = new Color(0, 0, 0);
                if(counter % 2 == 0) c = new Color(255, 255, 255);

                map[i][j].render(g, 100+i*Tile.SIZE, 50+j*Tile.SIZE, c);
                counter++;
            }
        }
    }

}
