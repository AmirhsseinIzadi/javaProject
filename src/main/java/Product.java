import java.util.ArrayList;

public class Product extends Warehouse {
    static String[] typesOfProduct = {"egg", "feather", "milk"};
    private static final ArrayList<Product> products;

    static {
        products = new ArrayList<>();
    }

    public final int Capacity_product = 1;
    public char EGG = 'e';
    public char MILK = 'm';
    public char FEATHER = 'f';
    public boolean showProduct = false;
    public int counterForDestroy = 0;
    String name;
    int amount;
    int priceOfSell;
    int row;
    int col;

    public Product(String name, int priceOfSell, int row, int col) {

        this.name = name;
        this.row = row;
        this.col = col;
        this.priceOfSell = priceOfSell;
        products.add(this);
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static Product getProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public static Product getProductByRowAndColumn(int row, int col) {
        for (Product product : products) {
            if (product.row == row && product.col == col) {
                return product;
            }
        }
        return null;
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
