package Code;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageMatrixViewer extends JFrame {
    private final int rows;
    private final int columns;
    private final int cellSize;

    private final String[] imagePaths;

    private final ImageIcon[] imageIcons;
    private final JLabel[][] imageLabels;

    public ImageMatrixViewer(int rows, int columns, int cellSize) {
        super("Image Matrix Viewer");
        this.rows = rows;
        this.columns = columns;
        this.cellSize=cellSize;


        String[] imagePaths1 = {
                "src/TestSprites/G.png", "src/TestSprites/W.png", "src/TestSprites/EC.png", "src/TestSprites/WC.png",
                "src/TestSprites/SC.png", "src/TestSprites/NC.png", "src/TestSprites/NEC.png", "src/TestSprites/NWC.png",
                "src/TestSprites/SEC.png", "src/TestSprites/SWC.png","src/TestSprites/INEC.png","src/TestSprites/INWC.png",
                "src/TestSprites/ISEC.png", "src/TestSprites/ISWC.png","src/TestSprites/F.png","src/TestSprites/NONE.png"
        };

        /*String[] imagePaths1 = {
                "src/Sprites1/G.png", "src/Sprites1/W.png", "src/Sprites1/EC.png", "src/Sprites1/WC.png",
                "src/Sprites1/SC.png", "src/Sprites1/NC.png", "src/Sprites1/NEC.png", "src/Sprites1/NWC.png",
                "src/Sprites1/SEC.png", "src/Sprites1/SWC.png","src/Sprites1/INEC.png","src/Sprites1/INWC.png",
                "src/Sprites1/ISEC.png", "src/Sprites1/ISWC.png","src/Sprites1/NONE.png","src/Sprites1/NONE.png"
        };*/

        /*String[] imagePaths1 = {
                "src/Sprites/G.png", "src/Sprites/W.png", "src/Sprites/EC.png", "src/Sprites/WC.png",
                "src/Sprites/SC.png", "src/Sprites/NC.png", "src/Sprites/NEC.png", "src/Sprites/NWC.png",
                "src/Sprites/SEC.png", "src/Sprites/SWC.png","src/Sprites/INEC.png","src/Sprites/INWC.png",
                "src/Sprites/ISEC.png", "src/Sprites/ISWC.png","src/Sprites/F.png","src/Sprites/NONE.png"
        };*/
        imagePaths=imagePaths1;
        this.imageLabels = new JLabel[rows][columns];

        setLayout(new GridLayout(rows, columns));
        imageIcons=new ImageIcon[imagePaths.length];

        for (int i = 0; i < imagePaths.length; i++) {
            imageIcons[i] = createImageIcon(imagePaths[i], cellSize);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                imageLabels[i][j]=new JLabel();
                add(imageLabels[i][j]);
            }
        }
        setSize(columns * cellSize, rows * cellSize+40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void zoomIn(int zoomLevel) {
        // Loop through each JLabel in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JLabel label = imageLabels[i][j];
                Icon icon = label.getIcon();

                // Calculate the new icon size based on the zoom level
                int iconWidth = Math.round(icon.getIconWidth() * zoomLevel / 100.0f);
                int iconHeight = Math.round(icon.getIconHeight() * zoomLevel / 100.0f);
                Image image = ((ImageIcon) icon).getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_DEFAULT);
                ImageIcon newIcon = new ImageIcon(image);

                // Set the new icon for the label
                label.setIcon(newIcon);
            }
        }
    }

    public void setImageAt(int x, int y, TileType tileType) {
        imageLabels[x][y].setIcon(imageIcons[tileType.getValue()]);
    }

    private ImageIcon createImageIcon(String imagePath, int cellSize) {
        try {
            Image image = ImageIO.read(new File(imagePath)).getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
