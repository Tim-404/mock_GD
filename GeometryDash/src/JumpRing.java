import processing.core.PApplet;

public class JumpRing extends Modifier
{
    private int strength;
    private float launchPower;

    public JumpRing(PApplet applet_, float x_, float y_, int w, int h) {
        this(applet_, x_, y_, w, h, 2);
    }

    public JumpRing(PApplet applet_, float x_, float y_, int w, int h, int power) {
        super(applet_, x_, y_, w, h);
        strength = Math.min(Math.max(power, 1), 4);
        switch (strength) {
            case 1:
                launchPower = applet.height / 24F;
                r = 200;
                b = 100;
                break;
            case 2:
                launchPower = applet.height / 18F;
                r = 255;
                g = 255;
                break;
            case 3:
                launchPower = applet.height / 12F;
                g = 255;
                break;
            default:
                b = 255;
                g = 255;
        }
        requiresInput = true;
    }

    @Override public boolean activate(Player p) {
        if (!isActivated()) {
            p.setYSpeed(-launchPower);
            isActivated(true);
            return true;
        }
        return false;
    }

    @Override public Obstacle copy() {
        return new JumpRing(applet, getX(), getY(), getWidth(), getHeight(), strength);
    }

    @Override public void draw() {
        applet.translate(getX(), getY());
        applet.rotate(getRotation());
        applet.fill(r, g, b);
        applet.stroke(stroke);
        applet.strokeWeight(strokeWeight);
        applet.ellipse(getX(), getY(), getWidth(), getHeight());
        applet.rotate(-getRotation());
        applet.translate(-getX(), -getY());
    }
}
