package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private final boolean[] inputs;
    public static final int[] KEYS = new int[]{KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_SPACE, KeyEvent.VK_ENTER};

    public Keyboard() {
        this.inputs = new boolean[KEYS.length];
        for (int i = 0; i < inputs.length; i++) this.inputs[i] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0; i < KEYS.length; i++) {
            if (KEYS[i] == e.getKeyCode()) {
                this.inputs[i] = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (int i = 0; i < KEYS.length; i++) {
            if (KEYS[i] == e.getKeyCode()) {
                this.inputs[i] = false;
            }
        }
    }

    // This is a more user based API approach, it is slow.
    public boolean isKeyPressedAPI(int keycode) {
        for (int i = 0; i < KEYS.length; i++) {
            if (KEYS[i] == keycode) return inputs[i];
        }
        return false;
    }

    // This requires memorising KEYS array, very quick.
    public boolean isKeyPressed(int index) {
        return inputs[index];
    }
    public void setPressedToFalse(int index){
        this.inputs[index] = false;
    }
}

