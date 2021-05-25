import java.time.Duration;

public class WaterWell {
    int row;
    int col;
    int amount;
    boolean dry=true;
    boolean full=false;

    public void TimeOfDry()
    {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dry=true;
        full=false;
        System.out.println("WaterWell is dried");

    }
    public  void fillTheBucket()
    {
        if (dry==true)
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
               full=true;
            dry=false;
            System.out.println("WaterWell is filled");

        }

    }


}
