public interface View {
    default void print (String response){
        if (response.startsWith("Info: "))
            System.out.println(response.replaceFirst("Info: ",""));
    }
}
