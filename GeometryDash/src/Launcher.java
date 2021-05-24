import processing.core.PApplet;

public class Launcher extends PApplet
{
    private int page;
    private Level runningLevel;
    private Level[] levels;
    private Selector[] selectors;

    private boolean isStalling;
    private int stallCounter;
    private final int STALL_TIME = 60;

    public static void main(String[] args) {
        PApplet.main("Launcher");
    }

    public void settings() {
        size(1800, 1000);
    }

    public void setup() {
        page = 1;
        levels = new Level[3];

        // Level 1
        levels[0] = new Level(this, new Square(this, 0, height * 2 / 3 - 100, 100), 295);
        levels[0].setBackground(0, 80, 255);

        levels[0].add(new Block(this, -width, height * 2 / 3, 80000, height / 2, true));
        levels[0].add(new Spike(this, width, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 1.9F, height * 2 / 3 - 40, 100, 40));
        levels[0].add(new Spike(this, width * 1.9F + 100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 2.8F, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 2.8F + 100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 2.8F + 200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 2.8F + 300, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Block(this, width * 2.8F + 600, height * 2 / 3 - 200, 100, 200));
        levels[0].add(new Spike(this, width * 2.8F + 700, height * 2 / 3 - 10, 300, 10));

        levels[0].add(new Block(this, width * 2.8F + 1000, height * 2 / 3 - 300, 100, 300));
        levels[0].add(new Spike(this, width * 4.6F, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 4.6F + 100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 5, height * 2 / 3 - 100, (int)(width * 0.4), 100));
        levels[0].add(new Spike(this, width * 5.4F, height * 2 / 3 - 10, (int)(width * 0.2), 10));
        levels[0].add(new Block(this, width * 5.6F, height * 2 / 3 - 100, (int)(width * 0.7), 100));
        levels[0].add(new Spike(this, width * 5.95F - 50, height * 2 / 3 - 200, 100, 100));
        levels[0].add(new Spike(this, width * 6.3F, height * 2 / 3 - 10, (int)(width * 0.2), 10));
        levels[0].add(new Block(this, width * 6.5F, height * 2 / 3 - 200, (int)(width * 0.8), 200));
        levels[0].add(new Spike(this, width * 6.9F - 50, height * 2 / 3 - 300, 100, 100));

        levels[0].add(new Spike(this, width * 7.3F, height * 2 / 3 - 10, 2500, 10));
        levels[0].add(new Block(this, width * 7.3F + 300, height * 2 / 3 - 300, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 700, height * 2 / 3 - 400, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 1100, height * 2 / 3 - 500, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 1500, height * 2 / 3 - 600, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 1900, height * 2 / 3 - 700, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 2300, height * 2 / 3 - 600, 200, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, false, true, true);
        levels[0].add(new Block(this, width * 7.3F + 2500, height * 2 / 3 - 600, 1900, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, true, false, false);
        levels[0].add(new Block(this, width * 7.3F + 2500, height * 2 / 3 - 500, 3400, 500));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(false, false, true, true);
        levels[0].add(new Block(this, width * 7.3F + 4400, height * 2 / 3 - 500, 1000, 500));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, false, false, false);

        levels[0].add(new Block(this, width * 10.3F, height * 2 / 3 - 400, 400, 500));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(false, false, true, false);
        levels[0].add(new Spike(this, width * 7.3F + 2900, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 3000, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 3100, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 3200, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Block(this, width * 7.3F + 3100, height * 2 / 3 - 800, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 3000, height * 2 / 3 - 800, 100, 40));
        levels[0].add(new Spike(this, width * 7.3F + 3700, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 3800, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 3900, height * 2 / 3 - 700, 100, 100));

        levels[0].add(new Spike(this, width * 7.3F + 4000, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Block(this, width * 7.3F + 3800, height * 2 / 3 - 800, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 3900, height * 2 / 3 - 800, 100, 40));
        levels[0].add(new Block(this, width * 7.3F + 4700, height * 2 / 3 - 700, 400, 40));
        levels[0].add(new Spike(this, width * 7.3F + 4700, height * 2 / 3 - 800, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 4800, height * 2 / 3 - 800, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 4900, height * 2 / 3 - 800, 100, 100));
        levels[0].add(new Spike(this, width * 7.3F + 5000, height * 2 / 3 - 800, 100, 100));
        levels[0].add(new Block(this, width * 10.3F, height * 2 / 3 - 600, 500, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, true, false, true);
        levels[0].add(new Block(this, width * 10.3F, height * 2 / 3 - 500, 500, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(false, true, false, false);

        levels[0].add(new Spike(this, width * 10.3F + 400, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Block(this, width * 10.3F + 500, height * 2 / 3 - 300, 600, 300));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(false, true, true, false);
        levels[0].add(new Block(this, width * 10.3F + 500, height * 2 / 3 - 400, 600, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, false, false, false);
        levels[0].add(new Spike(this, width * 10.3F + 500, height * 2 / 3 - 500, 100, 100));
        levels[0].add(new Block(this, width * 10.3F + 1100, height * 2 / 3 - 400, 400, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, true, true, false);
        levels[0].add(new Block(this, width * 10.3F + 1700, height * 2 / 3 - 400, 400, 40));
        levels[0].add(new Spike(this, width * 10.3F + 2000, height * 2 / 3 - 500, 100, 100));
        levels[0].add(new Block(this, width * 10.3F + 2200, height * 2 / 3 - 300, 700, 40));
        levels[0].add(new Spike(this, width * 10.3F + 2800, height * 2 / 3 - 400, 100, 100));
        levels[0].add(new Block(this, width * 10.3F + 3000, height * 2 / 3 - 200, 500, 40));

        levels[0].add(new Block(this, width * 10.3F + 3800, height * 2 / 3 - 300, 100, 40));
        levels[0].add(new Block(this, width * 10.3F + 4200, height * 2 / 3 - 400, 100, 40));
        levels[0].add(new Block(this, width * 10.3F + 4600, height * 2 / 3 - 500, 100, 40));
        levels[0].add(new Block(this, width * 10.3F + 5000, height * 2 / 3 - 600, 100, 40));
        levels[0].add(new Block(this, width * 13.3F, height * 2 / 3 - 700, 100, 40));
        levels[0].add(new Block(this, width * 13.3F + 400, height * 2 / 3 - 800, 100, 40));
        levels[0].add(new Spike(this, width * 13.3F + 400, height * 2 / 3 - 900, 100, 100));
        levels[0].add(new Spike(this, width * 10.3F + 3500, height * 2 / 3 - 10, 2100, 10));
        levels[0].add(new Block(this, width * 13.3F + 200, height * 2 / 3 - 400, 700, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, false, true, true);
        levels[0].add(new Block(this, width * 13.3F + 900, height * 2 / 3 - 400, 2400, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, true, false, false);

        levels[0].add(new Block(this, width * 13.3F + 900, height * 2 / 3 - 300, 2400, 300));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(false, true, true, true);
        levels[0].add(new Block(this, width * 13.3F + 900, -height, 500, height * 5 / 3 - 700, 0, true));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, false, true, true);
        levels[0].add(new Block(this, width * 13.3F + 1400, -height, 1400, height * 5 / 3 - 700));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, false, false, false);
        levels[0].add(new Block(this, width * 13.3F + 1400, height * 2 / 3 - 700, 1400, 100));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(false, true, true, true);
        levels[0].add(new Block(this, width * 13.3F + 2800, -height, 500,height * 5 / 3 - 700, 0, true));
        ((Block)(levels[0].getCourse()[levels[0].getSize() - 1])).limitStroke(true, true, true, false);
        levels[0].add(new ShipPortal(this, width * 13.3F + 3200, height * 2 / 3 - 700, 100, 300));  // Ship Portal
        levels[0].add(new Block(this, width * 13.3F + 3300, -height, 14100, height * 9 / 10));
        levels[0].add(new Block(this, width * 15.1F + 3300, -height / 10, 100, height / 4));
        levels[0].add(new Block(this, width * 16 + 3300, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 16 + 3300, -height / 10, 100, 100));

        levels[0].add(new Block(this, width * 18 + 1200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 18 + 1200, -height / 10, 100, 100));
        levels[0].add(new Spike(this, width * 16 + 3400, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 16 + 3500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 300, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 400, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 500, height * 2 / 3 - 100, 100, 100));

        levels[0].add(new Spike(this, width * 18 + 600, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 700, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 800, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 900, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 1000, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 1100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 16 + 3400, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 16 + 3500, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 100, -height / 10, 100, 100, 180));

        levels[0].add(new Spike(this, width * 18 + 200, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 300, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 400, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 500, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 600, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 700, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 800, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 900, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 1000, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 18 + 1100, -height / 10, 100, 100, 180));

        levels[0].add(new Block(this, width * 18 + 2800, height * 2 / 3 - 200, 100, 200));
        levels[0].add(new Spike(this, width * 18 + 2900, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 3000, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 3100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 18 + 3200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 20 + 400, -height / 10, 100, 200));
        levels[0].add(new Spike(this, width * 20 + 500, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 20 + 600, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 20 + 700, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 20 + 800, -height / 10, 100, 100, 180));

        levels[0].add(new Block(this, width * 20 + 1300, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 20 + 1400, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 20 + 1500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 20 + 1600, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 20 + 1700, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 21, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 21 + 100, height * 2 / 3 - 200, 100, 200));
        levels[0].add(new Spike(this, width * 21 + 200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 21 + 300, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 21 + 400, height * 2 / 3 - 100, 100, 100));

        levels[0].add(new Spike(this, width * 21 + 500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 21 + 600, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 21 + 700, height * 2 / 3 - 300, 100, 300));
        levels[0].add(new Spike(this, width * 21 + 1500, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 21 + 1600, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 21 + 1700, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 100, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 200, -height / 10, 100, 100, 180));
        levels[0].add(new Block(this, width * 22 + 300, -height / 10, 100, 400));

        levels[0].add(new Spike(this, width * 22 + 400, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 500, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 600, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 700, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 800, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 900, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 1000, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 1100, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 1200, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 1300, -height / 10, 100, 100, 180));

        levels[0].add(new Spike(this, width * 22 + 1400, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 1500, -height / 10, 100, 100, 180));
        levels[0].add(new Spike(this, width * 22 + 1600, -height / 10, 100, 100, 180));
        levels[0].add(new Block(this, width * 22 + 1700, height * 2 / 3 - 300, 1500, 100));
        ((Block)levels[0].getCourse()[levels[0].getSize() - 1]).limitStroke(true, true, false, true);
        levels[0].add(new Block(this, width * 22 + 1700, height * 2 / 3 - 200, 1500, 200));
        ((Block)levels[0].getCourse()[levels[0].getSize() - 1]).limitStroke(false, true, false, true);
        levels[0].add(new Block(this, width * 22 + 1700, -height, 1500, height * 5 / 3 - 700));
        levels[0].add(new Block(this, width * 23 + 1400, height * 2 / 3 - 200, 1400, 200));
        ((Block)levels[0].getCourse()[levels[0].getSize() - 1]).limitStroke(true, true, false, false);
        levels[0].add(new Block(this, width * 24 + 1300, height * 2 / 3 - 300, 100, 300));
        levels[0].add(new Spike(this, width * 24 + 1400, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Block(this, width * 24 + 1700, height * 2 / 3 - 400, 100, 400));

        levels[0].add(new SquarePortal(this, width * 23 + 1300, height * 2 / 3 - 650, 100, 300));   // Square Portal
        levels[0].add(new Spike(this, width * 25, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Block(this, width * 25 + 300, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 25 + 300, height * 2 / 3 - 500, 100, 200));
        levels[0].add(new Spike(this, width * 25 + 400, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Block(this, width * 25 + 700, height * 2 / 3 - 200, 800, 200));
        levels[0].add(new Block(this, width * 25 + 700, height * 2 / 3 - 600, 100, 200));
        levels[0].add(new Spike(this, width * 24 + 1000, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Spike(this, width * 25 + 1500, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Block(this, width * 26, height * 2 / 3 - 300, 100, 300));

        levels[0].add(new Block(this, width * 26 + 400, height * 2 / 3 - 400, 100, 400));
        levels[0].add(new Block(this, width * 26 + 800, height * 2 / 3 - 500, 100, 500));
        levels[0].add(new Block(this, width * 26 + 1200, height * 2 / 3 - 600, 100, 600));
        levels[0].add(new Block(this, width * 26 + 1600, height * 2 / 3 - 700, 100, 700));
        levels[0].add(new Block(this, width * 27 + 200, height * 2 / 3 - 800, 100, 800));
        levels[0].add(new Block(this, width * 27 + 600, height * 2 / 3 - 900, 100, 900));
        levels[0].add(new Block(this, width * 27 + 700, height * 2 / 3 - 900, 100, 40));
        levels[0].add(new Block(this, width * 27 + 900, height * 2 / 3 - 800, 200, 40));
        levels[0].add(new Block(this, width * 27 + 1200, height * 2 / 3 - 700, 200, 40));
        levels[0].add(new Block(this, width * 27 + 1500, height * 2 / 3 - 600, 300, 40));

        levels[0].add(new Block(this, width * 28 + 300, height * 2 / 3 - 500, 200, 40));
        levels[0].add(new Block(this, width * 28 + 600, height * 2 / 3 - 400, 300, 400));
        levels[0].add(new Block(this, width * 28 + 600, height * 2 / 3 - 440, 300, 40));
        levels[0].add(new Block(this, width * 28 + 1200, height * 2 / 3 - 340, 400, 40));
        levels[0].add(new Block(this, width * 28 + 1200, height * 2 / 3 - 300, 400, 300));
        levels[0].add(new Block(this, width * 28 + 1700, height * 2 / 3 - 300, 100, 40));
        levels[0].add(new Block(this, width * 29 + 100, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 29 + 300, height * 2 / 3 - 100, 100, 40));
        levels[0].add(new Spike(this, width * 29 + 1000, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 29 + 1100, height * 2 / 3 - 100, 100, 100));

        levels[0].add(new Block(this, width * 29 + 1200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 29 + 1500, height * 2 / 3 - 200, 200, 40));
        levels[0].add(new Block(this, width * 30, height * 2 / 3 - 100, 300, 100));
        levels[0].add(new Spike(this, width * 29 + 1300, height * 2 / 3 - 10, 500, 10));
        levels[0].add(new Block(this, width * 30 + 500, height * 2 / 3 - 200, 200, 40));
        levels[0].add(new Block(this, width * 30 + 900, height * 2 / 3 - 300, 200, 40));
        levels[0].add(new Block(this, width * 30 + 1200, height * 2 / 3 - 100, 300, 100));
        levels[0].add(new Spike(this, width * 30 + 300, height * 2 / 3 - 10, 900, 10));
        levels[0].add(new Spike(this, width * 30 + 1500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 30 + 1600, height * 2 / 3 - 100, 100, 100));

        levels[0].add(new Spike(this, width * 31 + 600, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 31 + 700, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 31 + 600, height * 2 / 3 - 500, 100, 100, 180));
        levels[0].add(new Spike(this, width * 31 + 700, height * 2 / 3 - 500, 100, 100, 180));
        levels[0].add(new Block(this, width * 31 + 600, height * 2 / 3 - 600, 200, 100));
        levels[0].add(new Spike(this, width * 31 + 1500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 31 + 1590, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 31 + 1680, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 31 + 1500, height * 2 / 3 - 500, 100, 100, 180));
        levels[0].add(new Spike(this, width * 31 + 1590, height * 2 / 3 - 500, 100, 100, 180));

        levels[0].add(new Spike(this, width * 31 + 1680, height * 2 / 3 - 500, 100, 100, 180));
        levels[0].add(new Block(this, width * 31 + 1500, height * 2 / 3 - 600, 280, 100));
        levels[0].add(new Spike(this, width * 32 + 700, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 32 + 700, height * 2 / 3 - 500, 100, 100, 180));
        levels[0].add(new Block(this, width * 32 + 700, height * 2 / 3 - 600, 100, 100));
        levels[0].add(new Spike(this, width * 32 + 1500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 33 + 600, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 33 + 700, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 33 + 800, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 33 + 1200, height * 2 / 3 - 200, 100, 200));

        levels[0].add(new Block(this, width * 33 + 1600, height * 2 / 3 - 300, 100, 300));
        levels[0].add(new Block(this, width * 34 + 200, height * 2 / 3 - 400, 100, 400));
        levels[0].add(new Block(this, width * 34 + 300, height * 2 / 3 - 400, 100, 40));
        levels[0].add(new Block(this, width * 34 + 500, height * 2 / 3 - 300, 100, 40));
        levels[0].add(new Block(this, width * 34 + 700, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 34 + 900, height * 2 / 3 - 100, 100, 40));
        levels[0].add(new Spike(this, width * 34 + 300, height * 2 / 3 - 10, 700, 10));
        levels[0].add(new Spike(this, width * 33 + 900, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Spike(this, width * 33 + 1300, height * 2 / 3 - 10, 300, 10));
        levels[0].add(new Spike(this, width * 33 + 1700, height * 2 / 3 - 10, 300, 10));

        levels[0].add(new Spike(this, width * 34 + 1500, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 34 + 1600, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 35 + 500, height * 2 / 3 - 200, 100, 100, 180));
        levels[0].add(new Spike(this, width * 35 + 600, height * 2 / 3 - 200, 100, 100, 180));
        levels[0].add(new Spike(this, width * 35 + 700, height * 2 / 3 - 200, 100, 100, 180));
        levels[0].add(new Spike(this, width * 35 + 800, height * 2 / 3 - 200, 100, 100, 180));
        levels[0].add(new Block(this, width * 35 + 500, height * 2 / 3 - 300, 400, 100));
        levels[0].add(new Spike(this, width * 35 + 1300, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 35 + 1400, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 36 + 400, height * 2 / 3 - 100, 400, 40));

        levels[0].add(new Block(this, width * 36 + 1100, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 36 + 1500, height * 2 / 3 - 300, 100, 40));
        levels[0].add(new Block(this, width * 37 + 100, height * 2 / 3 - 400, 100, 40));
        levels[0].add(new Block(this, width * 37 + 500, height * 2 / 3 - 500, 300, 40));
        levels[0].add(new Block(this, width * 37 + 900, height * 2 / 3 - 400, 100, 100));
        levels[0].add(new Block(this, width * 37 + 1100, height * 2 / 3 - 300, 100, 100));
        levels[0].add(new Block(this, width * 37 + 1300, height * 2 / 3 - 200, 100, 100));
        levels[0].add(new Block(this, width * 37 + 1550, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 37 + 900, height * 2 / 3 - 700, 100, 100));
        levels[0].add(new Block(this, width * 37 + 1100, height * 2 / 3 - 600, 100, 100));

        levels[0].add(new Block(this, width * 37 + 1300, height * 2 / 3 - 500, 100, 100));
        levels[0].add(new Block(this, width * 37 + 1550, height * 2 / 3 - 400, 100, 100));
        levels[0].add(new Spike(this, width * 37 + 1550, height * 2 / 3 - 440, 100, 40));
        levels[0].add(new Block(this, width * 38 + 100, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 38 + 350, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 38 + 600, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Spike(this, width * 38 + 350, height * 2 / 3 - 300, 100, 100));
        levels[0].add(new Block(this, width * 38 + 900, height * 2 / 3 - 300, 300, 40));
        levels[0].add(new Block(this, width * 38 + 1300, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 38 + 1500, height * 2 / 3 - 100, 300, 40));

        levels[0].add(new Spike(this, width * 36 + 400, height * 2 / 3 - 10, width * 2 + 1400, 10));
        levels[0].add(new Spike(this, width * 39, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 39 + 100, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 39 + 820, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 39 + 900, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 39 + 980, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Spike(this, width * 39 + 820, height * 2 / 3 - 450, 100, 100, 180));
        levels[0].add(new Spike(this, width * 39 + 900, height * 2 / 3 - 450, 100, 100, 180));
        levels[0].add(new Spike(this, width * 39 + 980, height * 2 / 3 - 450, 100, 100, 180));
        levels[0].add(new Block(this, width * 39 + 820, height * 2 / 3 - 550, 260, 100));

        levels[0].add(new Block(this, width * 40 + 200, height * 2 / 3 - 100, 100, 100));
        levels[0].add(new Block(this, width * 40 + 300, height * 2 / 3 - 100, 200, 40));
        levels[0].add(new Spike(this, width * 40 + 300, height * 2 / 3 - 10, 1100, 10));
        levels[0].add(new Block(this, width * 40 + 800, height * 2 / 3 - 100, 100, 40));
        levels[0].add(new Block(this, width * 40 + 1200, height * 2 / 3 - 100, 200, 40));
        levels[0].add(new Block(this, width * 40 + 1400, height * 2 / 3 - 100, 200, 100));
        levels[0].add(new Block(this, width * 40 + 1500, height * 2 / 3 - 200, 100, 100));
        levels[0].add(new Block(this, width * 40 + 1600, height * 2 / 3 - 200, 200, 40));
        levels[0].add(new Block(this, width * 41 + 300, height * 2 / 3 - 300, 100, 40));
        levels[0].add(new Block(this, width * 41 + 500, height * 2 / 3 - 200, 100, 40));

        levels[0].add(new Block(this, width * 41 + 700, height * 2 / 3 - 100, 300, 40));
        levels[0].add(new Block(this, width * 41 + 1300, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 41 + 1500, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Spike(this, width * 41 + 1500, height * 2 / 3 - 300, 100, 100));
        levels[0].add(new Block(this, width * 41 + 1700, height * 2 / 3 - 200, 400, 40));
        levels[0].add(new Block(this, width * 42 + 425, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Spike(this, width * 42 + 425, height * 2 / 3 - 300, 100, 100));
        levels[0].add(new Block(this, width * 42 + 575, height * 2 / 3 - 200, 100, 40));
        levels[0].add(new Block(this, width * 42 + 975, height * 2 / 3 - 300, 300, 40));
        levels[0].add(new Spike(this, width * 42 + 1175, height * 2 / 3 - 400, 100, 100));

        levels[0].add(new Block(this, width * 42 + 1375, height * 2 / 3 - 200, 300, 40));
        levels[0].add(new Spike(this, width * 41, height * 2 / 3 - 10, 3575, 10));
        levels[0].add(new Block(this, width * 42 + 1775, height * 2 / 3 - 300, 100, 300));
        levels[0].add(new Block(this, width * 42 + 1775, height * 2 / 3 - 1000, 100, 300));
        levels[0].add(new ShipPortal(this, width * 42 + 1775, height * 2 / 3 - 650, 100, 300));

        levels[0].save();

        // Level 2
        levels[1] = new Level(this, new Square(this, 0, height * 2 / 3 - 100, 100), 13);
        levels[1].setBackground(200, 0, 100);
        levels[1].add(new Block(this, -width, height * 2 / 3, 7000, height / 2));
        levels[1].add(new JumpPad(this, 1500, height * 2 / 3 - 30, 100, 30));
        levels[1].add(new Spike(this, 1600, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 1700, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 1800, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 1900, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 2500, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 2600, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 3500, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 3600, height * 2 / 3 - 100, 100, 100));

        levels[1].add(new Block(this, 3700, height * 2 / 3 - 100, 100, 100));
        levels[1].add(new Spike(this, 3800, height * 2 / 3 - 10, 300, 10));
        levels[1].add(new Block(this, 4100, height * 2 / 3 - 200, 100, 200));

        levels[1].save();

        // Level 3
        levels[2] = new Level(this, new Square(this, 0, height * 2 / 3 - 100, 100), 2);
        levels[2].setBackground(0, 150, 0);
        levels[2].add(new Block(this, -width, height * 2 / 3, 5000, height / 2));
        levels[2].add(new JumpRing(this, 1000, height * 2 / 3 - 250, 60, 60));

        levels[2].save();

        selectors = new Selector[3];
        selectors[0] = new Selector(this, 100, 100, 500, 100, "Level 1", levels[0]);
        selectors[1] = new Selector(this, 100, 300, 500, 100, "Level 2", levels[1]);
        selectors[2] = new Selector(this, 100, 500, 500, 100, "Level 3", levels[2]);
    }

    public void draw() {
        if (page == 1) {
            background(0, 200, 0);
            if (!isStalling) {
                for (Selector s : selectors) {
                    runningLevel = s.checkPressed();
                    if (runningLevel != null) {
                        isStalling = true;
                        stallCounter = STALL_TIME;
                        break;
                    }
                }
            }
            for (Selector s : selectors) {
                s.draw();
            }
            if (stallCounter > 0) {
                stallCounter--;
                if (stallCounter == 0) {
                    isStalling = false;
                    for (Selector s : selectors) {
                        s.isPressed(false);
                    }
                    page = 2;
                }
            }
        } else {
            runningLevel.run();
            if (runningLevel.isFinished()) {
                page = 1;
            }
        }
    }

    public void keyPressed() {
        if (page != 1) {
            runningLevel.checkInputSensitiveModifiers();
        }
    }
}
