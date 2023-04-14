package Code;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int[][][] preset = new int[14][4][4];
        //<editor-fold desc="Preset">
        int[][] G = {{0, 5, 6, 7}, {0, 4, 8, 9}, {0, 2, 6, 8}, {0, 3, 7, 9}};
        int[][] W = {{1, 4, 10, 11}, {1, 5, 12, 13}, {1, 3, 10, 12}, {1, 2, 11, 13}};

        int[][] EC = {{2, 8, 13, 14}, {2, 6, 11, 14}, {1, 3, 10, 12}, {0, 3, 7, 9}};
        int[][] WC = {{3, 9, 12, 7}, {3, 7, 10, 9}, {0, 2, 6, 8}, {1, 2, 11, 13}};
        int[][] SC = {{0, 5, 6, 7}, {1, 5, 12, 13}, {4, 9, 11, 14}, {4, 8, 10, 14}};
        int[][] NC = {{1, 4, 10, 11}, {0, 4, 8, 9}, {5, 7, 13, 14}, {5, 6, 12, 14}};

        int[][] NEC = {{2, 8, 13, 14}, {0, 4, 8, 9}, {5, 7, 13, 14}, {0, 3, 7, 9}};
        int[][] NWC = {{3, 9, 12, 14}, {0, 4, 8, 9}, {0, 2, 6, 8}, {5, 6, 12, 14}};
        int[][] SEC = {{0, 5, 6, 7}, {2, 6, 11, 14}, {4, 9, 11, 14}, {0, 3, 7, 9}};
        int[][] SWC = {{0, 5, 6, 7}, {3, 7, 10, 14}, {0, 2, 6, 8}, {4, 8, 10, 14}};

        int[][] INEC = {{3, 9, 12, 14}, {1, 5, 12, 13}, {4, 9, 11, 14}, {1, 2, 11, 13}};
        int[][] INWC = {{2, 8, 13, 14}, {1, 5, 12, 13}, {1, 3, 10, 12}, {4, 8, 10, 14}};
        int[][] ISEC = {{1, 4, 10, 11}, {3, 7, 10, 14}, {5, 7, 13, 14}, {1, 2, 11, 13}};
        int[][] ISWC = {{1, 4, 10, 11}, {2, 6, 11, 14}, {1, 3, 10, 12}, {5, 6, 12, 14}};


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
        //</editor-fold>
        int row=16,col=16;
        Map map = new Map(preset, row, col, 48);
        int prevEn=-1;
        int nextEn=map.getEnthropy();
        while(nextEn!=prevEn){
            System.out.println(map.getEnthropy());
            map.updateMap();
            prevEn=nextEn;
            nextEn=map.getEnthropy();
            Thread.sleep(100);
        }
        System.out.println("done");
    }
}