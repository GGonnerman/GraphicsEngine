import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = B_WIDTH / 2;
    private final int INITIAL_Y = B_HEIGHT / 2;
    private final int DELAY = 25;

    private Timer timer;
    private Cube[] cubeList = new Cube[1];
    private int visableCount = 1;
    private int width = 50;
    private int height = 50;


    public Board() {
        initBoard();
    }

    private void initBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        for (int i = 0; i < cubeList.length; i++) {
            cubeList[i] = new Cube(INITIAL_X, INITIAL_Y, width, height, 0);
        }

        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBall(g);
    }

    private void drawBall(Graphics g) {

        g.setColor(Color.WHITE);
        for (int i = 0; i < visableCount; i++) {
            cubeList[i].draw(g);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < visableCount; i++) {

            cubeList[i].calcVelocity();
            cubeList[i].move();

            double y = cubeList[i].getY();

            if (y > B_HEIGHT - 50 || y < 0) {
                cubeList[i].bounce();
            }

        }

        repaint();

        if (visableCount < cubeList.length) {
            visableCount++;
        }

    }

}
