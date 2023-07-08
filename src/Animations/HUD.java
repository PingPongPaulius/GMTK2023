package Animations;

import Databases.Database;
import Engine.Game;
import Engine.Handler;
import Environment.Map;
import Environment.Tile;
import IO.Mouse;
import Tokens.Character;
import Tokens.Dummy;
import Tokens.Token;

import java.awt.*;
import java.util.ArrayList;

public class HUD {

    private int SIZE = 100;
    private int start;

    Sprite up, down, startButton;

    private Character selected;
    public HUD(){
        up = new Sprite("Button", 100, 32);
        down = new Sprite("Button2", 100, 32);
        startButton = new Sprite("Button3", 100, 32);
        this.start = 0;
        this.selected = null;
    }

    public void render(Graphics2D g, Mouse mouse) {
        int x = 850, y = 100;

        up.render(g, 850, 50);

        if (mouse.clickedOn(new Rectangle(x, 50, 100, 32)) && start + 6 < Handler.getTokens().size()) {
            start += 1;
        }

        for (int i = start; i < Handler.getTokens().size(); i++) {
            if (i >= start + 6) {
                break;
            }
            Token t = Handler.getTokens().get(i);
            if (t instanceof Character c && !(c instanceof Dummy)) {

                if (c.isRed()) g.setColor(new Color(124, 31, 31));
                else g.setColor(new Color(4, 107, 148));

                g.fillRect(x, y, SIZE, SIZE);
                c.getIcon().copy(64, 64).render(g, x + 4, y + 4);

                y += SIZE + 20;
            }
        }

        down.render(g, x, y);
        if (mouse.clickedOn(new Rectangle(x, y, 100, 32)) && start > 0) {
            start -= 1;
        }
    }

    public boolean renderPrepPhase(Graphics2D g, Mouse mouse){

        int x = 850, y = 100;

        up.render(g, 850, 50);

        ArrayList<Character> roleList = Database.characters;

        if(mouse.clickedOn(new Rectangle(x, 50, 100, 32)) && start+6 < roleList.size()){
            start += 1;
        }

        for(int i = start; i < roleList.size(); i++){
            if(i >= start+6) {
                break;
            }
            Token t = Database.characters.get(i);
            if(t instanceof Character c && !(c instanceof Dummy)){

                if (c.isRed()) g.setColor( new Color(124, 31, 31));
                else g.setColor( new Color(4, 107, 148));

                g.fillRect(x, y, SIZE, SIZE);
                c.getIcon().copy(64, 64).render(g, x+4, y+4);

                if(mouse.clickedOn(new Rectangle(x, y, SIZE, SIZE))){
                    selected = c.copy();
                    System.out.println(selected);
                }

                y+=SIZE+20;
            }
        }

        down.render(g, x, y);
        if(mouse.clickedOn(new Rectangle(x, y, 100, 32)) && start > 0){
            start -= 1;
        }

        if(selected != null){
            g.setColor( new Color(72, 255, 0, 178));
            int startingSquare = 14;
            Point p = Map.getCoordinate(0, startingSquare);
            Rectangle deployZone = new Rectangle(p.x, p.y, Game.MAP_SIZE*Tile.SIZE, (Game.MAP_SIZE - startingSquare)*Tile.SIZE);
            g.fillRect(deployZone.x, deployZone.y, deployZone.width, deployZone.height);

            if(mouse.clickedOn(deployZone)){
                Tile t = Map.getTile(mouse.getPosition().x, mouse.getPosition().y);
                if(t.isEmpty()) {
                    selected.setPos(t.point.x, t.point.y);
                    Handler.add(selected);
                    selected = null;
                }
                else{
                    System.out.println("Cant Place here");
                }
            }

        }

        for(Token t: Handler.getTokensToAdd()) t.render(g);
        startButton.render(g, 850, 850);
        return mouse.clickedOn(new Rectangle(850, 850, 100, 32));
    }

}
