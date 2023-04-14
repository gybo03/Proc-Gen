package Code;

public class Tile {
    private TileType tileType;
    private int generation;

    public Tile(TileType tileType, int generation) {
        this.tileType = tileType;
        this.generation = generation;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public int getGeneration() {
        return generation;
    }

    public void increaseGeneration(){
        this.generation++;
    }
}
