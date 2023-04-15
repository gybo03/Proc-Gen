package Code;

import java.util.Arrays;
import java.util.OptionalInt;

public class Map {
    private int[][][] preset;
    private ImageMatrixViewer imageMatrixViewer;

    private Tile[][] map;
    private int row;
    private int col;

    private int enthropy;

    public Map(int[][][] preset, int row, int col, int size) {
        this.preset = preset;
        this.row = row;
        this.col = col;
        enthropy = row * col;
        this.imageMatrixViewer = new ImageMatrixViewer(row, col, size);
        map = new Tile[row][col];
        declareMap();
        initMap();
        startMap();
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                stringBuilder.append("|" + map[i][j].getTileType() + "|");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private void declareMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = new Tile(TileType.NONE, 0);
            }
        }
    }

    private TileType intToTileType(int i) {
        TileType tileType = null;
        for (TileType tt : TileType.values()) {
            if (tt.getValue() == i) {
                tileType = tt;
                break;
            }
        }
        return tileType;
    }

    public void generateTileMap(int[][] tileM) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (tileM[i][j] != 14) {
                    enthropy--;
                }
                initTile(i, j, intToTileType(tileM[i][j]));
            }
        }
    }

    public ImageMatrixViewer getImageMatrixViewer() {
        return imageMatrixViewer;
    }

    private void startMap() {
        int x = (int) (Math.random() * map.length);
        int y = (int) (Math.random() * map[0].length);
        initTile(0, 0, TileType.GRASS);
        enthropy--;
    }

    private void initMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                initTile(i, j, TileType.NONE);
            }
        }
    }

    private void initTile(int x, int y, TileType tileType) {
        map[x][y].setTileType(tileType);
        imageMatrixViewer.setImageAt(x, y, tileType);
    }

    private void updateGenrations() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j].getTileType() != TileType.NONE) {
                    map[i][j].increaseGeneration();
                }
            }
        }
    }

    private void updateNeighbours() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                updateTile(i, j);
            }
        }
    }

    private int[] getNumOfNeighbours(int x, int y) {
        int sum[] = {0, 0};
        if (x - 1 >= 0 && map[x - 1][y].getTileType() != TileType.NONE) {
            sum[0]++;
            sum[1] = Math.max(map[x - 1][y].getGeneration(), sum[1]);
        }
        if (x + 1 < row && map[x + 1][y].getTileType() != TileType.NONE) {
            sum[0]++;
            sum[1] = Math.max(map[x + 1][y].getGeneration(), sum[1]);
        }
        if (y - 1 >= 0 && map[x][y - 1].getTileType() != TileType.NONE) {
            sum[0]++;
            sum[1] = Math.max(map[x][y - 1].getGeneration(), sum[1]);
        }
        if (y + 1 < col && map[x][y + 1].getTileType() != TileType.NONE) {
            sum[0]++;
            sum[1] = Math.max(map[x][y + 1].getGeneration(), sum[1]);
        }
        return sum;
    }

    private void fillArray(int[][] preset, int x) {
        for (int i = 0; i < preset[0].length; i++) {
            preset[x][i] = -1;
        }
    }

    private double findMax(double[] bucket) {
        double max = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > max) {
                max = bucket[i];
            }
        }
        return max;
    }

    private void randomizeBucket(double[] bucket, double max) {
        for (int i = 0; i < bucket.length; i++) {
            if (max <= bucket[i]) {
                bucket[i] *= Math.random();
            } else {
                bucket[i] = 0;
            }
        }
    }

    private double[] calculateBucket(int[][] intersectionOfPresets) {
        double[] bucket = new double[14];
        for (int i = 0; i < intersectionOfPresets.length; i++) {
            for (int j = 0; j < intersectionOfPresets[0].length; j++) {
                if (intersectionOfPresets[i][j] != -1 && intersectionOfPresets[i][j] < 14) {
                    bucket[intersectionOfPresets[i][j]]++;
                }
            }
        }
        return bucket;
    }

    private int[][] calculateIntersectionPresets(int x, int y, boolean recursion) {
        int[][] intersectionOfPresets = new int[4][4];
        if (x - 1 >= 0 && map[x - 1][y].getTileType() != TileType.NONE) {
            intersectionOfPresets[0] = preset[map[x - 1][y].getTileType().getValue()][1];
        } else if (x - 1 >= 0 && map[x - 1][y].getTileType() == TileType.NONE && recursion) {
            intersectionOfPresets[0] = calculateIntersectionPresets(x - 1, y, false)[1];
        } else {
            fillArray(intersectionOfPresets, 0);
        }
        if (x + 1 < row && map[x + 1][y].getTileType() != TileType.NONE) {
            intersectionOfPresets[1] = preset[map[x + 1][y].getTileType().getValue()][0];
        } else if (x + 1 < row && map[x + 1][y].getTileType() == TileType.NONE && recursion) {
            intersectionOfPresets[1] = calculateIntersectionPresets(x + 1, y, false)[1];
        } else {
            fillArray(intersectionOfPresets, 1);
        }
        if (y + 1 < col && map[x][y + 1].getTileType() != TileType.NONE) {
            intersectionOfPresets[2] = preset[map[x][y + 1].getTileType().getValue()][3];
        } else if (y + 1 < col && map[x][y + 1].getTileType() == TileType.NONE && recursion) {
            intersectionOfPresets[2] = calculateIntersectionPresets(x, y + 1, false)[1];
        } else {
            fillArray(intersectionOfPresets, 2);
        }
        if (y - 1 >= 0 && map[x][y - 1].getTileType() != TileType.NONE) {
            intersectionOfPresets[3] = preset[map[x][y - 1].getTileType().getValue()][2];
        } else if (y - 1 >= 0 && map[x][y - 1].getTileType() == TileType.NONE && recursion) {
            intersectionOfPresets[3] = calculateIntersectionPresets(x, y - 1, false)[1];
        } else {
            fillArray(intersectionOfPresets, 3);
        }
        return intersectionOfPresets;
    }

    private void setTile(int x, int y, TileType tileType) {
        map[x][y].setTileType(tileType);
        map[x][y].increaseGeneration();
        imageMatrixViewer.setImageAt(x, y, tileType);
        enthropy--;
    }

    private void updateTile(int x, int y) {
        if (map[x][y].getTileType() == TileType.NONE && getNumOfNeighbours(x, y)[0] > 0 && getNumOfNeighbours(x, y)[1] > 1) {

            int[][] intersectionOfPresets = calculateIntersectionPresets(x, y, true);
            double[] bucket = calculateBucket(intersectionOfPresets);
            double max = findMax(bucket);
            randomizeBucket(bucket, max);
            max = findMax(bucket);

            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] >= max) {
                    setTile(x, y, intToTileType(i));
                    break;
                }
            }
        }
    }

    public void updateMap() {
        updateGenrations();
        updateNeighbours();
    }

    public int getEnthropy() {
        return enthropy;
    }
}
