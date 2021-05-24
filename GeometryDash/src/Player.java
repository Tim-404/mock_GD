import processing.core.PApplet;

public class Player
{
    protected PApplet applet;
    private Icon character;
    protected boolean isAlive;

    public Player(PApplet applet_, Icon p) {
        applet = applet_;
        character = p;
        isAlive = true;
    }

    public boolean collidedWith(Obstacle o) { return character.collidedWith(o); }

    public void draw() {
        character.draw();
    }

    public int getWidth() {
        return character.getWidth();
    }

    public float getX() {
        return character.getX();
    }

    public float getXSpeed() { return character.getXSpeed(); }

    public float getY() {
        return character.getY();
    }

    public void move(Obstacle[] obstacles) {
        character.move(obstacles, this);
    }

    public void moveX(float xDiff) {
        character.moveX(xDiff);
    }

    public void moveY(float yDiff) {
        character.moveY(yDiff);
    }

    public void rotate(int r) {
        character.rotate(r);
    }

    public void setX(float x_) {
        character.setX(x_);
    }

    public void setXSpeed(float xSpeed_) { character.setXSpeed(xSpeed_); }

    public void setY(float y_) { character.setY(y_); }

    public void setYSpeed(float ySpeed_) { character.setYSpeed(ySpeed_); }

    public void swap(Icon p) {
        character = p;
    }
}
