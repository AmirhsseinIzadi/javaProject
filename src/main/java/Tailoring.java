import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Tailoring {
    int row;
    int col;
    int priceOfBuild = 400;
    int priceOfUpgrade;

    public static void TailoringWork() {
        Warehouse.amountOfCloth();
        ArrayList<Warehouse> cloths = Warehouse.getCloths();
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        if (cloths.size() >= 1) {
            cloths.remove(0);
            warehouses.remove(ProductOfFactory.getProductFactoryByName("cloth"));
            Random random = new Random();
            int row = random.nextInt(12) + 1;
            int col = random.nextInt(14) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("dress", row, col, 100);
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
