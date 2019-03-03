import java.awt.*;

public class Cube {
    private double x;
    private double y;
    private double velocity;
    private int width;
    private int height;
    private double mass = .0005;
    private int seconds = 0;
    private boolean goingUp = false;
    private double oldVelocity = 0;
    private double maxSeconds = 0;

    Cube(double x, double y, int width, int height, double velocity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }

    public void addTime() {
        seconds++;
    }

    public void removeTime() {
        if(seconds == 4) { peak(); }
        seconds--;
    }

    public void clearTime() {
        seconds = 0;
    }

    public void calcVelocity() {
        if (goingUp) {
            velocity = mass * Math.pow((-0.85 * Math.sqrt(2 * 9.81 * maxSeconds)), 2);
            removeTime();
        } else {
            velocity = 9.81 * mass * Math.pow(seconds, 2);
            addTime();
        }
    }

    public void move() {
        y += velocity * 2;
    }


    public void bounce() {
        if(!goingUp) {
            seconds *= .95;
            oldVelocity = velocity;
            maxSeconds = seconds;
        }
        goingUp = true;
    }

    public void peak() {
        goingUp = false;
    }


    public double getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawOval((int) x, (int) y, width, height);
    }

}
