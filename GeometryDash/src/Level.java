import processing.core.PApplet;

public class Level
{
    protected PApplet applet;
    private int attempt;
    private boolean isFinished;
    private boolean isRunning;
    private boolean isSaved;
    private int size;

    private int end;
    private Obstacle[] obstacles;
    private Obstacle[] original;
    private Modifier[] inputSensitiveModifiers;
    private int iSMSize;

    private float distTraveled;
    protected Player player;
    private final float startX;
    private final float startY;

    private boolean isStalling;
    private int stallCounter;
    public final static int STALL_TIME = 60;   // 1 sec

    private float r;
    private float g;
    private float b;
    private float targetR;
    private float targetG;
    private float targetB;
    private float rSplit;
    private float gSplit;
    private float bSplit;
    private float startR;
    private float startG;
    private float startB;

    public Level(PApplet applet_, Icon p, int size_) {
        applet = applet_;
        attempt = 1;
        distTraveled = 0;
        end = 0;
        player = new Player(applet, p);
        obstacles = new Obstacle[Math.max(size_, 1)];
        original = new Obstacle[obstacles.length];
        inputSensitiveModifiers = new Modifier[obstacles.length];
        size = 0;
        iSMSize = 0;
        startX = p.getX();
        startY = p.getY();
        isFinished = true;
        isRunning = false;
        isSaved = false;
        isStalling = false;

        r = 0;
        g = 0;
        b = 0;
        targetR = r;
        targetG = g;
        targetB = b;
        startR = r;
        startG = g;
        startB = b;
    }

    public boolean add(Obstacle o) {
        if (size < obstacles.length) {
            obstacles[size] = o;
            size++;
            return true;
        }
        return false;
    }

    public void checkInputSensitiveModifiers() {
        for (int i = 0; i < iSMSize; i++) {
            if (player.collidedWith(inputSensitiveModifiers[i]) && inputSensitiveModifiers[i].activate(player)) {
                break;
            }
        }
    }

    private void exit() {
        isFinished = true;
        for (int i = 0; i < size; i++) {
            obstacles[i] = original[i].copy();
        }
        player.swap(new Square(applet, startX, startY, 100));
        distTraveled = 0;
        player.isAlive = true;
        isRunning = false;
        attempt = 1;
        resetBackground();
    }

    public int getSize() { return size; }

    public Obstacle[] getCourse() { return obstacles; }

    public boolean isFinished() {
        return isFinished;
    }

    public void isFinished(boolean f) {
        isFinished = f;
    }

    public boolean isSaved() { return isSaved; }

    public void isRunning(boolean r) {
        isRunning = r;
    }

    private void reset() {
        for (int i = 0; i < size; i++) {
            obstacles[i] = original[i].copy();
            obstacles[i].moveX(applet.width / 3);
        }
        player.swap(new Square(applet, startX, startY, 100));
        player.moveX(applet.width / 3);
        distTraveled = 0;
        player.isAlive = true;
        isRunning = true;
        attempt++;
        resetBackground();
    }

    private void resetBackground() {
        r = startR;
        g = startG;
        b = startB;
        targetR = r;
        targetG = g;
        targetB = b;
    }

    public void run() {
        applet.background(r, g, b);
        if (r != targetR) {
            if (Math.abs(rSplit) > Math.abs(targetR - r)) {
                r = targetR;
            } else {
                r += rSplit;
            }
        }
        if (g != targetG) {
            if (Math.abs(gSplit) > Math.abs(targetG - g)) {
                g = targetG;
            } else {
                g += gSplit;
            }
        }
        if (b != targetB) {
            if (Math.abs(bSplit) > Math.abs(targetB - b)) {
                b = targetB;
            } else {
                b += bSplit;
            }
        }

        if (isRunning) {
            player.move(obstacles);
            if (player.getX() >= applet.width / 3) {
                distTraveled += player.getXSpeed();
            }
        }
        player.draw();

        for (int i = 0; i < size; i++) {
            if (obstacles[i].getX() + obstacles[i].getWidth() > 0 && obstacles[i].getX() < applet.width) {
                obstacles[i].draw();
            }
        }

        applet.fill((r + g + b) / 3 > 127? 0 : 255);
        applet.textSize(applet.height / 10);
        applet.text("Attempt " + attempt, applet.width / 2 - distTraveled, applet.height / 3);
        applet.text("Level Complete!", obstacles[end].getX() + obstacles[end].getWidth() + applet.width / 2, applet.height / 3);

        // Player died
        if (!player.isAlive) {
            isRunning = false;
            if (!isStalling) {
                stallCounter = STALL_TIME;
                isStalling = true;
            }
            stallCounter--;
            if (stallCounter == 0) {
                reset();
                isStalling = false;
            }
        }

        // Level Completed!
        if (player.getX() > obstacles[end].getX() + obstacles[end].getWidth() + player.getWidth() || (isStalling && player.isAlive)) {
            isRunning = false;
            if (!isStalling) {
                stallCounter = STALL_TIME * 3 / 2;
                isStalling = true;
            }

            player.moveX((float)Math.pow((STALL_TIME * 3F / 2 - stallCounter) / STALL_TIME * 2.5, 4) - applet.width / 900F);
            player.moveY(-(float)Math.pow(stallCounter, 0.25));
            player.rotate(3);
            stallCounter--;
            if (stallCounter == 0) {
                exit();
                isStalling = false;
            }
        }

        if (applet.keyPressed && applet.key == 'q') {
            exit();
        }
    }

