import java.util.ArrayList;
import java.util.Random;

public class Tailoring{
    int row;
    int col;
    int priceOfBuild=400;
    int priceOfUpgrade;
    public static void TailoringWork()
    {
        Warehouse.amountOfCloth();
        ArrayList<Warehouse>cloths= Warehouse.getCloths();
        ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
        if(cloths.size()>=1)
        {
            cloths.remove(0);
            warehouses.remove(ProductOfFactory.getProductFactoryByName("cloth"));
            Random random = new Random();
            int row = random.nextInt(12) + 1;
            int col = random.nextInt(14) + 1;
            ProductOfFactory productOfFactory= new ProductOfFactory("dress",row,col,100);
        }
        else
        {
            System.out.println("you dont have enough cloth");
        }
    }
}
