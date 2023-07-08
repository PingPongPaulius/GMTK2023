package Engine;

import Environment.Map;
import Environment.Tile;
import Tokens.Token;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class Handler {

    private static final ArrayList<Token> tokens = new ArrayList<>();;
    // List of object that have to be removed this frame
    private static final ArrayList<Token> tokensToRemove = new ArrayList<>();
    // List of objects that have to be added next frame
    private static final ArrayList<Token> tokensToAdd = new ArrayList<>();

    public void loop(Graphics2D g){
        for (Token t: tokensToRemove) tokens.remove(t);
        tokens.addAll(tokensToAdd);

        tokensToAdd.clear();
        tokensToRemove.clear();

        for (Token t: tokens) t.update();
        for (Token t: tokens) t.render(g);
    }

    public static void add(Token t){
        tokensToAdd.add(t);
    }

    public static void remove(Token t){
        tokensToRemove.add(t);
    }

    public static ArrayList<Token> getTokens(){
        return tokens;
    }

    public static ArrayList<Token> getTokensToAdd(){return tokensToAdd;}

    public static void clear(){
        tokens.clear();
        tokensToRemove.clear();
        tokensToAdd.clear();
        for(Tile[] tA: Map.map){
            for(Tile t: tA) t.contents = Optional.empty();
        }
    }

}
