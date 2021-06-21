import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Bakery {
    final int priceOfBuild = 250;
    int row;
    int col;
    int priceOfUpgrade;

    public static void BakeryWork() {
        Warehouse.amountOfFlour();
        ArrayList<Warehouse> flours = Warehouse.getFlours();
        if (flours.size() >= 1) {
            flours.remove(0);
            Random random = new Random();
            int row = random.nextInt(10) + 1;
            int col = random.nextInt(15) + 1;
            ProductOfFactory productOfFactory = new ProductOfFactory("bread", row, col, 80);

        } else {
            print("Error: you dont have enough flour ");

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
