package Code;

public enum TileType {
    GRASS(0),
    WATER(1),
    EAST_COAST(2),
    WEST_COAST(3),
    SOUTH_COAST(4),
    NORTH_COAST(5),
    NORTH_EAST_COAST(6),
    NORTH_WEST_COAST(7),
    SOUTH_EAST_COAST(8),
    SOUTH_WEST_COAST(9),
    iNORTH_EAST_COAST(10),
    iNORTH_WEST_COAST(11),
    iSOUTH_EAST_COAST(12),
    iSOUTH_WEST_COAST(13),
    FOREST(14),
    NONE(15);


    private final int value;

    private TileType(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
