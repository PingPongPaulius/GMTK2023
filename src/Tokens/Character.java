package Tokens;

import Animations.Sprite;
import Engine.Game;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.Arrays;

public class Character extends Token{

    Sprite sprite;
    int x, y;

    private int speed = 100;

    private final int SIZE = 1;
    private int currMove = 0;
    public Character(){
        this.sprite = new Sprite("Assasin", SIZE* Tile.SIZE,SIZE* Tile.SIZE);
        x = 0;
        y = 0;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        handleMovementLogic();
    }

    public void handleMovementLogic(){
        boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        int move = Game.RANDOM.nextInt(moves.length);

        if(moves[move] && currMove > speed){
            currMove = 0;
            System.out.println(Arrays.toString(moves));
            if(moves[move]) {
                if (move == 0) x -= 1;
                if (move == 1) x += 1;
                if (move == 2) y += 1;
                if (move == 3) y -= 1;
            }
        }
        currMove++;
    }

    @Override
    public void render(Graphics2D g) {

        Point tile = Map.getCoordinate(x, y);
        sprite.render(g, tile.x, tile.y);
    }
}
