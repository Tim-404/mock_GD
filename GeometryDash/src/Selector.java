import processing.core.PApplet;

public class Selector
{
    protected PApplet applet;
    private float x;
    private float y;
    private int width;
    private int height;
    private String name;
    private Level level;
    private boolean isPressed;

    public Selector(PApplet applet_, float x_, float y_, int w, int h, String n, Level lvl) {
        applet = applet_;
        x = x_;
        y = y_;
        width = w;
        height = h;
        name = n;
        level = lvl;
        isPressed = false;
    }

    private boolean checkMouse() {
        return applet.mouseX > x && applet.mouseX < x + width
                && applet.mouseY > y && applet.mouseY < y + height;
    }

    public Level checkPressed() {
        if (applet.mousePressed && checkMouse()) {
            level.start();
            isPressed = true;
            return level;
        }
        return null;
    }

    public void draw() {
        float shift = 0;
        if (isPressed) {
            shift = Math.min(width, height) / 10F;
        }

        applet.stroke(255);
        applet.strokeWeight(2);
        applet.textAlign(applet.CENTER, applet.CENTER);
        applet.textSize(height / 3);
        if (checkMouse()) {
            applet.fill(255);
            applet.rect(x - shift, y - shift, width + shift * 2, height + shift * 2, (height + shift * 2) / 2);
            applet.fill(0);
        } else {
            applet.fill(100, 100);
            applet.rect(x - shift, y - shift, width + shift * 2, height + shift * 2, (height + shift * 2) / 2);
            applet.fill(255);
        }
        applet.text(name, x + width / 2, y + height / 2);
    }

    public void isPressed(boolean p) {
        isPressed = p;
    }
}
