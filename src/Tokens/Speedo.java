package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Speedo extends Character{

    public Speedo(int x, int y, boolean isRed) {
        super(x, y, isRed, "Speedo");
        this.speed = 20;
        this.health = 10;
        this.maxHealth = 10;
        this.startingHealth = 10;
        this.farDamage = 0;
        this.closeMinDamage = 40;
        this.closeMaxDamage = 41;
        this.target = null;
    }

    public Character findTarget(){
        return Map.BFS_Furthest_Shooting(this);
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
        Collections.shuffle(tiles);
        for(Tile t: tiles){
            if(!t.isEmpty()){
                Character enemy = t.contents.get();
                if(enemy.isEnemy(this) && enemy.getFarDamage() > 0){
                    int damage = this.getMeleeDamage();
                    Console.log("Speedo has inflicted: " + damage + " damage to " + enemy.getClass().getSimpleName());
                    enemy.takeDamage(damage);
                }
                break;
            }
        }
    }

    @Override
    public void handleMovementLogic(){

        if(currMove <= speed) return;
        // Target might get killer so then the assassin should relocate.
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

        if (move.x == 0 && move.y == 0){
            super.handleMovementLogic();
        }

    }

    public Character copy(){
        return new Speedo(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Focuses a furthest enemy.");
        out.add("Ignores melee damage units.");
        return out;
    }
}
