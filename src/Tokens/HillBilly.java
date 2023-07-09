package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class HillBilly extends Character{
    public HillBilly(int x, int y, boolean isRed) {
        super(x, y, isRed, "HillBilly");
        this.speed = 70;
        this.health = 30;
        this.maxHealth = 10;
        this.startingHealth = 10;
        this.farDamage = 0;
        this.closeMinDamage = 5;
        this.closeMaxDamage = 21;
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
        Collections.shuffle(tiles);
        for(Tile t: tiles){
            if(!t.isEmpty()){
                Character enemy = t.contents.get();
                int damage = this.getMeleeDamage();
                Console.log("Hill Billy has inflicted: " + damage + " damage to " + enemy.getClass().getSimpleName());
                enemy.takeDamage(damage);
                }
                break;
            }
    }

    public Character copy(){
        return new HillBilly(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Moves Randomly");
        out.add("Attacks everyone");
        out.add("Drunk");
        return out;
    }
}
