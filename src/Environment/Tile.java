package Environment;

import Tokens.Character;
import Tokens.Token;

import java.awt.*;
import java.util.Optional;

public class Tile {

    public Color c;
    public Optional<Character> contents;
    public final Point point;
    public static int SIZE = 32;
    public boolean isMined;
    public Tile(int x, int y){
        this.contents = Optional.empty();
        this.point = new Point(x, y);
        isMined = false;
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

    public void placeMine(){
        isMined = true;
    }

    public void unMine(){
        isMined = false;
    }

}
