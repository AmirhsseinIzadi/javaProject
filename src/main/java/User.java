import java.util.ArrayList;

public class User {
    private static ArrayList<User> users;
    private String Username;
    private String PassWord;
    public static int amountOfCoin=500;
    public static User loggedInUser ;
    public int coin =500 ;
    boolean StageWasCompleted=false;
    boolean TheSecondStageWasCompleted=false;

    static {
        users = new ArrayList<>();
    }

    public User(String UserName, String PassWord) {
        this.Username = UserName;
        this.PassWord = PassWord;
        users.add(this);
        try {
            DataBase.saveData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getUsername() {
        return Username;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public int getAmountOfCoin() {
        return amountOfCoin;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }
}