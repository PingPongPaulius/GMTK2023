package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Tank extends Character{

    public Tank(int x, int y, boolean isRed) {
        super(x, y, isRed, "Warrior");
        this.speed = 140;
        this.health = 50;
        this.maxHealth = 60;
        this.startingHealth = 50;
        this.farDamage = 0;
        this.closeMinDamage = 1;
        this.closeMaxDamage = 2;
        this.score = 1;
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
        return new Tank(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Moves towards an enemy unit.");
        out.add("Which deals largest damage");
        return out;
    }

}
