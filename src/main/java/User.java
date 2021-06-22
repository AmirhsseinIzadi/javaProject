import java.util.ArrayList;

public class User {
    public  int amountOfCoin = 500;
    public static User loggedInUser;
    private static ArrayList<User> users;

    static {
        users = new ArrayList<>();
    }

    boolean StageWasCompleted = true;
    boolean TheSecondStageWasCompleted = true;
    boolean TheThirdStageWasCompleted = true;
    boolean TheFourthStageWasCompleted = true;
    boolean TheFifthStageWasCompleted = true;

    private String Username;
    private String PassWord;

    public User(String UserName, String PassWord) {
        this.Username = UserName;
        this.PassWord = PassWord;
        users.add(this);
//        try {
//            DataBase.saveData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getAmountOfCoin() {
        return amountOfCoin;
    }
}
