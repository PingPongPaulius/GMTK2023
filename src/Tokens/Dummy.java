package Tokens;

import Engine.Handler;
import Environment.Map;

import java.util.Optional;

public class Dummy extends Character{
    public Dummy(int x, int y, boolean isRed) {
        super(x, y, isRed, "Skelleton");
        this.speed = 2000;
        this.health = 1;
        this.maxHealth = 1;
        this.farDamage = 10;
        this.closeMinDamage = 100;
        this.closeMaxDamage = 200;
    }

    @Override
    public void update() {
        if(health <= 0) {
            Map.map[x][y].contents = Optional.empty();
            Handler.remove(this);
        }
    }

}
