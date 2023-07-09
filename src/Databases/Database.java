package Databases;

import Engine.Game;
import Engine.Handler;
import Tokens.*;
import Tokens.Character;

import java.util.ArrayList;

public class Database {

    public static ArrayList<Character> characters = new ArrayList<>();

    public Database(){
        characters.add(new Archer(0,24, true));
        characters.add(new Assassin(0,24, true));
        characters.add(new Healer(0,24,true));
        characters.add(new Tank(0,24, true));
        characters.add(new Wizard(0,24,true));
        characters.add(new GoofyDevil(0, 24, true));
        characters.add(new BodyGuard(0, 24, true));
        characters.add(new Speedo(0,24,true));
        characters.add(new HillBilly(0,24,true));
        characters.add(new Lawyer(0,24, true));
    }

    public static void reverseRole(Character c){
        Handler.remove(c);
        Character reversed;
        if(c instanceof Archer){
            reversed = new Tank(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof Tank){
            reversed = new Archer(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof GoofyDevil){
            reversed = new Healer(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof Healer){
            reversed = new GoofyDevil(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof Assassin){
            reversed = new BodyGuard(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof BodyGuard){
            reversed = new Assassin(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof Wizard){
            reversed = new HillBilly(c.getX(), c.getY(), c.isRed());
        }
        else if(c instanceof HillBilly){
            reversed = new Wizard(c.getX(), c.getY(), c.isRed());
        }
        else{
            return;
        }

        reversed.health = c.health;
        if(reversed.health > reversed.startingHealth) reversed.health = reversed.startingHealth;

        Handler.add(reversed);
    }



}
