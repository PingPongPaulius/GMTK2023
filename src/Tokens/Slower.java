package Tokens;

import Engine.Console;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Slower extends Character{
    public Slower(int x, int y, boolean isRed) {
        super(x, y, isRed, "Alien");
        this.speed = 200;
        this.health = 10;
        this.maxHealth = 10;
        this.startingHealth = 10;
        this.farDamage = 0;
        this.closeMinDamage = 40;
        this.closeMaxDamage = 41;
        this.target = null;
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        for(Tile[] tiles: Map.map){
            for(Tile t: tiles){
                if(Map.distBetween(t.point, new Point(x, y)) < 5 && !t.isEmpty()){
                    t.contents.get().speed = 200;
                }
            }
        }

    }

    public Character copy(){
        return new Slower(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Possibly and Alien");
        out.add("Does not fight");
        return out;
    }
}
