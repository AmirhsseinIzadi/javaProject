import java.io.*;
import java.time.LocalDateTime;

public interface View {

    default void print(String response) {
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
        saveMessage+=" "+LocalDateTime.now().toString() ;

        try {
            DataBase.writeFile(DataBase.loggerAddress,saveMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
