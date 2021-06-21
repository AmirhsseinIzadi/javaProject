import java.util.ArrayList;

public class Grass {
    static boolean buyGrass = false;
    private static final ArrayList<Grass> grasses;

    static {
        grasses = new ArrayList<>();
    }

    public boolean eatenGrass = false;
    public int counterForDisAppear = 0;
    int row;
    int col;

    public Grass(int row, int col) {
        this.row = row;
        this.col = col;
        grasses.add(this);
    }

    public static ArrayList<Grass> getGrasses() {
        return grasses;
    }

    public static Grass getGrassByRowAndColumn(int row, int col) {
        for (Grass grass : grasses) {
            if (grass.row == row && grass.col == col) {
                return grass;
            }
        }
        return null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


}
