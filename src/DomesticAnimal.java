import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DomesticAnimal extends Animal {
    int price;
    String product;
    public static int amountOfHens=0;
    public static int amountOFTurkey=0;
    public static int amountOfBuffalo=0;
    private int health=100;
    public int getCounterOfTurnForGenerateProduct=1;
    public  int counterForIncreaseHealth=0;
    public  int counterForDistanceOfWalk=0;
   public boolean generateProduct=false;
    static boolean Sell = false;
  public   boolean increaseHealthOfDomesticAnimals = false;
  public  boolean eatGrass=false;
    static String[] names = {"Hen", "Turkey", "Buffalo"};

    private static ArrayList<DomesticAnimal> domesticAnimals;

    static {
        domesticAnimals = new ArrayList<>();

    }


    public DomesticAnimal(String name, int row, int col, int price) {
        super(name, row, col);
        this.row = row;
        this.col = col;
        //this.name = name;
        if (name.equalsIgnoreCase(names[0])) {
            this.price = 100;
        } else if (name.equalsIgnoreCase(names[1])) {
            this.price = 200;
        } else if (name.equalsIgnoreCase(names[2])) {
            this.price = 400;
        }
        setName(name);
        domesticAnimals.add(this);

    }



    public static ArrayList<DomesticAnimal> getDomesticAnimals() {
        return domesticAnimals;
    }



    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getRow() {
        return super.getRow();
    }

    @Override
    public int getCol() {
        return super.getCol();
    }

    public String getProduct() {
        return product;
    }


}
