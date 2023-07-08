package Tokens;

import Animations.Sprite;
import Engine.Game;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class Character extends Token{

    Sprite sprite;
    int x, y;

    boolean isRed;

    protected int speed = 100;
    protected int health = 100;
    protected int closeMinDamage = 1;
    protected int closeMaxDamage = 2;
    protected int farDamage = 0;
    protected final int SIZE = 1;
    protected int currMove = 0;
    public Character(int x, int y, boolean isRed, String SpriteName){
        this.sprite = new Sprite(SpriteName, SIZE* Tile.SIZE,SIZE* Tile.SIZE);
        this.x = x;
        this.y = y;
        this.isRed = isRed;
    }

    public void setSide(boolean isRed){
        this.isRed = isRed;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        handleMovementLogic();
        handleFighting();
    }

    public void handleFighting(){

    }

    public void handleMovementLogic(){
        boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        int move = Game.RANDOM.nextInt(moves.length);

        if(moves[move] && currMove > speed){
            currMove = 0;
            System.out.println(Arrays.toString(moves));
            if(moves[move]) {
                Map.map[x][y].contents = Optional.empty();
                if (move == 0) x -= 1;
                if (move == 1) x += 1;
                if (move == 2) y += 1;
                if (move == 3) y -= 1;
                Map.map[x][y].contents = Optional.of(this);
            }
        }
        currMove++;
    }

    @Override
    public void render(Graphics2D g) {

        Point tile = Map.getCoordinate(x, y);
        sprite.render(g, tile.x, tile.y);

    }

    public boolean isRed(){
        return this.isRed;
    }

}
