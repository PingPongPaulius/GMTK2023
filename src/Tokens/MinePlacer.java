package Tokens;

import Engine.Console;
import Engine.Game;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class MinePlacer extends Character {

    int bombs = 5;
    Tile dest;
    public MinePlacer(int x, int y, boolean isRed) {
        super(x, y, isRed, "Miner");
        this.speed = 40;
        this.health = 22;
        this.maxHealth = 20;
        this.startingHealth = 22;
        this.farDamage = 0;
        this.closeMinDamage = 0;
        this.closeMaxDamage = 1;
        dest = null;
    }

    public void handleMovementLogic(){

        if(currMove <= speed) return;

        if(dest == null && bombs > 0) {
            int x = Game.RANDOM.nextInt(Game.MAP_SIZE);
            int y = Game.RANDOM.nextInt(Game.MAP_SIZE);
            dest = Map.map[x][y];
        }

        // If Target is still null don't move.
        if(dest == null){
            return;
        }

        boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        Point move = this.handleDirectedMovement(dest.point.x, dest.point.y, moves);
        moveBy(move.x, move.y);

        if (move.x == 0 && move.y == 0){
            super.handleMovementLogic();
        }

        if((Map.getAdjacentTiles(x, y).contains(dest))){
            bombs -= 1;
            dest.placeMine();
            dest = null;
            Console.log("MinePlacer Has placed a Land Mine");
        }

    }

    public void handleFighting(){

    }

    public Character copy(){
        return new MinePlacer(0, 24, true);
    }

    public ArrayList<String> parseInfo(){
        var out = super.parseInfo();
        out.add("places Land mines");
        return out;
    }

}
