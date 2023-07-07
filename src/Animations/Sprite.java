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
    public Sprite(String name, int w, int h){
        this.image = loadImage(name);
        this.w = w;
        this.h = h;
    }

    public Sprite(String name){
        this.image = loadImage(name);
        this.w = 16;
        this.h = 16;
    }

    public Sprite(BufferedImage image){
        this.image = image;
    }

    public void setSize(int w, int h){
        this.w = w;
        this.h = h;
    }

    public void render(Graphics2D g, double x, double y){
        g.drawImage(image, (int) x, (int) y, w, h, null);
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
}

