package Engine;

import Animations.HUD;
import Animations.Sprite;
import Databases.Database;
import Environment.Map;
import IO.Keyboard;
import IO.Mouse;
import Tokens.*;
import Tokens.Character;
import com.sun.source.tree.WhileLoopTree;
import jdk.jshell.spi.SPIResolutionException;

import javax.net.ssl.SNIHostName;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JFrame implements Runnable {

    public static final Dimension SIZE = new Dimension();
    private final Canvas renderWindow;
    private Graphics graphics;

    public static final Random RANDOM = new Random();
    public static final int MAP_SIZE = 25;

    private Keyboard keyboard;
    private Mouse mouse;

    private Sprite goldCoin;

    Handler handler;

    public Map map;
    public Console console;
    public HUD hud;

    private boolean started;
    private int currLevel;

    public static int score;

    //private MusicPlayer musicPlayer;

    public Game(int width, int height) {

        this.handler = new Handler();
        this.console = new Console();
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
        this.hud = new HUD();
        //this.musicPlayer = new MusicPlayer("Based Legitness");
        SIZE.setSize(width, height);

        renderWindow = new Canvas();
        renderWindow.setSize(SIZE);
        this.goldCoin = new Sprite("GoldCoin", 32, 32);
        this.setTitle("Role Switch");
        this.setSize(SIZE);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(renderWindow);
        this.addKeyListener(keyboard);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.renderWindow.createBufferStrategy(3);
        this.graphics = renderWindow.getBufferStrategy().getDrawGraphics();

        this.renderWindow.addMouseListener(mouse);
        this.renderWindow.addMouseMotionListener(mouse);
        this.renderWindow.addKeyListener(keyboard);

        map = new Map();

        new DeltaTime();
        new Database();

        this.started = false;

        currLevel = 0;
        loadLevel();
    }

    private void loadLevel(){
        Handler.clear();

        if(currLevel == 0){
            Handler.add(new Dummy(0, 0, false));
        }
        if(currLevel == 1){
            Handler.add(new HillBilly(13, 0, false));
            Handler.add(new HillBilly(0, 13, false));
        }
        if(currLevel == 2){
            //musicPlayer.play();
            Handler.add(new Archer(15, 0, false));
            Handler.add(new Tank(15, 1, false));
            Handler.add(new Assassin(0, 10, false));
            Handler.add(new Assassin(24, 10, false));
        }
        if(currLevel == 3){
            Handler.add(new Archer(3, 0, false));
            Handler.add(new Archer(9, 0, false));
            Handler.add(new Archer(20, 0, false));
            Handler.add(new Wizard(13, 5, false));
            Handler.add(new Healer(4, 0, false));
            Handler.add(new Healer(13, 4, false));
        }
        if(currLevel == 4){
            Handler.add(new Archer(2, 1, false));
            Handler.add(new Healer(1, 1, false));
            Handler.add(new Healer(3, 1, false));
            Handler.add(new Healer(2, 0, false));
            Handler.add(new BodyGuard(5, 5, false));
            Handler.add(new BodyGuard(6, 5, false));
            Handler.add(new Archer(2+15, 1, false));
            Handler.add(new Healer(1+15, 1, false));
            Handler.add(new Healer(3+15, 1, false));
            Handler.add(new Healer(2+15, 0, false));
            Handler.add(new BodyGuard(5+15, 5, false));
            Handler.add(new BodyGuard(6+15, 5, false));
            Handler.add(new Speedo(7+15, 5, false));
            Handler.add(new Speedo(7+15, 5, false));
        }
        if(currLevel == 5){
            Handler.add(new Lawyer(15, 20, false));
            Handler.add(new Speedo(12, 20, false));
            Handler.add(new Speedo(1, 20, false));
            Handler.add(new Speedo(24, 20, false));
            Handler.add(new Tank(10, 13, false));
            Handler.add(new Tank(15, 13, false));
            Handler.add(new Healer(10, 12, false));
            Handler.add(new Healer(15, 12, false));
            Handler.add(new Archer(0, 0, false));
            Handler.add(new Archer(24, 0, false));
        }
        if(currLevel == 6){
            for(int i = 0; i< MAP_SIZE-3; i+=3){
                Handler.add(new Healer(i, 0, false));
                Handler.add(new Healer(i+1, 0, false));
                Handler.add(new Healer(i+2, 0, false));
                Handler.add(new Healer(i+2, 1, false));
                Handler.add(new Healer(i, 1, false));
                Handler.add(new Healer(i, 2, false));
                Handler.add(new Healer(i+2, 2, false));
                Handler.add(new Healer(i+1, 2, false));
                Handler.add(new Archer(i+1, 1, false));
            }
            Handler.add(new BodyGuard(13, 13, false));
            Handler.add(new Lawyer(13, 12, false));
            Handler.add(new BodyGuard(14, 13, false));
            Handler.add(new Lawyer(14, 12, false));
            Handler.add(new BodyGuard(12, 13, false));
            Handler.add(new Lawyer(12, 12, false));
            Handler.add(new BodyGuard(22, 13, false));
            Handler.add(new Lawyer(22, 12, false));
        }
        if(currLevel == 7){
            for(int i = 0; i < MAP_SIZE; i++){
                if(i%2==0){
                    Handler.add(new Archer(i, 0, false));
                    Handler.add(new Tank(i, 13, false));
                }
                else{
                    Handler.add(new Wizard(i, 0, false));
                    Handler.add(new Speedo(i, 13, false));
                }
            }
        }
        if(currLevel == 8){
            for(int i = 0; i < MAP_SIZE; i++){
                if(i%2==0){
                    Handler.add(new Archer(i, 0, false));
                    Handler.add(new Tank(i, 13, false));
                }
                else{
                    Handler.add(new Wizard(i, 0, false));
                    Handler.add(new Speedo(i, 12, false));
                }
                Handler.add(new Lawyer(i, 13, false));
            }
        }
        if(currLevel == 9){
            for(int i = 0; i < MAP_SIZE; i++){
                if(i%2==0){
                    Handler.add(new Archer(i, 0, false));
                    Handler.add(new Tank(i, 13, false));
                }
                else{
                    Handler.add(new Wizard(i, 0, false));
                    Handler.add(new Speedo(i, 12, false));
                }
                Handler.add(new Lawyer(i, 13, false));
            }
            Handler.add(new HillBilly(15, 23, false));
            Handler.add(new HillBilly(10, 23, false));
            Handler.add(new HillBilly(1, 23, false));
            Handler.add(new HillBilly(24, 23, false));
        }
        if(currLevel == 10){
            for(int i = 0; i < MAP_SIZE; i++) {
                Handler.add(new Healer(i, 0, false));
                Handler.add(new Archer(i, 1, false));
                Handler.add(new Wizard(i, 2, false));
                Handler.add(new Assassin(i, 3, false));
                Handler.add(new Tank(i, 4, false));
                Handler.add(new BodyGuard(i, 5, false));
                Handler.add(new Lawyer(i, 6, false));
                Handler.add(new Assassin(i, 7, false));
                Handler.add(new Assassin(i, 8, false));
                Handler.add(new Speedo(i, 9, false));
                Handler.add(new Wizard(i, 10, false));
                Handler.add(new Tank(i, 11, false));
                Handler.add(new Healer(i, 12, false));
                Handler.add(new Lawyer(i, 13, false));
            }
            Handler.add(new HillBilly(20, 20, false));
            Handler.add(new Lawyer(24, 24, false));
            Handler.add(new GoofyDevil(12, 20, false));
        }

        ++currLevel;
    }

    @Override
    public void run() {
        // Capping to 60 Frames Per Second

        long fps = DeltaTime.FPS;
        float oneFrameEveryMilliseconds = ((float) 1 / fps) * 1000;
        int counter = 0;
        while (true) {
            DeltaTime.reset();

            //----------------------------------------------------------
            // Remembering Starting of the loop time.
            //----------------------------------------------------------
            long startingTimeMill = System.currentTimeMillis();
            //----------------------------------------------------------
            // Drawing
            //----------------------------------------------------------



            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, SIZE.width, SIZE.height);

            this.goldCoin.render((Graphics2D) graphics, 10, 10);
            graphics.setColor(Color.WHITE);
            graphics.drawString(""+score, 50, 30);

            if(currLevel == 12){
                Sprite sprite = new Sprite("Win", 200, 64);
                sprite.render((Graphics2D) graphics, 400, 500);
                graphics.setColor(Color.WHITE);
                graphics.drawString("Your Score: " + score, 450, 650);
            }
            else {

                map.render((Graphics2D) graphics);
                Console.render((Graphics2D) graphics);
                if (started) {

                    handler.loop((Graphics2D) graphics);
                    this.hud.render((Graphics2D) graphics, mouse);

                    int numReds = 0, numBlues = 0;
                    for (Token t : Handler.getTokens()) {
                        if (t instanceof Character c) {
                            if (c.isRed()) numReds++;
                            else numBlues++;
                        }
                    }

                    if (numReds == 0) {
                        Sprite s = new Sprite("LoseScreen", 100, 100);
                        s.render((Graphics2D) graphics, 400, 400);

                    }
                    if (numBlues == 0) {
                        loadLevel();
                        started = false;
                    }

                    if (currLevel == 1) {
                        graphics.setColor(new Color(0, 0, 0));
                        graphics.fillRect(650, 150, 170, 20);
                        graphics.setColor(new Color(255, 255, 255));
                        graphics.drawString("Click on hero to reverse roles ->", 655, 165);
                    }

                } else {
                    if (currLevel == 1) {
                        graphics.setColor(new Color(0, 0, 0));
                        graphics.fillRect(60, 60, 200, 20);
                        graphics.setColor(new Color(255, 255, 255));
                        graphics.drawString("<- Enemy has background blue.", 60, 75);
                        graphics.drawString("<- Goal is to try and minimise the total cost of the army", 60, 30);
                        graphics.drawString("This round the troops are on me ;)", 60, 45);
                        graphics.drawString("Chose your fighter by clicking on them.", 750, 20);
                    }
                    started = hud.renderPrepPhase((Graphics2D) graphics, mouse, currLevel);
                }

            }


            this.renderWindow.getBufferStrategy().show();
            //----------------------------------------------------------
            // Handling 60 FPS loop
            //----------------------------------------------------------
            // Remembering ending of the loop time.
            long endingTimeMill = System.currentTimeMillis();
            long loopTookInMilliseconds = endingTimeMill - startingTimeMill;
            // Should wait time before the next loop (I want to render every 16.66667 seconds but loop takes some time thus subtract).
            long sleepTimeInMilliseconds = (long) (oneFrameEveryMilliseconds - loopTookInMilliseconds);

            if (sleepTimeInMilliseconds > 0) {
                try {
                    Thread.sleep(sleepTimeInMilliseconds);
                } catch (InterruptedException e) {
                    System.exit(0);
                }
            }
            counter = handleFPS(counter);
        }
    }

    public int handleFPS(int counter){
        counter += 1;
        if(counter == 60) {
            //System.out.println("NOW");
            counter = 0;
        }
        return counter;
    }

}

