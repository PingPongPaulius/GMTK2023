package Tokens;

import Animations.Sprite;
import Databases.Database;
import Engine.Game;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class Character extends Token{

    protected Sprite sprite;
    protected int x, y;

    protected boolean isRed;

    protected int speed = 100;
    public int health = 100;
    protected int maxHealth = 100;
    protected int closeMinDamage = 1;
    protected int closeMaxDamage = 2;
    protected int farDamage = 0;
    protected final int SIZE = 1;
    protected int currMove = 0;

    public int score = 1;

    public int startingHealth = 100;

    protected Character target;
    public Character(int x, int y, boolean isRed, String SpriteName)                                {
        this.sprite = new Sprite(SpriteName, SIZE* Tile.SIZE,SIZE* Tile.SIZE)                   ;
        this.x = x                                                                                      ;
        this.y = y                                                                                      ;
        Map.map[x][y].contents = Optional.of(this)                                              ;
        this.isRed = isRed                                                                              ;
        currMove = 0                                                                                    ;
        target= null                                                                                    ;
                                                                                                    }

    public Sprite getIcon(){
        return this.sprite                                                          ;
    }

                                                        public void setSide(boolean isRed){
                                                            this.isRed = isRed;
    }

    public void setPos(int x, int y)                                                    {
                                                                                    Map.map[this.x][this.y].contents = Optional.empty();
        this.x = x;
                                                                                                            this.y = y;
                                        Map.map[this.x][this.y].contents = Optional.of(this);
                                                                                            if(Map.map[this.x][this.y].isMined) {
                                                               this.health -= 50;
                                                    Map.map[this.x][this.y].unMine();}
                                                        }

    @Override
    public void update() {
        handleMovementLogic();
        handleFighting();
        if(health <= 0) {
            Map.map[x][y].contents = Optional.empty();
            Handler.remove(this);
        }
        if(currMove > speed) currMove = 0;
        currMove+=20;
    }

    public void handleFighting(){

        if(currMove <= speed) return;

                                                            ArrayList<Tile> tiles = Map.getAdjacentTiles(x, y);
                                                                Collections.shuffle(tiles);
                                                                                                                for(Tile t: tiles){
                    if(!t.isEmpty()){
                                                                Character enemy = t.contents.get();
                if(enemy.isEnemy(this)){
                                                                                enemy.takeDamage(getMeleeDamage());
                }
                break;
            }
        }
    }

    public void handleMovementLogic(){
                                                                    boolean[] moves = Map.getPossibleMoves(x, y, SIZE);

        int move = Game.RANDOM.nextInt(moves.length);

        if(moves[move] && currMove > speed){
            if(moves[move]) {
                                                        int x = 0, y = 0;
                if (move == 0) x -= 1;
                                                                                                                        if (move == 1) x += 1;if (move == 2) y += 1;
                if (move == 3) y -= 1;
                moveBy(x, y);
            }
        }

    }

    public void takeDamage(int x){
                                                                                            health -= x;
                            this.sprite.overlay(x > 0);
    }

    public Point handleDirectedMovement(int goalX, int goalY, boolean[] moves){

                                                                    ArrayList<Point> possibleMoves = new ArrayList<>();

        if(goalX > this.x && moves[1]) possibleMoves.add(new Point(1, 0));
                                                                                                                if (goalX < this.x && moves[0]) possibleMoves.add(new Point(-1, 0));
                                                                        if (goalY > this.y && moves[2]) possibleMoves.add(new Point(0, 1));
        if (goalY < this.y && moves[3]) possibleMoves.add(new Point(0, -1));

        if(possibleMoves.isEmpty()) return new Point(0, 0);

        return possibleMoves.get(Game.RANDOM.nextInt(possibleMoves.size()));
    }

    public int getMeleeDamage(){
                                                        return Game.RANDOM.nextInt(this.closeMaxDamage) + this.closeMinDamage;
    }

    @Override
    public void render(Graphics2D g) {

                                                                Point tile = Map.getCoordinate(x, y);
                                    sprite.render(g, tile.x, tile.y);

    }

    public boolean isRed(){
        return this.isRed;
    }

    public boolean isEnemy(Character token){
        return token.isRed != this.isRed;
    }

    public void moveBy(int x, int y){
        Map.map[this.x][this.y].contents = Optional.empty();
                                                                                                                        this.x += x;
                                                                            this.y += y;
        Map.map[this.x][this.y].contents = Optional.of(this);
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getFarDamage() {
        return farDamage;
    }

    public void changeSides(){
        isRed = !isRed;
    }

    public Character copy(){
        return null;
    }

    public ArrayList<String> parseInfo(){
        var output = new ArrayList<String>();
                                                                    output.add("Speed: " + speed);
                                                                                output.add("Max Health: " + maxHealth);
                                                        output.add("Min Melee Damage: " + closeMinDamage);
                                                                                                 output.add("Max Melee Damage: " + closeMaxDamage);
                                                                                                                    output.add("Ranged Damage: " + farDamage);
                                                                    output.add("Gold Coin Cost: " + score);
                                                                                                 return output;
    }

}
