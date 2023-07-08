package IO;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

public class Mouse implements MouseListener, MouseMotionListener {

    private Optional<Point> pos;
    private Point relativePos;
    public boolean clicked;

    public Mouse() {
        this.pos = Optional.empty();
        this.clicked = false;
        relativePos = new Point(0, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.pos = Optional.of(new Point(e.getX(), e.getY()));
        this.clicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.clicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        relativePos  = new Point(e.getX(), e.getY());
    }

    public Optional<Point> getClickedPos() {
        return pos;
    }

    public boolean clickedOn(Rectangle hitbox){
        Rectangle rectangle = new Rectangle((int)getPosition().x, (int)getPosition().y, 1, 1);
        boolean out = hitbox.intersects(rectangle) && clicked;
        if (out) clicked = false;
        return out;
    }

    public Point getPosition(){
        return relativePos;
    }
}

