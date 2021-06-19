import java.util.ArrayList;
import java.util.Random;

public class Mill {
    int row;
    int col;
    final int priceOfBuild = 150;
    int priceOfUpgrade;
    public static void MillWork() {
        Warehouse.amountOfEgg();
        ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
        ArrayList<Warehouse> eggs = Warehouse.getEggs();
        if (eggs.size() >= 1) {
            eggs.remove(0);
            warehouses.remove(Product.getProduct("egg"));

            Random random = new Random();
            int row = random.nextInt(1) + 1;
            int col = random.nextInt(2) + 1;
                ProductOfFactory productOfFactory = new ProductOfFactory("flour", row, col, 40);
        }
        else
        {
            System.out.println("you dont have enough egg");
        }
    }

}
