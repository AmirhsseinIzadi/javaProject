import java.util.ArrayList;
import java.util.Random;

public class WildAnimal extends Animal {
    String [] names={"Lion","Bear","Tiger"};
    int damage=0;
    boolean inCage=false;
    private static ArrayList<WildAnimal>wildAnimals;
    static
    {
        wildAnimals=new ArrayList<>();
    }

    public WildAnimal(String name,int health, int row , int col )
    {
        super(name , row, col);
        this.row=row;
        this.col=col;
        if(name.equals(names[1])) {
            this.damage = 100;
        }
        else if(name.equals(names[1]))
        {
            this.damage=90;
        }
        else if(name.equals(names[2]))
        {
            this.damage=80;
        }
        setName(name);
        setHealth(health);
        wildAnimals.add(this);
    }

  public  static ArrayList<WildAnimal> getWildAnimals()
{
    return wildAnimals;
}

    @Override
    public void setRow(int row) {
        super.setRow(row);
    }

    @Override
    public void setCol(int col) {
        super.setCol(col);
    }

    @Override
    public int getRow() {
        return super.getRow();
    }

    @Override
    public int getCol() {
        return super.getCol();
    }
}
