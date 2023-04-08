package Code;

import Graphics.ColorMatrix;

import java.awt.*;

public class Map {
    private Tile[][] map;
    private int[][] presetMatrix;

    private ColorMatrix colorMatrix;
    private int entropy;

    public Map(int rows, int cols, int[][] presetMatrix, ColorMatrix colorMatrix) {
        this.colorMatrix = colorMatrix;
        map = new Tile[rows][cols];
        entropy = rows * cols;
        this.presetMatrix = presetMatrix;
        initMap();
        startMap();
    }

    private void initMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile();
            }
        }
    }

    private void startMap() {
        for (int i = 0; i < 1; i++) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map[0].length);
            //int x=0,y=0;
            map[x][y].setState(TileType.GRAS);
            map[x][y].generation = 2;
            map[x][y].setProbability(presetMatrix[0]);
            updateTile(x, y);
            colorMatrix.setColor(x, y, Color.green);
        }
        /*int x=0,y=2;
        map[x][y].setState(TileType.WATR);
        map[x][y].generation = 2;
        map[x][y].setProbability(presetMatrix[2]);
        updateTile(x, y);
        colorMatrix.setColor(x, y, Color.blue);*/
    }

    public void updateMap() {
        updateProb();
        updateNeighbours();
        updateGeneration();
    }

    private void updateGeneration() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].getState() != TileType.NONE) {
                    map[i][j].generation++;
                }
            }
        }
    }

    private void updateNeighbours() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                updateTile(i, j);
            }
        }
    }

    private int maxGenerationOfNeighbours(int i, int j) {
        int[] gen = new int[4];
        if (i - 1 >= 0 && map[i - 1][j].getState() != TileType.NONE) {
            gen[0] = map[i - 1][j].generation;
        }
        if (i + 1 < map.length && map[i + 1][j].getState() != TileType.NONE) {
            gen[1] = map[i + 1][j].generation;
        }
        if (j - 1 >= 0 && map[i][j - 1].getState() != TileType.NONE) {
            gen[2] = map[i][j - 1].generation;
        }
        if (j + 1 < map[0].length && map[i][j + 1].getState() != TileType.NONE) {
            gen[3] = map[i][j + 1].generation;
        }
        int max = -1;
        for (int k = 0; k < gen.length; k++) {
            if (max < gen[k]) {
                max = gen[k];
            }
        }
        return max;
    }

    private void updateTile(int i, int j) {
        if (map[i][j].getState() == TileType.NONE && getNumberOfNeighbours(i, j) > 0 && maxGenerationOfNeighbours(i, j) > 1) {
            int[] prob = getNeighbourProb(i, j);
            int[] randProb = new int[prob.length];
            for (int k = 0; k < prob.length; k++) {
                randProb[k] = (int) (Math.random() * prob[k]);
            }
            int max = findMax(randProb);
            int stateOfMax = -1;
            for (int k = 0; k < randProb.length; k++) {
                if (max == randProb[k]) {
                    stateOfMax = k;
                }
            }
            //System.out.println(stateOfMax);
            switch (stateOfMax) {
                case 0 -> {
                    map[i][j].setState(TileType.GRAS);
                    colorMatrix.setColor(i, j, Color.green);
                }
                case 1 -> {
                    map[i][j].setState(TileType.SAND);
                    colorMatrix.setColor(i, j, Color.yellow);
                }
                case 2 -> {
                    map[i][j].setState(TileType.WATR);
                    colorMatrix.setColor(i, j, Color.blue);
                }
            }
            map[i][j].generation++;
            entropy--;
        }
    }

    public int getEntropy() {
        return entropy;
    }

    private int findMax(int[] prob) {
        int max = -1;
        for (int i = 0; i < prob.length; i++) {
            if (max < prob[i]) {
                max = prob[i];
            }
        }
        return max;
    }

    private int[] getNeighbourProb(int i, int j) {
        int[] prob = new int[3];
        boolean changed=false;
        if (i - 1 >= 0 && map[i - 1][j].getState() != TileType.NONE) {
            changed=sumProb(prob, i - 1, j,changed);
        }
        if (i + 1 < map.length && map[i + 1][j].getState() != TileType.NONE) {
            changed=sumProb(prob, i + 1, j,changed);
        }
        if (j - 1 >= 0 && map[i][j - 1].getState() != TileType.NONE) {
            changed=sumProb(prob, i, j - 1,changed);
        }
        if (j + 1 < map[0].length && map[i][j + 1].getState() != TileType.NONE) {
            changed=sumProb(prob, i, j + 1,changed);
        }
        return prob;
    }

    private boolean sumProb(int[] prob, int i, int j,boolean change) {
        for (int k = 0; k < prob.length; k++) {
            if (map[i][j].getProbability()[k] == 0||(prob[k]==0&&change)) {
                prob[k] *= map[i][j].getProbability()[k];
            }else{
                prob[k] += map[i][j].getProbability()[k];
            }
        }
        return true;
    }

    private int getNumberOfNeighbours(int i, int j) {
        int amount = 0;
        if (i - 1 >= 0 && map[i - 1][j].getState() != TileType.NONE) {
            amount++;
        }
        if (i + 1 < map.length && map[i + 1][j].getState() != TileType.NONE) {
            amount++;
        }
        if (j - 1 >= 0 && map[i][j - 1].getState() != TileType.NONE) {
            amount++;
        }
        if (j + 1 < map[0].length && map[i][j + 1].getState() != TileType.NONE) {
            amount++;
        }
        return amount;
    }

    private void updateProb() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].getState() != TileType.NONE) {
                    map[i][j].setProbability(preset(map[i][j].getState()));
                }
            }
        }
    }

    private int[] preset(TileType state) {
        int[] prob = new int[3];
        switch (state) {
            case GRAS -> setProb(prob, 0);
            case SAND -> setProb(prob, 1);
            case WATR -> setProb(prob, 2);
        }
        return prob;
    }

    private void setProb(int[] prob, int state) {
        for (int i = 0; i < prob.length; i++) {
            prob[i] = presetMatrix[state][i];
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                stringBuilder.append(map[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
