import processing.core.PApplet;

public class Block extends Obstacle
{
    private boolean[] limitStroke;

    public Block(PApplet applet_, float x_, float y_, int w, int h) {
        super(applet_, x_, y_, w, h);
        isAlwaysFatal = false;
        isHarmless(false);
        limitStroke = new boolean[4];
        limitStroke[0] = true;
        limitStroke[1] = true;
        limitStroke[2] = true;
        limitStroke[3] = true;
    }

    public Block(PApplet applet_, float x_, float y_, int w, int h, boolean isSafe) {
        this(applet_, x_, y_, w, h);
        isAlwaysFatal = !isSafe;
        isHarmless(isSafe);
    }

    public Block(PApplet applet_, float x_, float y_, int w, int h, int r) {
        this(applet_, x_, y_, w, h);
        while (r >= 360) {
            r -= 360;
        }
        while (r < 0) {
            r += 360;
        }
        setRotation(r % 180 == 0? r : 0);
        // Currently limits rotation to 0 or 180
    }

    public Block(PApplet applet_, float x_, float y_, int w, int h, int r, boolean s) {
        this(applet_, x_, y_, w, h, r);
        isSoft = s;
    }

    /*
    @Override public boolean collided(Icon p) {
        switch (getRotation()) {
            case 0:
                return getX() + getWidth() > p.getX() && getX() < p.getX() + p.getWidth()
                        && getY() + getHeight() > p.getY() && getY() < p.getY() + p.getHeight();
            case 30:
            case 45:
            case 60:
                return false;
            default:
                return true;
        }
    }
     */

    @Override public boolean collided(Icon p) {
        boolean collided = false;
        if (!isBuffered()) {
            switch (getRotation()) {
                case 0:
                    if (getX() + getWidth() > p.getX() && getX() < p.getX() + p.getWidth()
                            && getY() + getHeight() > p.getY() && getY() < p.getY() + p.getHeight()) {
                        collided = true;
                    }
                    break;
                case 30:
                case 45:
                case 60:
                    collided = true;
                    break;
            }
            if (isHarmless()) {
                closeBuffer();
            }
        }
        if (isSoft && collided || isBuffered()) {
            setBuffers(getTrueY() + p.getHeight() / 4, getHeight() + p.getHeight() / 4);
            switch (getRotation()) {
                case 0:
                    return getX() + getWidth() > p.getX() && getX() < p.getX() + p.getWidth()
                            && getBufferedY() + getBufferedHeight() > p.getY() && getBufferedY() < p.getY() + p.getHeight();
                case 30:
                case 45:
                case 60:
                    return false;
            }
        }
        return collided;
    }

    @Override public Obstacle copy() {
        Block copy = new Block(applet, getX(), getY(), getWidth(), getHeight(), getRotation(), isSoft);
        copy.isAlwaysFatal = isAlwaysFatal;
        copy.isHarmless(isHarmless());
        copy.setColor(r, g, b);
        copy.limitStroke(limitStroke[0], limitStroke[1], limitStroke[2], limitStroke[3]);
        return copy;
    }

    @Override public void draw() {
        applet.translate(getX() + getWidth() / 2, getTrueY() + getHeight() / 2);
        applet.rotate(getRotation() * (float)Math.PI / 180);
        applet.fill(r, g, b);
        applet.noStroke();
        applet.rect(-getWidth() / 2, -getHeight() / 2, getWidth(), getHeight());
        applet.stroke(stroke);
        applet.strokeWeight(strokeWeight);
        if (limitStroke[0]) applet.line(-getWidth() / 2, -getHeight() / 2, getWidth() / 2, -getHeight() / 2);
        if (limitStroke[1]) applet.line(getWidth() / 2, -getHeight() / 2, getWidth() / 2, getHeight() / 2);
        if (limitStroke[2]) applet.line(getWidth() / 2, getHeight() / 2, -getWidth() / 2, getHeight() / 2);
        if (limitStroke[3]) applet.line(-getWidth() / 2, getHeight() / 2, -getWidth() / 2, -getHeight() / 2);
        applet.rotate(-getRotation() * (float)Math.PI / 180);
        applet.translate(-(getX() + getWidth() / 2), -(getTrueY() + getHeight() / 2));
    }

    public void limitStroke(boolean north, boolean east, boolean south, boolean west) {
        limitStroke[0] = north;
        limitStroke[1] = east;
        limitStroke[2] = south;
        limitStroke[3] = west;
    }
}
