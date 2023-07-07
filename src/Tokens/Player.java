package Tokens;

import Animations.AnimationHandler;

import java.awt.*;

public class Player extends Token{

    AnimationHandler animations;

    public Player(){
        this.animations = new AnimationHandler();
        this.animations.loadSheet("TestAnimations", "TestAnimation", 16);
        this.animations.setSize("TestAnimations", 100, 100);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        animations.render(g, 400, 400);
    }
}
