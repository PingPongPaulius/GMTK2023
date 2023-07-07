package Animations;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimationHandler {

    private HashMap<String, ArrayList<Sprite>> animations;
    private String current;
    public int frame, counter, speed;
    public AnimationHandler(){
        animations = new HashMap<>();
        this.current = "";
        this.frame = 0;
        this.counter = 0;
        this.speed = 60;
    }

    public void loadSheet(String name, String spriteSheet, int oneSpriteWidth){
        if(animations.isEmpty()) this.current = name;
        animations.put(name, Sprite.loadSheet(spriteSheet, oneSpriteWidth));
    }

    public void select(String title) {
        this.current = title;
    }

    public void render(Graphics2D g, int x, int y){

        ArrayList<Sprite> spriteSheet = animations.get(current);
        if(frame >= spriteSheet.size()){
            frame = 0;
        }

        spriteSheet.get(frame).render(g, x, y);

        if(counter++>speed){
            frame++;
            counter = 0;
        }

    }

    public void setSize(String name, int w, int h){
        for(Sprite s: animations.get(name)) s.setSize(w, h);
    }

}
