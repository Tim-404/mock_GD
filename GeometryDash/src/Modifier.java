import processing.core.PApplet;

public class Modifier extends Obstacle  // Subset of Obstacle that will never harm the player
{
    private boolean isActivated;
    public boolean requiresInput;

    public Modifier(PApplet applet_, float x_, float y_, int w, int h) {
        super(applet_, x_, y_, w, h);
        isAlwaysFatal = false;
        isHarmless(true);
        isActivated = false;
    }

    public boolean activate(Player p) { return false; }

    @Override public boolean collided(Icon p) {
        return getX() + getWidth() > p.getX() && getX() < p.getX() + p.getWidth()
                && getY() + getHeight() > p.getY() && getY() < p.getY() + p.getHeight();
    }

    public boolean isActivated() { return isActivated; }

    public void isActivated(boolean a) { isActivated = a; }
}
