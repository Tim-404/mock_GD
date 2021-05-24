import processing.core.PApplet;

public class Unit
{
    protected PApplet applet;
    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private int width;
    private int height;
    private int rotation;
    protected int r;
    protected int g;
    protected int b;
    protected int stroke;
    protected int strokeWeight;

    public void draw() {}

    public int getHeight() { return height; }

    public int getRotation() { return rotation; }

    public int getWidth() { return width; }

    public float getX() { return x; }

    public float getXSpeed() { return xSpeed; }

    public float getY() { return y; }

    public float getYSpeed() { return ySpeed; }

    public void moveX(float xDiff) { x += xDiff; }

    public void moveY(float yDiff) { y += yDiff; }

    public void rotate(float angle) { rotation += angle; }

    public void setColor(int r_, int g_, int b_) {
        r = r_;
        g = g_;
        b = b_;
    }

    public void setHeight(int h) { height = h; }

    public void setRotation(int r) { rotation = r; }

    public void setWidth(int w) { width = w; }

    public void setX(float x_) { x = x_; }

    public void setXSpeed(float xSpeed_) { xSpeed = xSpeed_; }

    public void setY(float y_) { y = y_; }

    public void setYSpeed(float ySpeed_) { ySpeed = ySpeed_; }
}
