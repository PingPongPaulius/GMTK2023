package Tokens;

import Engine.Game;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class GoofyDevil extends Character{
    public GoofyDevil(int x, int y, boolean isRed) {
        super(x, y, isRed, "DevilBoss");
        this.speed = 80;
        this.health = 1;
        this.startingHealth = 1;
        this.maxHealth = 100;
        this.farDamage = 0;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 1;
    }

    public void handleFighting(){

        if(currMove <= speed) return;

        ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
        Collections.shuffle(tiles);
        for(Tile t: tiles){
            if(!t.isEmpty()){
                Character enemy = t.contents.get();
                enemy.takeDamage(enemy.maxHealth);
                this.health = 0;
            }
        }
    }

    public void handleMovementLogic(){

        if(currMove <= speed) return;

        int x = Game.RANDOM.nextInt(0, 25);
        int y = Game.RANDOM.nextInt(0, 25);

        Tile t = Map.map[x][y];

        if(t.isEmpty()){
            Map.map[this.x][this.y].contents = Optional.empty();
            this.x = x;
            this.y = y;
            Map.map[this.x][this.y].contents = Optional.of(this);
        }

    }

    public Character copy(){
        return new GoofyDevil(0,24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = new ArrayList<String>();
        out.add("Moves randomly");
        out.add("One-shots anything adjacent");
        out.add("dies after a one-shot");
        out.add("gold coin cost: " + score);
        return out;
    }

}
