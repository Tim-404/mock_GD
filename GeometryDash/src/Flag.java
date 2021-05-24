import processing.core.PApplet;

public class Flag extends Modifier
{
    private Level level;
    private int targetR;
    private int targetG;
    private int targetB;
    private int duration;
    private String type;

    public Flag(PApplet applet_, float x_, float y_, int h, Level l, int r, int g, int b, int time, String type_) {
        super(applet_, x_, y_, 0, h);
        level = l;
        targetR = r;
        targetG = g;
        targetB = b;
        duration = time;
        type = type_;
    }

    @Override public boolean activate(Player p) {
        if (!isActivated()) {
            level.transitionBackground(targetR, targetG, targetB, duration, type);
            isActivated(true);
            return true;
        }
        return false;
    }

    @Override public Obstacle copy() { return new Flag(applet, getX(), getY(), getHeight(), level, targetR, targetG, targetB, duration, type); }
}
