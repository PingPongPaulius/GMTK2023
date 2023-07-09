package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Lawyer extends Character{
    public Lawyer(int x, int y, boolean isRed) {
        super(x, y, isRed, "Patrick");
        this.speed = 80;
        this.health = 20;
        this.maxHealth = 20;
        this.startingHealth = 20;
        this.farDamage = 0;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 1;
        this.target = null;
        this.score = 1;
    }

    public Character findTarget(){
        return Map.BFS_Closest_Enmy(x, y, this);
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
        Collections.shuffle(tiles);
        for(Tile t: tiles){
            if(!t.isEmpty()){
                Character enemy = t.contents.get();
                if(enemy.isEnemy(this)){
                    enemy.isRed = !enemy.isRed;
                    enemy.target = null;
                }
                break;
            }
        }
    }

    @Override
    public void handleMovementLogic(){

        if(currMove <= speed) return;
        // Target might get killer so then the assassin should relocate.
        if(target == null || target.isRed == this.isRed ||!Handler.getTokens().contains(target)){
            target = findTarget();
        }
        // If Target is still null don't move.
        if(target == null){
            return;
        }

        boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        Point move = this.handleDirectedMovement(target.x, target.y, moves);
        moveBy(move.x, move.y);

        if (move.x == 0 && move.y == 0){
            super.handleMovementLogic();
        }

    }

    public Character copy(){
        return new Lawyer(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Convinces enemy");
        out.add("To switch sides.");
        return out;
    }
}
