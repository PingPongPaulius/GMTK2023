package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class Healer extends Character{

    Character target;
    public Healer(int x, int y, boolean isRed) {
        super(x, y, isRed, "Healer");
        this.speed = 100;
        this.health = 10;
        this.maxHealth = 9;
        this.farDamage = 0;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 1;
        target = null;
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
                Character friend = t.contents.get();
                if(!friend.isEnemy(this) && friend.health < friend.maxHealth){
                    Console.log("Healer has healed " + friend.getClass().getSimpleName());
                    friend.health += 1;
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
            System.out.println(target);
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
