package Environment;

import Tokens.Character;
import Tokens.Token;

import java.awt.*;
import java.util.Optional;

public class Tile {

    public Color c;
    public Optional<Character> contents;

    public static int SIZE = 32;
    public Tile(){
        this.contents = Optional.empty();
    }

    public boolean isEmpty(){
        return contents.isEmpty();
    }

    public void render(Graphics2D g, int x, int y){

        if(contents.isPresent()) {
            if (contents.get().isRed()) g.setColor( new Color(124, 31, 31));
            else g.setColor( new Color(4, 107, 148));
        }
        else {
            g.setColor(c);
        }
        g.fillRect(x, y, SIZE, SIZE);

    }

}
