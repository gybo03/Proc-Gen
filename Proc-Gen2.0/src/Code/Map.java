package Code;

import java.util.Arrays;
import java.util.OptionalInt;

public class Map {
    private int[][][] preset;
    private ImageMatrixViewer imageMatrixViewer;

    private Tile[][] map;
    private int row;
    private int col;

    private  int enthropy=0;

    public Map(int[][][] preset,int row,int col,int size) {
        this.preset = preset;
        this.row=row;
        this.col=col;
        this.imageMatrixViewer = new ImageMatrixViewer(row, col, size);
        map=new Tile[row][col];
        declareMap();
        initMap();
        startMap();
    }

    private void declareMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j]=new Tile(TileType.NONE,0);
            }
        }
    }

    private void startMap(){
        int x = (int) (Math.random() * map.length);
        int y = (int) (Math.random() * map[0].length);
        initTile(x,y,TileType.GRASS);
    }

    private void initMap(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                initTile(i,j,TileType.NONE);
            }
        }
    }

    private void initTile(int x,int y,TileType tileType){
        map[x][y].setTileType(tileType);
        imageMatrixViewer.setImageAt(x,y,tileType);
    }

    private void updateGenrations(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(map[i][j].getTileType()!=TileType.NONE){
                    map[i][j].increaseGeneration();
                }
            }
        }
    }

    private void updateNeighbours(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                updateTile(i,j);
            }
        }
    }
    private int[] getNumOfNeighbours(int x,int y){
        int sum[]={0,0};
        if(x-1>=0&&map[x-1][y].getTileType()!=TileType.NONE){
            sum[0]++;
            sum[1]=Math.max(map[x-1][y].getGeneration(),sum[1]);
        }
        if(x+1<row&&map[x+1][y].getTileType()!=TileType.NONE){
            sum[0]++;
            sum[1]=Math.max(map[x+1][y].getGeneration(),sum[1]);
        }
        if(y-1>=0&&map[x][y-1].getTileType()!=TileType.NONE){
            sum[0]++;
            sum[1]=Math.max(map[x][y-1].getGeneration(),sum[1]);
        }
        if(y+1<col&&map[x][y+1].getTileType()!=TileType.NONE){
            sum[0]++;
            sum[1]=Math.max(map[x][y+1].getGeneration(),sum[1]);
        }
        return sum;
    }
    private void fillArray(int[][] preset,int x){
        for (int i = 0; i < preset[0].length; i++) {
            preset[x][i]=-1;
        }
    }
    private void updateTile(int x,int y){
        if(map[x][y].getTileType()==TileType.NONE&&getNumOfNeighbours(x,y)[0]>0&&getNumOfNeighbours(x,y)[1]>1){
            int[][] intersectionOfPresets=new int[4][4];
            if(x-1>=0&&map[x-1][y].getTileType()!=TileType.NONE){
                intersectionOfPresets[0]=preset[map[x-1][y].getTileType().getValue()][1];
            }else{
                fillArray(intersectionOfPresets,0);
            }
            if(x+1<row&&map[x+1][y].getTileType()!=TileType.NONE){
                intersectionOfPresets[1]=preset[map[x+1][y].getTileType().getValue()][0];
            }else{
                fillArray(intersectionOfPresets,1);
            }
            if(y+1<col&&map[x][y+1].getTileType()!=TileType.NONE){
                intersectionOfPresets[2]=preset[map[x][y+1].getTileType().getValue()][3];
            }else{
                fillArray(intersectionOfPresets,2);
            }
            if(y-1>=0&&map[x][y-1].getTileType()!=TileType.NONE){
                intersectionOfPresets[3]=preset[map[x][y-1].getTileType().getValue()][2];
            }else{
                fillArray(intersectionOfPresets,3);
            }
            double[] bucket=new double[15];
            for (int i = 0; i < intersectionOfPresets.length; i++) {
                for (int j = 0; j < intersectionOfPresets[0].length; j++) {
                    if(intersectionOfPresets[i][j]!=-1){
                        bucket[intersectionOfPresets[i][j]]++;
                    }
                }
            }
            for (int i = 0; i < bucket.length; i++) {
                bucket[i]*=Math.random();
            }
            double max=-1;
            for (int i = 0; i < bucket.length; i++) {
                if(bucket[i]>max){
                    max=bucket[i];
                }
            }
            for (int i = 0; i < bucket.length; i++) {
                if(max==bucket[i]){
                    TileType tileType = null;
                    for (TileType tt : TileType.values()) {
                        if (tt.getValue() == i) {
                            tileType = tt;
                            break;
                        }
                    }
                    enthropy++;
                    map[x][y].setTileType(tileType);
                    map[x][y].increaseGeneration();
                    imageMatrixViewer.setImageAt(x,y,tileType);
                    break;
                }
            }
        }
    }
    public void updateMap(){
        updateGenrations();
        updateNeighbours();
    }

    public  int getEnthropy() {
        return enthropy;
    }
}
