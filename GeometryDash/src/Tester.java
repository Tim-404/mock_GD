import processing.core.PApplet;

public class Tester extends PApplet
{
    private Player player;
    private Obstacle[] obstacles;

    public static void main(String[] args) {
        PApplet.main("Tester");
    }

    public void settings() {
        size(1800, 1000);
    }

    public void setup() {
        player = new Player(this, new Square(this, 0, height * 2 / 3 - height / 10, height / 10));

        obstacles = new Obstacle[20];
        obstacles[0] = new Block(this, -500, height * 2 / 3, 100000, 400, true);
        obstacles[1] = new Block(this, 1500, height * 2 / 3 - 100, 500, 100);
        obstacles[2] = new Block(this, 4000, height * 2 / 3 - 200, 200, 200);
        obstacles[3] = new Block(this, 6000, height * 2 / 3 - 100, 500, 100);
        obstacles[4] = new Block(this, 8000, height * 2 / 3 - 150, 100, 50);
        obstacles[5] = new Block(this, 4400, height * 2 / 3 - 400, 100, 50);
        obstacles[6] = new Block(this, 4800, height * 2 / 3 - 500, 100, 50);
        obstacles[7] = new Block(this, 5200, height * 2 / 3 - 600, 100, 50);
        obstacles[8] = new Block(this, 5600, height * 2 / 3 - 700, 100, 50);
        obstacles[9] = new Block(this, 6000, height * 2 / 3 - 800, 100, 50);
        obstacles[10] = new Spike(this, 8500, height * 2 / 3 - 100, 100, 100);
        obstacles[11] = new Spike(this, 8590, height * 2 / 3 - 100, 100, 100);
        obstacles[12] = new Spike(this, 8680, height * 2 / 3 - 100, 100, 100);
        obstacles[13] = new ShipPortal(this, 9000, height * 2 / 3 - 400, 100, 400);
        obstacles[14] = new Block(this, 10000, height * 2 / 3 - 100, 3000, 50);
        obstacles[15] = new Block(this, 10000, height * 2 / 3 - 400, 3000, 50);
        obstacles[16] = new Spike(this, 11000, height * 2 / 3 - 200, 100, 100);
        obstacles[17] = new Spike(this, 12000, height * 2 / 3 - 200, 100, 100);
        obstacles[18] = new Block(this, 13500, height * 2 / 3 - 300, 1000, 100, 0, true);
        obstacles[19] = new JumpRing(this, 1000, height * 2 / 3 - 400, 60, 60);
    }

    public void draw() {
        background(0);

        player.move(obstacles);
        player.draw();

        for (Obstacle o : obstacles) {
            o.draw();
        }

        System.out.println(player.getY());
    }
}