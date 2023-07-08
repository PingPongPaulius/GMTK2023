package Animations;

import Engine.Handler;
import IO.Mouse;
import Tokens.Character;
import Tokens.Dummy;
import Tokens.Token;

import java.awt.*;

public class HUD {

    private int SIZE = 100;
    private int start;

    Sprite up, down;
    public HUD(){
        up = new Sprite("Button", 100, 32);
        down = new Sprite("Button2", 100, 32);
        this.start = 0;
    }

    public void render(Graphics2D g, Mouse mouse){
        int x = 850, y = 100;

        up.render(g, 850, 50);

        if(mouse.clickedOn(new Rectangle(x, 50, 100, 32)) && start+6 < Handler.getTokens().size()){
            start += 1;
        }

        for(int i = start; i < Handler.getTokens().size(); i++){
            if(i >= start+6) {
                break;
            }
            Token t = Handler.getTokens().get(i);
            if(t instanceof Character c && !(c instanceof Dummy)){

                if (c.isRed()) g.setColor( new Color(124, 31, 31));
                else g.setColor( new Color(4, 107, 148));

                g.fillRect(x, y, SIZE, SIZE);
                c.getIcon().copy(64, 64).render(g, x+4, y+4);

                y+=SIZE+20;
            }
        }

        down.render(g, x, y);
        if(mouse.clickedOn(new Rectangle(x, y, 100, 32)) && start > 0){
            start -= 1;
        }
    }

}
