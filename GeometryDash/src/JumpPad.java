import processing.core.PApplet;

public class JumpPad extends Modifier
{
    private int strength;
    private float launchPower;

    public JumpPad(PApplet applet_, float x_, float y_, int w, int h) {
        this(applet_, x_, y_, w, h, 2);
    }

    public JumpPad(PApplet applet_, float x_, float y_, int w, int h, int power) {
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
        requiresInput = false;
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
        return new JumpPad(applet, getX(), getY(), getWidth(), getHeight(), strength);
    }

    @Override public void draw() {
        applet.translate(getX() + getWidth() / 2, getY() + getHeight() / 2);
        applet.rotate(getRotation() * (float)Math.PI / 180);
        applet.fill(r, g, b);
        applet.stroke(stroke);
        applet.strokeWeight(strokeWeight);
        applet.arc(0, getHeight() / 2, getWidth(), getHeight() * 2, applet.PI, applet.TWO_PI, applet.CHORD);
        applet.rotate(-getRotation() * (float)Math.PI / 180);
        applet.translate(-(getX() + getWidth() / 2), -(getY() + getHeight() / 2));
    }
}
