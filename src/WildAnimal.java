import java.util.ArrayList;
import java.util.Random;

public class WildAnimal extends Animal{
    String [] names={"Lion","Bear","Tiger"};
    int damage=0;
     public boolean inCage=false;
     public int countForDie=0;
     public int distanceOfWalk=0;
    private static ArrayList<WildAnimal> wildAnimalsInCage;
    private static ArrayList<WildAnimal>wildAnimals;
    static
    {
        wildAnimals=new ArrayList<>();
    }
    static {
        wildAnimalsInCage = new ArrayList<>();
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
    public static ArrayList<WildAnimal> getWildAnimalInCage() {
        return wildAnimalsInCage;
    }
    public static WildAnimal getWildAnimalsInCageObject(ArrayList<String> bars)
    {
        for (WildAnimal wildAnimal : wildAnimalsInCage)
        {
            for (int i=0 ; i<bars.size();i++)
            {
                if(wildAnimal.getName().equalsIgnoreCase(bars.get(i)))
                {
                    return  wildAnimal;
                }
            }
        }
        return null;
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
