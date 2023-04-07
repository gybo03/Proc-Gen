package Code;

public class Tile {
    private TileType state;
    private int[] probability;

    public int generation;

    public Tile() {
        this.state = TileType.NONE;
        probability=new int[3];
        generation=0;
        initProb();
    }
    private void initProb(){
        probability[0]=0;
        probability[1]=0;
        probability[2]=0;
    }

    public TileType getState() {
        return state;
    }

    public void setState(TileType state) {
        this.state = state;
    }

    public int[] getProbability() {
        return probability;
    }

    public void setProbability(int[] probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
