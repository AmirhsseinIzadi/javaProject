import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Mill {
    final int priceOfBuild = 150;
    int row;
    int col;
    int priceOfUpgrade;

    public static void MillWork() {
        Warehouse.amountOfEgg();
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        ArrayList<Warehouse> eggs = Warehouse.getEggs();
        if (eggs.size() >= 1) {
            eggs.remove(0);
            warehouses.remove(Product.getProduct("egg"));

            Random random = new Random();
            int row = random.nextInt(1) + 1;
            int col = random.nextInt(2) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("flour", row, col, 40);
        } else {
            print("Error: you dont have enough egg");
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
