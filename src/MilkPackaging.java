import java.util.ArrayList;
import java.util.Random;

public class MilkPackaging {

    int row;
    int col;
    final int priceOfBuild = 400;
    int priceOfUpgrade;
    public static void MilkPackagingWork()
    {
       Warehouse.amountOfMilk();
        ArrayList<Warehouse>milks=Warehouse.getMilks();
        ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
        if(milks.size()>=1)
        {
            milks.remove(0);
            warehouses.remove(Product.getProduct("milk"));
            Random random = new Random();
            int row = random.nextInt(10) + 1;
            int col = random.nextInt(11) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("milkPackage",row,col,60);

        }
        else
        {
            System.out.println("you dont have enough of milk");
        }
    }


}
