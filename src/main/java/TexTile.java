import java.util.ArrayList;
import java.util.Random;

public class TexTile {
int row;
int col;

int priceOfBuild=250;
int priceOfUpgrade;
public  static  void TexTileWork()
{
    Warehouse.amountOfFeather();
    ArrayList<Warehouse> feathers=Warehouse.getFeathers();
    ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
    if(feathers.size()>=1)
    {
        feathers.remove(0);
        warehouses.remove(Product.getProduct("feather"));
        Random random = new Random();
        int row = random.nextInt(15) + 1;
        int col = random.nextInt(16) + 1;
        ProductOfFactory productOfFactory= new ProductOfFactory("cloth",row,col,50);
    }
    else
    {
        System.out.println("you dont have enough cloth");
    }


}
}
