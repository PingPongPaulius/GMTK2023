package Tokens;

import Engine.Console;
import Engine.Game;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class Archer extends Character{

    Character target;

    public Archer(int x, int y, boolean isRed) {
        super(x, y, isRed, "Archer");
        this.speed = 100;
        this.health = 25;
        this.startingHealth = 25;
        this.maxHealth = 22;
        this.farDamage = 3;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 2;
        this.score = 2;
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
                    int damage = this.getMeleeDamage();
                    enemy.takeDamage(damage);
                    damaged = true;
                    Console.log("Archer Did Melee damage: " + damage + " to " + target.getClass().getSimpleName());
                }
            }
        }

        if(!damaged){
            int damage = 0;
            if(Map.distBetween(this, target) <= 5 || Game.RANDOM.nextInt(0, 2) == 1){
                damage = this.farDamage;
                target.takeDamage(damage);
            }
            Console.log("Archer did ranged damage: " + damage + " to " + target.getClass().getSimpleName());
        }

    }

    public void handleMovementLogic(){}

    public Character copy(){
        return new Archer(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Does not move");
        return out;
    }

}
