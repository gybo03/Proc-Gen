package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ColorMatrix extends JFrame {
    private final int ROWS;
    private final int COLS;
    private final int CELL_SIZE;
    private static final int MARGIN = 0;

    private final Color[][] colors;
    private final JPanel matrixPanel;

    public ColorMatrix(int ROWS, int COLS, int CELL_SIZE) {
        this.CELL_SIZE = CELL_SIZE;
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 1)); // Set the layout manager to GridLayout
        this.setSize((CELL_SIZE + MARGIN) * COLS + 14, (CELL_SIZE + MARGIN) * ROWS + 38);
        this.setResizable(false);

        colors = new Color[ROWS][COLS];
        //Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                //colors[i][j] = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                colors[i][j] = Color.BLACK;
            }
        }

        matrixPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLS; j++) {
                        g.setColor(colors[i][j]);
                        g.fillRect(MARGIN + j * (CELL_SIZE + MARGIN), MARGIN + i * (CELL_SIZE + MARGIN), CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        };
        this.add(matrixPanel);

        this.setVisible(true);
    }

    public void setColor(int row, int col, Color color) {
        colors[row][col] = color;
        matrixPanel.repaint();
    }
}