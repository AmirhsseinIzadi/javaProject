import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class IceCreamFactory {
    final int priceOfBuild = 550;
    int row;
    int col;
    int priceOfUpgrade;

    public static void iceCreamWork() {
        Warehouse.amountOfMilkPackage();
        ArrayList<Warehouse> milksPackage = Warehouse.getMilksPackage();
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        if (milksPackage.size() >= 1) {
            milksPackage.remove(0);
            warehouses.remove(ProductOfFactory.getProductFactoryByName("milkPackage"));

            Random random = new Random();
            int row = random.nextInt(11) + 1;
            int col = random.nextInt(9) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("iceCream", row, col, 120);


        } else {
            print("Error: you dont have enough milkPackage");
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
