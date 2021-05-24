import processing.core.PApplet;

public class Spike extends Obstacle
{
    public Spike(PApplet applet_, float x_, float y_, int w, int h) {
        super(applet_, x_, y_, w, h);
        isAlwaysFatal = true;
        isHarmless(false);
    }

    public Spike(PApplet applet_, float x_, float y_, int w, int h, int r) {
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

    public Spike(PApplet applet_, float x_, float y_, int w, int h, int r, boolean s) {
        this(applet_, x_, y_, w, h, r);
        isSoft = s;
    }

    @Override public boolean collided(Icon p) {
        float pY;
        int pHeight;
        if (isSoft) {   // reduces player height by half for collision checking
            pY = p.getY() + p.getHeight() / 4F;
            pHeight = p.getHeight() / 2;
        } else {
            pY = p.getY();
            pHeight = p.getHeight();
        }
        switch (getRotation()) {
            case 0:
                if (getX() < p.getX() + p.getWidth() && getX() + getWidth() / 2 > p.getX() + p.getWidth()
                        && getY() + getHeight() - (p.getX() + p.getWidth() - getX()) * 2 * getHeight() / getWidth() < pY + pHeight
                        && getY() + getHeight() > pY) {
                    return true;
                } else if (getX() + getWidth() / 2 <= p.getX() + p.getWidth() && getX() + getWidth() / 2 >= p.getX()
                        && getY() < pY + pHeight && getY() + getHeight() > pY) {
                    return true;
                } else if (getX() + getWidth() / 2 < p.getX() && getX() + getWidth() > p.getX()
                        && getY() + (p.getX() - getX() - getWidth() / 2) * 2 * getHeight() / getWidth() < pY + pHeight
                        && getY() + getHeight() > pY) {
                    return true;
                }
                return false;
            case 30:
            case 45:
            case 60:
            case 90:
            case 120:
            case 135:
            case 150:
                return false;
            case 180:
                if (getX() < p.getX() + p.getWidth() && getX() + getWidth() / 2 > p.getX() + p.getWidth()
                        && getY() + (p.getX() + p.getWidth() - getX()) * 2 * getHeight() / getWidth() > pY
                        && getY() < pY + pHeight) {
                    return true;
                } else if (getX() + getWidth() / 2 <= p.getX() + p.getWidth() && getX() + getWidth() / 2 > p.getX()
                        && getY() < pY + pHeight && getY() + getHeight() > pY) {
                    return true;
                } else if (getX() + getWidth() / 2 < p.getX() && getX() + getWidth() > p.getX()
                        && getY() + (getX() + getWidth() - p.getX()) * 2 * getHeight() / getWidth() > pY
                        && getY() < pY + pHeight) {
                    return true;
                }
                return false;
            case 210:
            case 225:
            case 240:
            case 270:
            case 300:
            case 315:
            case 330:
                return false;
            default:
                return true;
        }
    }

    @Override public Obstacle copy() {
        Spike copy = new Spike(applet, getX(), getY(), getWidth(), getHeight(), getRotation(), isSoft);
        copy.setColor(r, g, b);
        return copy;
    }

    @Override public void draw() {
        applet.translate(getX() + getWidth() / 2, getY() + getHeight() / 2);
        applet.rotate(getRotation() * (float)Math.PI / 180);
        applet.fill(r, g, b);
        applet.stroke(stroke);
        applet.strokeWeight(strokeWeight);
        applet.triangle(-getWidth() / 2, getHeight() / 2, 0, -getHeight() / 2, getWidth() / 2, getHeight() / 2);
        applet.rotate(-getRotation() * (float)Math.PI / 180);
        applet.translate(-(getX() + getWidth() / 2), -(getY() + getHeight() / 2));
    }
}
