package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class Tank extends Character{

    Character target;

    public Tank(int x, int y, boolean isRed) {
        super(x, y, isRed, "Warrior");
        this.speed = 180;
        this.health = 210;
        this.maxHealth = 200;
        this.farDamage = 0;
        this.closeMinDamage = 1;
        this.closeMaxDamage = 2;
        target = null;
    }

    public Character findTarget(){

        Character targetCharacter = null;

        for(Token t: Handler.getTokens()){
            if(t instanceof Character c){
                if(c.isEnemy(this)){
                    if(targetCharacter == null) targetCharacter = c;
                    else if(targetCharacter.closeMaxDamage <= c.closeMaxDamage){
                        targetCharacter = c;
                    }
                }
            }
        }

        return Map.BFS_Character(x, y, targetCharacter);
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
                    Console.log("Tank has inflicted: " + damage + " damage to " + enemy.getClass().getSimpleName());
                    enemy.health -= damage;
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

        int x = 0, y = 0;

        if(target.x > this.x && moves[1]) x+=1;
        else if (target.x < this.x && moves[0]) x-=1;
        else if (target.y > this.y && moves[2]) y+=1;
        else if (target.y < this.y && moves[3]) y-=1;

        moveBy(x, y);

    }

}
