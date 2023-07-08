package Databases;

import Engine.Game;
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
    }



}
