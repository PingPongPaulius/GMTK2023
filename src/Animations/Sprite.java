package Animations;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*
Class that loads image from a resource root.
 */
public class Sprite {

    BufferedImage image;
    private int w, h;
    private int overlayR = 0;
    private int overlayG = 0;

    private String name;
    public Sprite(String name, int w, int h){
        this.image = loadImage(name);
        this.w = w;
        this.h = h;
        this.name = name;
    }

    public Sprite(String name){
        this.image = loadImage(name);
        this.w = 16;
        this.h = 16;
        this.name= name;
    }

    public Sprite(BufferedImage image){
        this.image = image;
    }

    public Sprite copy(int x, int y){
        return new Sprite(name, x, y);
    }

    public void setSize(int w, int h){
        this.w = w;
        this.h = h;
    }

    public void render(Graphics2D g, double x, double y){

        g.drawImage(image, (int) x, (int) y, w, h, null);
        if(overlayR-- > 0){
            g.setColor(new Color(157, 26, 26, 152));
            g.fillRect((int)x, (int)y, w, h);
        }
        if(overlayG-- > 0){
            g.setColor(new Color(30, 157, 26, 152));
            g.fillRect((int)x, (int)y, w, h);
        }
    }

    public static BufferedImage loadImage(String name){
        String path = "/" + name + ".png";
        try {
            return ImageIO.read(Objects.requireNonNull(Sprite.class.getResource(path)));
        }
        catch (IOException e){
            System.out.println("File Not found: " + path);
        }
        return null;
    }

    public static ArrayList<Sprite> loadSheet(String name, int oneSpriteWidth){

        var output = new ArrayList<Sprite>();
        BufferedImage sheet = loadImage(name);
        int curr = 0;
        assert sheet != null;
        while(curr < sheet.getWidth()){

            Sprite sprite = new Sprite(sheet.getSubimage(curr, 0, oneSpriteWidth, sheet.getHeight()));
            output.add(sprite);

            curr += oneSpriteWidth;
        }

        return output;
    }

    public void overlay(boolean R){
        if(R)
            this.overlayR = 10;
        else
            this.overlayG = 10;
    }

}

