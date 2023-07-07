package Engine;

public class DeltaTime {

    private static long deltaTimeMills, lastTime;
    public static final long FPS = 60L;

    public DeltaTime(){
        deltaTimeMills = 0;
        lastTime = System.currentTimeMillis();
    }

    public static float get(){
        return FPS*(float)deltaTimeMills/1000;
    }

    public static void reset(){
        deltaTimeMills = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
    }

}

