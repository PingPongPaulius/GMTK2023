import Engine.Game;

public class Main {
    public static void main(String[] args) {
        Runnable game = new Game(1000, 1000);
        Thread t = new Thread(game);
        t.start();
    }

}