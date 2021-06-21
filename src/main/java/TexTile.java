import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class TexTile {
    int row;
    int col;

    int priceOfBuild = 250;
    int priceOfUpgrade;

    public static void TexTileWork() {
        Warehouse.amountOfFeather();
        ArrayList<Warehouse> feathers = Warehouse.getFeathers();
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        if (feathers.size() >= 1) {
            feathers.remove(0);
            warehouses.remove(Product.getProduct("feather"));
            Random random = new Random();
            int row = random.nextInt(15) + 1;
            int col = random.nextInt(16) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("cloth", row, col, 50);
        } else {
            print("Error: you dont have enough cloth");
        }

    }

    public static void print(String response) {
        String thisLine = null ;
        String saveMessage="" ;
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(new File( DataBase.loggerAddress).getPath())) ;
            while ((thisLine = bufferedReader.readLine()) != null) {
                saveMessage+=thisLine+"\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (response.startsWith("Info: ")) {
            System.out.println(response.replaceFirst("Info: ", ""));

        }
        else if (response.startsWith("Error: ")) {
            System.out.println(response.replaceFirst("Error: ", ""));
        }
        saveMessage+=response;
        saveMessage+=" "+ LocalDateTime.now().toString() ;

        try {
            DataBase.writeFile(DataBase.loggerAddress,saveMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
