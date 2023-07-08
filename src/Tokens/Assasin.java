package Tokens;

import Engine.Game;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Assasin extends Character{

    Character target;

    public Assasin(int x, int y, boolean isRed){
        super(x, y, isRed, "Assasin");
        this.speed = 80;
        this.health = 20;
        this.farDamage = 0;
        this.closeMinDamage = 6;
        this.closeMaxDamage = 12;
        this.target = null;
    }

    public Character findTarget(){
        return Map.BFS(x, y, this);
    }

    @Override
    public void handleMovementLogic(){

        if(currMove <= speed) return;

        if(target == null || !Handler.getTokens().contains(target)){
            target = findTarget();
        }
        // If Target is still null don't move.
        if(target == null){
            return;
        }

        boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        int x = 0, y = 0;

        if(target.x > this.x && moves[1]) x+=1;
        else if (target.x < this.x && moves[0]) x-=1;
        else if (target.y > this.y && moves[2]) y+=1;
        else if (target.y < this.y && moves[3]) y-=1;

        moveBy(x, y);

    }

}
