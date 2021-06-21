import java.util.ArrayList;

public class ProductOfFactory extends Warehouse {

    private static final ArrayList<ProductOfFactory> productOfFactories;

    static {
        productOfFactories = new ArrayList<>();
    }

    public boolean showFlour = false;
    public boolean showCloth = false;
    public boolean showMilkPackage = false;
    public boolean showBread = false;
    public boolean showIceCream = false;
    public boolean showDress = false;
    public int counterForConvertEggToFlour = 0;
    public int counterForConvertFeatherToCloth = 0;
    public int countForConvertMilkToMilkPackage = 0;
    public int countForConvertFlourToBread = 0;
    public int countForConvertClothToDress = 0;
    public int countForConvertMilkPackageToIceCream = 0;
    public boolean destroyFlour = false;
    public int counterForDestroyFlour = 0;
    public int counterForDestroyCloth = 0;
    public int counterForDestroyMilkPackage = 0;
    public int counterForDestroyBread = 0;
    public int counterForDestroyIceCream = 0;
    public int counterForDestroyDress = 0;
    String name;
    int amount;
    int priceOfSell;
    int row;
    int col;

    public ProductOfFactory(String name, int row, int col, int priceOfSell) {
        this.row = row;
        this.col = col;
        this.name = name;
        this.priceOfSell = priceOfSell;
        productOfFactories.add(this);
    }

    public static ArrayList<ProductOfFactory> getProductOfFactories() {
        return productOfFactories;
    }

    public static ProductOfFactory getProductFactoryByName(String name) {
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase(name)) {
                return productOfFactory;
            }
        }
        return null;
    }

    public int getPriceOfSell() {
        return priceOfSell;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
