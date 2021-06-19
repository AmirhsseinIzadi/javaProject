import java.util.ArrayList;

public class Truck {
    public static boolean travelOfTruck=false;
    public static boolean successfulTraveling=false;

    public static int counterForTimeOfTraveling=0;
    private static ArrayList<String>bars;
    static {
        bars=new ArrayList<>();
    }
    public static ArrayList<String>getBars()
    {
        return bars;
    }
}
