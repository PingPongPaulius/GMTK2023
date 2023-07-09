package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BodyGuard extends Character{

    public BodyGuard(int x, int y, boolean isRed) {
        super(x, y, isRed, "BodyGuard");
        this.speed = 200;
        this.health = 50;
        this.maxHealth = 10;
        this.startingHealth = 150;
        this.farDamage = 0;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 40;
        this.score = 1;
    }

    public Character findTarget(){
        return Map.BFS_Closest_Friend(x, y, this);
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
        Collections.shuffle(tiles);
        for(Tile t: tiles){
            if(!t.isEmpty()){
                Character enemy = t.contents.get();
                if(enemy.isEnemy(this)){
                    int damage = this.getMeleeDamage();
                    Console.log("Body Guard has inflicted: " + damage + " damage to " + enemy.getClass().getSimpleName());
                    enemy.takeDamage(damage);
                }
                break;
            }
        }
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
        Point move = this.handleDirectedMovement(target.x, target.y, moves);
        moveBy(move.x, move.y);

    }

    public Character copy(){
        return new BodyGuard(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Moves towards");
        out.add("Closest Friend");
        return out;
    }
}
