import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class MilkPackaging {

    final int priceOfBuild = 400;
    int row;
    int col;
    int priceOfUpgrade;

    public static void MilkPackagingWork() {
        Warehouse.amountOfMilk();
        ArrayList<Warehouse> milks = Warehouse.getMilks();
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        if (milks.size() >= 1) {
            milks.remove(0);
            warehouses.remove(Product.getProduct("milk"));
            Random random = new Random();
            int row = random.nextInt(10) + 1;
            int col = random.nextInt(11) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("milkPackage", row, col, 60);

        } else {
            print("Error: you dont have enough of milk");
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