    // Sorts obstacles into optimal processing order, then saves a copy
    public void save() {
        // Makes array full so move() in Icon works correctly
        if (size < obstacles.length) {
            for (int i = 0; i < size; i++) {
                original[i] = obstacles[i].copy();
            }
            obstacles = new Obstacle[size];
            for (int i = 0; i < size; i++) {
                obstacles[i] = original[i].copy();
            }
        }

        // Sorts in order of increasing x position
        for (int i = 1; i < size; i++) {
            Obstacle copy = obstacles[i].copy();
            int j = 1;
            while (j <= i && copy.getX() < obstacles[i - j].getX()) {
                obstacles[i - j + 1] = obstacles[i - j].copy();
                j++;
            }
            obstacles[i - j + 1] = copy;
        }

        // Sorts harmless obstacles to the front
        for (int i = 0; i < size; i++) {
            if (obstacles[i].isHarmless() && !obstacles[i].isAlwaysFatal) {
                Obstacle copy = obstacles[i].copy();
                for (int j = i; j > 0; j--) {
                    obstacles[j] = obstacles[j - 1].copy();
                }
                obstacles[0] = copy;
            }
        }

        // Sorts most dangerous obstacles to the back
        for (int i = size - 1; i >= 0; i--) {
            if (obstacles[i].isAlwaysFatal) {
                Obstacle copy = obstacles[i].copy();
                for (int j = i; j < size - 1; j++) {
                    obstacles[j] = obstacles[j + 1].copy();
                }
                obstacles[size - 1] = copy;
            }
        }

        // Saves array to original[]
        for (int i = 0; i < size; i++) {
            original[i] = obstacles[i].copy();

            // Sets identifier for the end of the level
            if (obstacles[i].getX() + obstacles[i].getWidth() > obstacles[end].getX() + obstacles[end].getWidth()) {
                end = i;
            }
        }

        // Saves input sensitive modifiers in a separate array
        for (int i = 0; i < size; i++) {
            if (obstacles[i] instanceof Modifier && ((Modifier)obstacles[i]).requiresInput) {
                inputSensitiveModifiers[iSMSize] = (Modifier)obstacles[i];
                iSMSize++;
            }
        }

        isSaved = true;
    }

    public void setBackground(int rgb) {
        r = rgb;
        g = rgb;
        b = rgb;
    }

    public void setBackground(int r_, int g_, int b_) {
        r = r_;
        g = g_;
        b = b_;
        targetR = (int)r;
        targetG = (int)g;
        targetB = (int)b;
        startR = r;
        startG = g;
        startB = b;
    }

    public void start() {
        isFinished = false;
        isRunning = true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += obstacles[i].getX() + ": " + obstacles[i].isHarmless() + ", " + obstacles[i].isAlwaysFatal + "\n";
        }
        return s;
    }

    public void transitionBackground(int r_, int g_, int b_, int time, String type) {
        if (type.equals("flash")) {
            r = r_;
            g = g_;
            b = b_;
        } else {
            targetR = r_;
            targetG = g_;
            targetB = b_;
        }
        rSplit = (targetR - r) / time;
        gSplit = (targetG - g) / time;
        bSplit = (targetB - b) / time;
    }
}
