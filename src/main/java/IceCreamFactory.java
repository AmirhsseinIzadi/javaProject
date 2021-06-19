import java.util.ArrayList;
import java.util.Random;

public class IceCreamFactory {
    int row;
    int col;
    final int priceOfBuild = 550;
    int priceOfUpgrade;

    public  static  void iceCreamWork()
    {
        Warehouse.amountOfMilkPackage();
        ArrayList<Warehouse>milksPackage=Warehouse.getMilksPackage();
        ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
        if(milksPackage.size()>=1)
        {
            milksPackage.remove(0);
            warehouses.remove(ProductOfFactory.getProductFactoryByName("milkPackage"));

            Random random = new Random();
            int row = random.nextInt(11) + 1;
            int col = random.nextInt(9) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("iceCream", row, col, 120);


        }
        else
        {
            System.out.println("you dont have enough milkPackage");
        }
    }
}
