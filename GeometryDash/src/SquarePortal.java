import processing.core.PApplet;

public class SquarePortal extends Modifier
{
    public SquarePortal(PApplet applet_, float x_, float y_, int w, int h) {
        super(applet_, x_, y_, w, h);
        b = 255;
        requiresInput = false;
    }

    @Override public boolean activate(Player p) {
        if (!isActivated()) {
            p.swap(new Square(applet, p.getX(), p.getY(), applet.height / 10));
            isActivated(true);
            return true;
        }
        return false;
    }

    @Override public Obstacle copy() {
        return new SquarePortal(applet, getX(), getY(), getWidth(), getHeight());
    }

    @Override public void draw() {
        applet.translate(getX() + getWidth() / 2, getY() + getHeight() / 2);
        applet.rotate(getRotation() * (float)Math.PI / 180);
        applet.fill(r, g, b);
        applet.stroke(stroke);
        applet.strokeWeight(strokeWeight);
        applet.rect(-getWidth() / 2, -getHeight() / 2, getWidth(), getHeight());
        applet.rotate(-getRotation() * (float)Math.PI / 180);
        applet.translate(-(getX() + getWidth() / 2), -(getY() + getHeight() / 2));
    }
}
