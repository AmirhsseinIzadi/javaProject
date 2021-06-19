
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            DataBase.dataBaseInitialization();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//       Thread saveData= new Thread(()->{
//            while (true){
//                for(User user : User.getUsers()){
//                try {
//                    DataBase.saveUserInfo(user);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    break;
//                }
//
//            }}
//        }) ;
//        saveData.setDaemon(true);
//        saveData.start();
        ctrl ctrl1= new ctrl();
        ctrl1.start();


    }


}