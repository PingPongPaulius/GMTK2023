package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class Assassin extends Character{

    Character target;

    public Assassin(int x, int y, boolean isRed){
        super(x, y, isRed, "Assasin");
        this.speed = 80;
        this.health = 20;
        this.maxHealth = 18;
        this.farDamage = 0;
        this.closeMinDamage = 6;
        this.closeMaxDamage = 12;
        this.target = null;
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
                    int damage = this.getMeleeDamage();
                    Console.log("Assassin has inflicted: " + damage + " damage to " + enemy.getClass().getSimpleName());
                    enemy.health -= damage;
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

        int x = 0, y = 0;

        if(target.x > this.x && moves[1]) x+=1;
        else if (target.x < this.x && moves[0]) x-=1;
        else if (target.y > this.y && moves[2]) y+=1;
        else if (target.y < this.y && moves[3]) y-=1;

        moveBy(x, y);

    }

}
