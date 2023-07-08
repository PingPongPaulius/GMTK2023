package Databases;

import Engine.Game;
import Tokens.Character;

import java.util.ArrayList;

public class Database {

    private static ArrayList<Character> bosses = new ArrayList<>();

    public Database(){

    }

    public static Character getRandomBoss(){
        int index = Game.RANDOM.nextInt(bosses.size());
        return bosses.get(index);
    }

}
