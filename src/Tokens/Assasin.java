package Tokens;

public class Assasin extends Character{

    public Assasin(int x, int y, boolean isRed){
        super(x, y, isRed, "Assasin");
        this.speed = 80;
        this.health = 20;
        this.farDamage = 0;
        this.closeMinDamage = 5;
        this.closeMaxDamage = 7;
    }

}
