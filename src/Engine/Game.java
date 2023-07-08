package Engine;

import Animations.HUD;
import Animations.Sprite;
import Databases.Database;
import Environment.Map;
import IO.Keyboard;
import IO.Mouse;
import Tokens.*;
import Tokens.Character;

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

    Handler handler;

    public Map map;
    public Console console;
    public HUD hud;

    private boolean started;
    private int currLevel;

    public Game(int width, int height) {

        this.handler = new Handler();
        this.console = new Console();
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
        this.hud = new HUD();
        SIZE.setSize(width, height);

        renderWindow = new Canvas();
        renderWindow.setSize(SIZE);

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

        currLevel = 1;
        loadLevel();
    }

    private void loadLevel(){
        Handler.clear();

        if(currLevel == 1){
            Handler.add(new Archer(15, 0, false));
            Handler.add(new Tank(15, 1, false));
            Handler.add(new Assassin(0, 10, false));
            Handler.add(new Assassin(24, 10, false));
        }
        if(currLevel == 2){
            Handler.add(new Archer(3, 0, false));
            Handler.add(new Archer(9, 0, false));
            Handler.add(new Archer(20, 0, false));
            Handler.add(new Wizard(13, 5, false));
            Handler.add(new Healer(4, 0, false));
            Handler.add(new Healer(13, 4, false));
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
            map.render((Graphics2D) graphics);
            Console.render((Graphics2D) graphics);
            if(started) {
                this.hud.render((Graphics2D) graphics, mouse);
                handler.loop((Graphics2D) graphics);

                int numReds=0, numBlues=0;
                for(Token t: Handler.getTokens()){
                    if(t instanceof Character c){
                        if(c.isRed()) numReds++;
                        else numBlues ++;
                    }
                }

                if(numReds == 0){
                    Sprite s = new Sprite("LoseScreen", 100, 100);
                    s.render((Graphics2D) graphics, 400, 400);

                }
                if(numBlues == 0){
                    loadLevel();
                    started = false;
                }

            }
            else{
                started = hud.renderPrepPhase((Graphics2D) graphics, mouse);
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

