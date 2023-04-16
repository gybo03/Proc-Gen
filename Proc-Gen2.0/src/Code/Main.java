package Code;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[][] tileM = {{1, 1, 1, 1, 1, 1}, {1, 12, 5, 5, 13, 1}, {1, 3, 0, 14, 14, 1}, {1, 3, 8, 14, 14, 5}, {1, 10, 11, 1, 10, 9}, {1, 1, 1, 1, 1, 3}};
        //int[] enabledTiles = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
        //int[] enabledTiles = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1};
        int[] enabledTiles = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0};
        int[][][] preset = new int[15][4][5];
        //<editor-fold desc="Preset">
        int[][] G = {{TileType.WATER.getValue(), 5, 6, 7, 14}, {0, 4, 8, 9, 14}, {0, 2, 6, 8, 14}, {0, 3, 7, 9, 14}};
        int[][] W = {{1, 4, 10, 11, 15}, {1, 5, 12, 13, 15}, {1, 3, 10, 12, 15}, {1, 2, 11, 13, 15}};
        int[][] F = {{0, 15, 15, 15, 15}, {0, 15, 15, 15, 15}, {0, 15, 15, 15, 15}, {0, 15, 15, 15, 15}};

        int[][] EC = {{2, 8, 13, 15, 15}, {2, 6, 11, 15, 15}, {1, 3, 10, 12, 15}, {0, 3, 7, 9, 15}};
        int[][] WC = {{3, 9, 12, 7, 15}, {3, 7, 10, 9, 15}, {0, 2, 6, 8, 15}, {1, 2, 11, 13, 15}};
        int[][] SC = {{0, 5, 6, 7, 15}, {1, 5, 12, 13, 15}, {4, 9, 11, 15, 15}, {4, 8, 10, 15, 15}};
        int[][] NC = {{1, 4, 10, 11, 15}, {0, 4, 8, 9, 15}, {5, 7, 13, 15, 15}, {5, 6, 12, 15, 15}};

        int[][] NEC = {{2, 8, 13, 15, 15}, {0, 4, 8, 9, 15}, {5, 7, 13, 15, 15}, {0, 3, 7, 9, 15}};
        int[][] NWC = {{3, 9, 12, 15, 15}, {0, 4, 8, 9, 15}, {0, 2, 6, 8, 15}, {5, 6, 12, 15, 15}};
        int[][] SEC = {{0, 5, 6, 7, 15}, {2, 6, 11, 15, 15}, {4, 9, 11, 15, 15}, {0, 3, 7, 9, 15}};
        int[][] SWC = {{0, 5, 6, 7, 15}, {3, 7, 10, 15, 15}, {0, 2, 6, 8, 15}, {4, 8, 10, 15, 15}};

        int[][] INEC = {{3, 9, 12, 15, 15}, {1, 5, 12, 13, 15}, {4, 9, 11, 15, 15}, {1, 2, 11, 13, 15}};
        int[][] INWC = {{2, 8, 13, 15, 15}, {1, 5, 12, 13, 15}, {1, 3, 10, 12, 15}, {4, 8, 10, 15, 15}};
        int[][] ISEC = {{1, 4, 10, 11, 15}, {3, 7, 10, 15, 15}, {5, 7, 13, 15, 15}, {1, 2, 11, 13, 15}};
        int[][] ISWC = {{1, 4, 10, 11, 15}, {2, 6, 11, 15, 15}, {1, 3, 10, 12, 15}, {5, 6, 12, 15, 15}};


        preset[TileType.GRASS.getValue()] = G;
        preset[TileType.WATER.getValue()] = W;
        preset[TileType.EAST_COAST.getValue()] = EC;
        preset[TileType.WEST_COAST.getValue()] = WC;
        preset[TileType.SOUTH_COAST.getValue()] = SC;
        preset[TileType.NORTH_COAST.getValue()] = NC;
        preset[TileType.NORTH_EAST_COAST.getValue()] = NEC;
        preset[TileType.NORTH_WEST_COAST.getValue()] = NWC;
        preset[TileType.SOUTH_EAST_COAST.getValue()] = SEC;
        preset[TileType.SOUTH_WEST_COAST.getValue()] = SWC;
        preset[TileType.iNORTH_EAST_COAST.getValue()] = INEC;
        preset[TileType.iNORTH_WEST_COAST.getValue()] = INWC;
        preset[TileType.iSOUTH_EAST_COAST.getValue()] = ISEC;
        preset[TileType.iSOUTH_WEST_COAST.getValue()] = ISWC;
        preset[TileType.FOREST.getValue()] = F;
        //</editor-fold>
        int row = 64, col = 64;
        Map map = new Map(preset, enabledTiles, row, col, 16);
        do {
            map.updateMap();
            Thread.sleep(100);
        } while (map.getEnthropy() > 0);

        //map.getImageMatrixViewer().zoomIn(200);

        /*for (int i = 0; i < 100; i++) {
            Map map = new Map(preset, row, col, 128);
            map.generateTileMap(tileM);
            //map.setMap(m);
            //Scanner sc=new Scanner(System.in);
            //int n;
            do{
                map.updateMap();
                //System.out.println(map.getEnthropy());
                //System.out.println(map);
                //n=sc.nextInt();
                Thread.sleep(3000);
            }while(map.getEnthropy()>0);
            System.out.println("done");
            Thread.sleep(500);
            map.getImageMatrixViewer().dispose();
        }*/

        //map.updateMap();

    }
}