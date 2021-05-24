import processing.core.PApplet;

public class Square extends Icon
{
    private static float jumpPower = 40;        // Defaults for
    private static float acceleration = 3.5F;      // game at 1000 x 1800
    private static float turnSpeed = 7;

    public Square(PApplet applet_, float x_, float y_, int w) {
        super(applet_, x_, y_, w, w);

        minHeight = applet.height / 8;
        maxHeight = applet.height * 7 / 8 - getHeight();
        preferredMinHeight = applet.height / 4;
        preferredMaxHeight = applet.height * 3 / 4 - getHeight();
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

    @Override public void jump() {
        if ((applet.keyPressed || applet.mousePressed) && !isFalling) {
            setYSpeed(-jumpPower);
        }
    }

    @Override public void move(Obstacle[] obstacles, Player p) {
        super.move(obstacles, p);

        // Shifts screen to keep the player in view
        if (getY() > preferredMaxHeight || getY() < preferredMinHeight) {
            float shift = getY() > applet.height / 2 ? -applet.height / 200F : applet.height / 200F;
            if (getY() > maxHeight || getY() < minHeight) {
                shift = getY() > applet.height / 2? -applet.height / 10F : applet.height / 10F;
            } else if (getY() + getYSpeed() > maxHeight || getY() + getYSpeed() < minHeight) {
                shift = -getYSpeed();
            }
            moveY(shift);
            for (Obstacle o : obstacles) {
                o.moveY(shift);
            }
        }

        // Check for collisions, including if the player is on a block or is dead
        isFalling = true;
        for (Obstacle o : obstacles) {
            if (o.getX() + o.getWidth() >= getX() && o.getX() <= getX() + getWidth()) {
                if (!o.isAlwaysFatal) {
                    if (o.getY() >= getY() + getHeight()) {
                        o.isHarmless(true);
                        if (o.getY() == getY() + getHeight()) {
                            setYSpeed(Math.min(getYSpeed(), 0));
                            isFalling = false;
                        }
                    } else if (o.collided(this)) {
                        if (o instanceof Modifier && !((Modifier)o).requiresInput) {
                            ((Modifier)o).activate(p);
                        } else if (o.isHarmless()) {
                            setY(o.getY() - getHeight());
                            setYSpeed(Math.min(getYSpeed(), 0));
                            isFalling = false;
                        } else if (getY() + getHeight() - getYSpeed() / getXSpeed() * (getX() + getWidth() - o.getX()) <= o.getY()) {
                            o.isHarmless(true);
                            setY(o.getY() - getHeight());
                            setYSpeed(Math.min(getYSpeed(), 0));
                            isFalling = false;
                        } else {
                            p.isAlive = false;
                        }
                    }
                } else if (o.collided(this)) {
                    p.isAlive = false;
                }
            }
        }
        if (isFalling) {
            setYSpeed(Math.min(getYSpeed() + acceleration, jumpPower));
            rotate(turnSpeed);
        } else if (getRotation() % 90 != 0) {
            if (getRotation() % 90 >= 45 && 90 - getRotation() % 90 > turnSpeed) {
                rotate(turnSpeed);
            } else if (getRotation() % 90 < 45 && getRotation() % 90 > turnSpeed) {
                rotate(-turnSpeed);
            } else {
                setRotation(Math.round(getRotation() / 90) * 90);
            }
        }
    }

    public static void setAcceleration(float a) {
        acceleration = a;
    }

    public static void setJumpPower(float p) {
        jumpPower = p;
    }
}
