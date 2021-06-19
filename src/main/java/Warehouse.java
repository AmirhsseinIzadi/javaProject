import java.util.ArrayList;

public class Warehouse {
    public String name;
    public int priceOfSell;
    public static int amountOfEgg=0;
    public static int amountOfFeather=0;
    public static int amountOfFlour=0;
    public static int numberOfEgg=0 ;
    private static ArrayList<Warehouse> warehouses;
    private static ArrayList<Warehouse> eggs;
    private static ArrayList<Warehouse> feathers;
    private static ArrayList<Warehouse> milks;
    private static ArrayList<Warehouse> flours;
    private static ArrayList<Warehouse> milksPackage;
    private static ArrayList<Warehouse> cloths;
    public static final int Capacity = 30;
    public static int counter_Capacity = 0;
    static boolean full_Capacity = false;
    public boolean CancelSellingProduct = false;


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

    public String getName() {
        return name;
    }

    public int getPriceOfSell() {
        return priceOfSell;
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
        if (warehouses.size()==0){
            System.out.println("Your warehouse is empty !");
        }
        else {
            for (Warehouse warehouse : warehouses) {

                for (int i = 0; i < bars.size(); i++) {

                    if (warehouse.getName().equalsIgnoreCase(bars.get(i))) {
                        return warehouse;
                    } else if (!warehouse.getName().equalsIgnoreCase(bars.get(i))) {
                        System.out.println("You dont have "+bars.get(i));
                    }
                }
            }
        }
        return null;
    }
public static int amountOfEggInWareHouse()
{
    ArrayList<Warehouse>warehouse=Warehouse.getEggs();
    boolean eggInWareHouse=false ;
    for (Warehouse warehouse1 : warehouses)
    {
        if(warehouse1.getName().equalsIgnoreCase("egg"))
        {
            if (ctrl.inWareHouse) {
                eggInWareHouse=true ;

            //    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&");
            }
        }
    }
    if (eggInWareHouse){
        numberOfEgg++ ;
    }
    ctrl.inWareHouse=false ;
    return numberOfEgg ;

}
public static int amountOfFeatherInWreHouse()
{
    ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
    for(Warehouse warehouse : warehouses)
    {
        if(warehouse.getName().equalsIgnoreCase("feather"))
        {
            amountOfFeather++;
        }
    }
    return amountOfFeather;
}
public static int amountOfFlourInWarehouse()
{
    ArrayList<Warehouse>warehouses=Warehouse.getWarehouses();
    for(Warehouse warehouse : warehouses)
    {
        if(warehouse.getName().equalsIgnoreCase("flour"))
        {
            amountOfFlour++;
        }
    }
    return amountOfFlour;
}
}
