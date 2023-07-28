package Tokens;

import Engine.Console;
import Engine.Game;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Wizard extends Character{

    public Wizard(int x, int y, boolean isRed) {
        super(x, y, isRed, "Wizard");
        this.speed = 200;
        this.health = 15;
        this.maxHealth = 25;
        this.startingHealth = 15;
        this.farDamage = 5;
        this.closeMinDamage = 3;
        this.closeMaxDamage = 6;
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

    @Override
    public void update() {

        if(currMove > speed) {
            String[] actions = new String[]{"Attack", "Move", "Spell"};

            String action = actions[Game.RANDOM.nextInt(actions.length)];

            switch (action) {
                case "Attack" -> {
                    handleFighting();
                }
                case "Move" -> {
                    handleMovementLogic();
                }
                default -> {
                    fake();
                }
            }
            if (health <= 0) {
                Map.map[x][y].contents = Optional.empty();
                Handler.remove(this);
            }
            currMove = 0;
        }
        currMove+=Game.speedUp;
    }

    public void fake(){
        int x = Game.RANDOM.nextInt(Game.MAP_SIZE);
        int y = Game.RANDOM.nextInt(Game.MAP_SIZE);

        if(Map.map[x][y].isEmpty()){
            Console.log("Wizard Casted a distraction Spell");
            Handler.add(new Dummy(x, y, this.isRed));
        }

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
                    Console.log("Wizard Did Melee damage: " + damage + " to " + target.getClass().getSimpleName());
                }
            }
        }

        if(!damaged){
            int damage = 0;
            if(Map.distBetween(this, target) <= 5 || Game.RANDOM.nextInt(2) == 1){
                damage = this.farDamage;
                target.takeDamage(damage);
            }
            Console.log("Wizard did ranged damage: " + damage + " to " + target.getClass().getSimpleName());
        }

    }

    public Character copy(){
        return new Wizard(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("Moves randomly");
        out.add("Shots randomly");
        out.add("Places dummies");
        out.add("To Distract enemies");
        return out;
    }

}
