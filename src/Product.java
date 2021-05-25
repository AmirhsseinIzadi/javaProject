import java.util.ArrayList;

public class Product {
    String name;
    int  amount;
    int priceOfSell;
    int row;
    int col;
     public   int amountOfEgg = 0;
    public int amountOfFeather = 0;
     public  int amountOfMilk = 0;
      public  char EGG='e';
   public char MILK='m';
     public   char FEATHER='f';

    private static ArrayList<Product>products;
    static
    {
        products=new ArrayList<>();
    }
    static String[] typesOfProduct = {"egg", "feather", "milk"};
    public Product(String name, int priceOfSell, int row , int col)
    {
        this.name=name;
        this.row=row;
        this.col=col;
        this.priceOfSell=priceOfSell;
        products.add(this);
    }

    public  static  ArrayList<Product> getProducts()
    {
        return products;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPriceOfSell() {
        return priceOfSell;
    }
}
