import java.util.ArrayList;

public class Grass {
    int row;
    int col;
    static boolean buyGrass=false;
    public boolean eatenGrass=false;

    private static ArrayList<Grass>grasses;
    static
    {
        grasses=new ArrayList<>();
    }
    public Grass(int row , int col)
    {
        this.row=row;
        this.col=col;
        grasses.add(this);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static ArrayList<Grass> getGrasses()
    {
        return grasses;
    }



}
