package Tokens;

import Animations.AnimationHandler;
import Animations.Sprite;
import Engine.Game;
import Environment.Map;

import java.awt.*;
import java.util.Arrays;

public class VIP extends Token{

    Sprite sprite;
    int x, y;

    private int speed = 100;

    private final int SIZE = 2;
    private int currMove = 0;
    public VIP(){
        this.sprite = new Sprite("DevilBoss", SIZE*16,SIZE*16);
        x = 0;
        y = 0;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        int move = Game.RANDOM.nextInt(moves.length);

        if(moves[move] && currMove > speed){
            currMove = 0;
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
