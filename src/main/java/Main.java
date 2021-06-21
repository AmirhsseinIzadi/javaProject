import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            DataBase.dataBaseInitialization();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       Thread saveData= new Thread(()->{
           outer:
            while (true){
                try {

                for(User user : User.getUsers()) {
                    try {
                        DataBase.saveUserInfo(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break outer;
                    }
                }
            }
            catch (Exception e){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }}
        }) ;
        saveData.setDaemon(true);
        saveData.start();
        ctrl ctrl1 = new ctrl();
        ctrl1.start();


    }
}


