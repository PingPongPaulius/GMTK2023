package Tokens;

import Engine.Game;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class Archer extends Character{

    Character target;

    public Archer(int x, int y, boolean isRed) {
        super(x, y, isRed, "Skelleton");
        this.speed = 100;
        this.health = 40;
        this.maxHealth = 37;
        this.farDamage = 3;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 2;
        this.target = null;
    }

    public Character findTarget(){
        return Map.BFS_Closest_Enmy(x, y, this);
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        target = findTarget();
        // If Target is still null don't move.
        if(target == null){
            return;
        }

        boolean damaged = false;
        ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
        Collections.shuffle(tiles);
        for(Tile t: tiles){
            if(!t.isEmpty()){
                Character enemy = t.contents.get();
                if(enemy.isEnemy(this)){
                    enemy.health -= this.getMeleeDamage();
                    damaged = true;
                }
            }
        }

        if(!damaged){
            if(Map.distBetween(this, target) <= 5 || Game.RANDOM.nextInt(0, 2) == 1){
                target.health -= this.farDamage;
            }
        }

    }

    public void handleMovementLogic(){}

}
