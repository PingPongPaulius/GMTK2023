package Databases;

import Engine.Game;
import Tokens.VIP;

import java.util.ArrayList;

public class Database {

    private static ArrayList<VIP> bosses = new ArrayList<>();

    public Database(){
        bosses.add(new VIP());
    }

    public static VIP getRandomBoss(){
        int index = Game.RANDOM.nextInt(bosses.size());
        return bosses.get(index);
    }

}
