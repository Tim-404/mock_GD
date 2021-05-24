import processing.core.PApplet;

public class Ship extends Icon
{
    private static float thrust = 1;
    private static float maxYSpeed = 25;
    private int shiftCounter;
    private int shiftSpeed;

    public Ship(PApplet applet_, float x_, float y_, int w) {
        super(applet_, x_, y_, w, w);

        minHeight = 0;
        maxHeight = applet.height - getHeight();
    }

    @Override public void draw() {
        applet.fill(0, 0, 0, 0);
        applet.stroke(255);
        applet.rect(getX(), getY(), getWidth(), getHeight());

        applet.translate(getX() + getWidth() / 4, getY() + getHeight() / 2);
        applet.rotate(getRotation() * (float)Math.PI / 180);
        applet.fill(r, g, b);
        applet.stroke(stroke);
        applet.rect(-getWidth() / 12, -getHeight() / 2, getWidth() / 2, getHeight() / 2);
        applet.rect(0, 0, getWidth() / 2, getHeight() / 2);
        applet.rect(-getWidth() / 2, getHeight() / 16, getWidth() / 8, getHeight() * 3 / 8);
        applet.beginShape();
        applet.vertex(0, getHeight() / 16);
        applet.vertex(0, getHeight() * 7 / 16);
        applet.vertex(-getWidth() * 3 / 8, getHeight() * 9 / 16);
        applet.vertex(-getWidth() * 3 / 8, -getHeight() / 16);
        applet.endShape(applet.CLOSE);
        applet.beginShape();
        applet.vertex(getWidth() / 2, 0);
        applet.vertex(getWidth() / 2, getHeight() / 2);
        applet.vertex(getWidth() * 9 / 10, getHeight() * 3 / 8);
        applet.vertex(getWidth() * 9 / 10, getHeight() / 8);
        applet.endShape(applet.CLOSE);
        applet.fill(r2, g2, b2);
        applet.beginShape();
        applet.vertex(-getWidth() / 6, getHeight() / 8);
        applet.vertex(-getWidth() / 6, -getHeight() * 5 / 16);
        applet.vertex(-getWidth() / 12, -getHeight() * 5 / 16);
        applet.vertex(-getWidth() / 12, -getHeight() / 16);
        applet.vertex(getWidth() * 3 / 4, 0);
        applet.vertex(getWidth() * 3 / 4, getHeight() / 8);
        applet.endShape(applet.CLOSE);
        applet.rotate(-getRotation() * (float)Math.PI / 180);
        applet.translate(-(getX() + getWidth() / 4), -(getY() + getHeight() / 2));
    }

    @Override public void jump() {
        if (applet.keyPressed || applet.mousePressed) {
            setYSpeed(Math.max(getYSpeed() - thrust, -maxYSpeed));
        } else {
            setYSpeed(Math.min(getYSpeed() + thrust, maxYSpeed));
        }
    }

    @Override public void move(Obstacle[] obstacles, Player p) {
        super.move(obstacles, p);

        // Shifts screen to keep the player in view
        if (getY() < minHeight || getY() > maxHeight) {
            shiftCounter = 5;
            shiftSpeed = getY() < minHeight? (int)maxYSpeed : -(int)maxYSpeed;
        }
        if (shiftCounter > 0) {
            moveY(shiftSpeed);
            for (Obstacle o : obstacles) {
                o.moveY(shiftSpeed);
            }
            shiftCounter--;
        }

        // Check for collisions, and whether or not to restrict movement
        for (Obstacle o : obstacles) {
            if (o.getX() + o.getWidth() >= getX() && o.getX() <= getX() + getWidth()) {
                if (!o.isAlwaysFatal) {
                    if (!o.collided(this)) {
                        o.isHarmless(true);
                        if (getY() + getHeight() == o.getY()) {
                            setYSpeed(Math.min(getYSpeed(), 0));
                        } else if (getY() == o.getY() + o.getHeight()) {
                            setYSpeed(Math.max(getYSpeed(), 0));
                        }
                    } else {
                        if (o instanceof Modifier && !((Modifier)o).requiresInput) {
                            ((Modifier)o).activate(p);
                        } else if (o.isHarmless()) {
                            if (getYSpeed() > 0) {
                                setY(o.getY() - getHeight());
                                setYSpeed(Math.min(getYSpeed(), 0));
                            } else {
                                setY(o.getY() + o.getHeight());
                                setYSpeed(Math.max(getYSpeed(), 0));
                            }
                        } else {
                            float assumedY = getY() + getHeight() - getYSpeed() / getXSpeed() * (getX() + getWidth() - o.getX());
                            if (assumedY < o.getY() || assumedY > o.getY() + o.getHeight()) {
                                o.isHarmless(true);
                                setYSpeed(Math.min(getYSpeed(), 0));
                                setY(o.getY() + (assumedY < o.getY() ? -getHeight() : o.getHeight()));
                            } else {
                                p.isAlive = false;
                            }
                        }
                    }
                } else if (o.collided(this)) {
                    p.isAlive = false;
                }
            }
        }
        setRotation((int)(Math.atan(getYSpeed() / getXSpeed()) * 180 / Math.PI));
    }

    public static void setMaxYSpeed(float s) {
        maxYSpeed = s;
    }

    public static void setThrust(float t) {
        thrust = t;
    }
}
