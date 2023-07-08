package Tokens;

import Engine.Handler;
import Environment.Map;

public class Assasin extends Character{

    Character target;

    public Assasin(int x, int y, boolean isRed){
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
