package Engine;

import Databases.Database;
import Environment.Map;
import IO.Keyboard;
import IO.Mouse;
import Tokens.*;

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

    public Game(int width, int height) {

        this.handler = new Handler();
        this.console = new Console();
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();

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

        new DeltaTime();
        new Database();

        map = new Map();

        Handler.add(new Assassin(20, 20, true));
        Handler.add(new Healer(21, 20, true));
        Handler.add(new Tank(15, 15, true));
        Handler.add(new Archer(1, 0, false));
        Handler.add(new Wizard(0 ,0, false));
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
            handler.loop((Graphics2D) graphics);
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

