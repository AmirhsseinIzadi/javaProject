import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Warehouse {
    public static final int Capacity = 30;
    public static int amountOfEgg = 0;
    public static int amountOfFeather = 0;
    public static int amountOfFlour = 0;
    public static int amountOfMilk =0 ;
    public static int amountOfCloth =0 ;
    public static int numberOfEgg = 0;
    public static int counter_Capacity = 0;
    static boolean full_Capacity = false;
    private static final ArrayList<Warehouse> warehouses;
    private static final ArrayList<Warehouse> eggs;
    private static final ArrayList<Warehouse> feathers;
    private static final ArrayList<Warehouse> milks;
    private static final ArrayList<Warehouse> flours;
    private static final ArrayList<Warehouse> milksPackage;
    private static final ArrayList<Warehouse> cloths;

    static {
        warehouses = new ArrayList<>();
    }

    static {
        eggs = new ArrayList<>();
    }

    static {
        feathers = new ArrayList<>();
    }

    static {
        milks = new ArrayList<>();
    }

    static {
        flours = new ArrayList<>();
    }

    static {
        milksPackage = new ArrayList<>();

    }

    static {
        cloths = new ArrayList<>();
    }

    public String name;
    public int priceOfSell;
    public boolean CancelSellingProduct = false;

    public static ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    public static void amountOfEgg() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Warehouse warehouse : new ArrayList<>(warehouses)) {
            if (warehouse.getName().equalsIgnoreCase("egg")) {
                eggs.add(warehouse);
            }
        }
    }

    public static void amountOfFeather() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Warehouse warehouse : new ArrayList<>(warehouses)) {
            if (warehouse.getName().equalsIgnoreCase("feather")) ;
            {
                feathers.add(warehouse);
            }
        }
    }

    public static void amountOfMilk() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Warehouse warehouse : new ArrayList<>(warehouses)) {
            if (warehouse.getName().equalsIgnoreCase("milk")) {
                milks.add(warehouse);

            }
        }
    }

    public static void amountOfFlour() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Warehouse warehouse : new ArrayList<>(warehouses)) {
            if (warehouse.getName().equalsIgnoreCase("flour")) {
                flours.add(warehouse);

            }
        }
    }

    public static void amountOfMilkPackage() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Warehouse warehouse : new ArrayList<>(warehouses)) {
            if (warehouse.getName().equalsIgnoreCase("millPackage")) ;
            {
                milksPackage.add(warehouse);
            }
        }
    }

    public static void amountOfCloth() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Warehouse warehouse : new ArrayList<>(warehouses)) {
            if (warehouse.getName().equalsIgnoreCase("cloth")) {
                cloths.add(warehouse);
            }
        }
    }

    public static ArrayList<Warehouse> getEggs() {
        return eggs;
    }

    public static ArrayList<Warehouse> getFeathers() {
        return feathers;
    }

    public static ArrayList<Warehouse> getMilks() {
        return milks;
    }

    public static ArrayList<Warehouse> getFlours() {
        return flours;
    }

    public static ArrayList<Warehouse> getMilksPackage() {
        return milksPackage;
    }

    public static ArrayList<Warehouse> getCloths() {
        return cloths;
    }

    public static Warehouse getWarehouseObject(ArrayList<String> bars) {
        if (warehouses.size() == 0) {
            print("Error: Your warehouse is empty !");
        } else {
            for (Warehouse warehouse : warehouses) {

                for (int i = 0; i < bars.size(); i++) {

                    if (warehouse.getName().equalsIgnoreCase(bars.get(i))) {
                        return warehouse;
                    } else if (!warehouse.getName().equalsIgnoreCase(bars.get(i))) {
                        print("You dont have " + bars.get(i));
                    }
                }
            }
        }
        return null;
    }

    public static int amountOfEggInWareHouse() {
        ArrayList<Warehouse> warehouse = Warehouse.getEggs();
        boolean eggInWareHouse = false;
        for (Warehouse warehouse1 : warehouses) {
            if (warehouse1.getName().equalsIgnoreCase("egg")) {
                if (ctrl.inWareHouse) {
                    eggInWareHouse = true;

                    //    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&");
                }
            }
        }
        if (eggInWareHouse) {
            numberOfEgg++;
        }
        ctrl.inWareHouse = false;
        return numberOfEgg;

    }

    public static int amountOfFeatherInWareHouse() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        boolean featherInWarehouse=false ;
        for (Warehouse warehouse1 : warehouses) {
            if (warehouse1.getName().equalsIgnoreCase("feather")) {
                if (ctrl.inWareHouse) {
                    amountOfFeather++;
                }
            }
        }
        if (featherInWarehouse){
            amountOfFeather++ ;
        }
        ctrl.inWareHouse=false ;
        return amountOfFeather;
    }

    public static int amountOfFlourInWarehouse() {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        boolean flourInWareHouse=false ;
        for (Warehouse warehouse1 : warehouses) {
            if (warehouse1.getName().equalsIgnoreCase("flour")) {
                if (ctrl.inWareHouse) {
                    amountOfFlour++;
                }
            }
        }
        if (flourInWareHouse){
            amountOfFlour++ ;
        }
        ctrl.inWareHouse=false;
        return amountOfFlour;
    }
    public static int amountOfMilkINWareHouse(){
        ArrayList<Warehouse> warehouses=Warehouse.getWarehouses();
        boolean milkInWareHouse=false ;
        for (Warehouse warehouse1 : warehouses) {
            if (warehouse1.getName().equalsIgnoreCase("milk")) {
                if (ctrl.inWareHouse) {
                    amountOfMilk++;
                }
            }
        }
        if (milkInWareHouse){
            amountOfMilk++ ;
        }
        ctrl.inWareHouse=false;
        return amountOfMilk;
    }
    public static int amountOfClothesINWareHouse(){
        ArrayList<Warehouse> warehouses=Warehouse.getWarehouses();
        boolean clothesInWareHouse=false ;
        for (Warehouse warehouse1 : warehouses) {
            if (warehouse1.getName().equalsIgnoreCase("cloth")) {
                if (ctrl.inWareHouse) {
                    amountOfCloth++;
                }
            }
        }
        if (clothesInWareHouse){
            amountOfCloth++ ;
        }
        ctrl.inWareHouse=false;
        return amountOfCloth;
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

    public String getName() {
        return name;
    }

    public int getPriceOfSell() {
        return priceOfSell;
    }
}
