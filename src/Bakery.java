import java.util.ArrayList;
import java.util.Random;

public class Bakery {
    int row;
    int col;
    final int priceOfBuild = 250;
    int priceOfUpgrade;

    public static  void BakeryWork()
    {
        Warehouse.amountOfFlour();
        ArrayList<Warehouse>flours=Warehouse.getFlours();
        if(flours.size()>=1)
        {
            flours.remove(0);
            Random random = new Random();
            int row = random.nextInt(10) + 1;
            int col = random.nextInt(15) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("bread", row, col, 80);

        }
        else
        {
            System.out.println("you dont have enough flour ");
        }
    }

}
