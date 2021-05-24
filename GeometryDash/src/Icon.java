import processing.core.PApplet;

public class Icon extends Unit
{
    protected boolean isFalling;

    // For shifting the window to keep the player in view
    // min is up, max is down
    protected float minHeight;
    protected float maxHeight;
    protected float preferredMinHeight;
    protected float preferredMaxHeight;

    protected int r2;
    protected int g2;
    protected int b2;

    public Icon(PApplet applet_, float x_, float y_, int w, int h) {
        applet = applet_;
        setX(x_);
        setY(y_);
        setXSpeed(applet.width / 100F);
        setYSpeed(0);
        setWidth(w);
        setHeight(h);
        r = 0;
        g = 255;
        b = 0;
        r2 = 0;
        g2 = 150;
        b2 = 255;
        stroke = 0;
        strokeWeight = 2;
    }

    public boolean collidedWith(Obstacle o) {
        if (getX() > o.getX() + o.getWidth() && getX() + getWidth() < o.getX()) {
            if (getY() > o.getY() + o.getHeight() && getY() + getHeight() < o.getY()) {
                return true;
            }
        }
        return false;
    }

    public void jump() {}

    public void move(Obstacle[] obstacles, Player p) {
        jump();

        // For animation at the beginning of the first attempt
        if (getX() < applet.width / 3) {
            moveX(getXSpeed());
        } else {
            for (Obstacle o : obstacles) {
                o.moveX(-getXSpeed());
            }
        }
        moveY(getYSpeed());
    }
}
