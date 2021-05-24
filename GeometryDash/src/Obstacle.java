import processing.core.PApplet;

public class Obstacle extends Unit
{
    protected boolean isAlwaysFatal;    // Cannot be touched or else player dies
    private boolean isHarmless;
    protected boolean isSoft;   // Will give the player leeway in hitbox collisions
    private boolean isBuffered;
    private float bufferedY;
    private int bufferedHeight;
    private boolean bufferFinalized;

    public Obstacle(PApplet applet_, float x_, float y_, int w, int h) {
        applet = applet_;
        setX(x_);
        setY(y_);
        setXSpeed(0);
        setYSpeed(0);
        setWidth(w);
        setHeight(h);
        // The only rotations possible will be multiple of 30 and 45
        setRotation(0);
        isSoft = false;
        isBuffered = false;
        bufferFinalized = false;
        r = 0;
        g = 0;
        b = 0;
        stroke = 255;
        strokeWeight = 2;
    }

    public boolean collided(Icon p) {
        return false;
    }

    public Obstacle copy() {
        return null;
    }

    public int getBufferedHeight() { return bufferedHeight; }

    public float getBufferedY() { return bufferedY; }

    @Override public float getY() { return isBuffered()? bufferedY : super.getY(); }

    public float getTrueY() { return super.getY(); }

    public boolean isBuffered() { return isBuffered; }

    public void closeBuffer() {
        if (!bufferFinalized) {
            isBuffered = false;
            bufferFinalized = true;
        }
    }

    public boolean isHarmless() {
        return isHarmless;
    }

    public void isHarmless(boolean isPastPlayer) {
        isHarmless = isPastPlayer;
    }

    public void setBuffers(float y_, int h) {
        if (!bufferFinalized) {
            bufferedY = y_;
            bufferedHeight = h;
            isBuffered = true;
            bufferFinalized = true;
        }
    }
}
