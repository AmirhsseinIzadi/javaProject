import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctrl {

    Scanner scanner = new Scanner(System.in);
    final char HEN = 'h';
    final char TURKEY = 't';
    final char BUFFALO = 'b';
    final char LION = 'l';
    static char[][] map;

    ctrl() {
        map = new char[16][30];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 30; j++) {
                map[i][j] = ' ';
            }
        }

    }


    void start() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("login")) {
                login();
            } else if (input.equals("signup")) {
                signup();
            }
            checkWaterWellDry(getCommandMatcher(input, "check WaterWell"));
            BuyGrass(getCommandMatcher(input, "buy grass"));
            BuyDomesticAnimal(getCommandMatcher(input, "buy domestic animal"));
            generateWildAnimals(getCommandMatcher(input, "generate"));
            getProduct(getCommandMatcher(input, "get product"));
            BuyDog(getCommandMatcher(input, "buy dog"));
            BuyCat(getCommandMatcher(input, "buy cat"));
            if (input.equals("showMap"))
                showMap();
        }

    }


    public void showMap() {
        int i, j;

        for (j = 0; j < 30 + 17; j++)
            System.out.print('-');

        System.out.println("");

        for (i = 0; i < 16; i++) {
            System.out.print(i + 1 + ":");
            for (j = 0; j < 30; j++)
                System.out.print(map[i][j]);

            System.out.println(":" + (i + 1));


        }

        for (j = 0; j < 30 + 17; j++)
            System.out.print('-');

        System.out.println("");

        walkDomesticAnimals();
        walkWildAnimals();
        generateProduct();
        walkDog();
        walkCat();

    }

    public void BuyDomesticAnimal(Matcher matcher) {

        if (matcher.find()) {

            String str = scanner.nextLine();
            int price;
            price = 0;
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            DomesticAnimal domesticAnimal = new DomesticAnimal(str, row, col, price);
            domesticAnimal.setName(str);
            DomesticAnimal.Sell = true;
            if (str.equalsIgnoreCase("hen")) {
                map[row][col] = HEN;
            } else if (str.equalsIgnoreCase("buffalo")) {
                map[row][col] = BUFFALO;
            } else if (str.equalsIgnoreCase("turkey")) {
                map[row][col] = TURKEY;
            }

        }
    }

    public void walkDomesticAnimals() {

        checkDeadDomesticAnimals();

        int row = 0;
        int col = 0;
        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            row = domesticAnimal.getRow();
            col = domesticAnimal.getCol();
            map[row][col] = ' ';
            Random rand = new Random();
            int x = rand.nextInt(10) + 1;
            if (x % 2 == 0) {
                row++;
                domesticAnimal.setRow(row);
                col++;
                domesticAnimal.setCol(col);
            } else if (x % 3 == 0) {
                row += 2;
                domesticAnimal.setRow(row);
                col--;
                domesticAnimal.setCol(col);
            } else if (x % 5 == 0) {
                row--;
                domesticAnimal.setRow(row);
                col++;
                domesticAnimal.setCol(col);
            } else if (x % 7 == 0) {
                row++;
                domesticAnimal.setRow(row);
                col -= 2;
                domesticAnimal.setCol(col);
            }
            System.out.println(row);
            System.out.println(col);
            if (domesticAnimal.getName().equalsIgnoreCase("hen")) {
                map[row][col] = HEN;
            } else if (domesticAnimal.getName().equalsIgnoreCase("Turkey")) {
                map[row][col] = TURKEY;
            } else if (domesticAnimal.getName().equalsIgnoreCase("Buffalo")) {
                map[row][col] = BUFFALO;
            }
        }

        if (Grass.buyGrass) {

            takeDamage();
        } else {

            int health = 0;
            boolean TimeDamage = false;
            for (DomesticAnimal domesticAnimal : new ArrayList<>(domesticAnimals)) {

                health = domesticAnimal.getHealth();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                health -= 10;
                domesticAnimal.setHealth(health);
                System.out.println(health);
                if (health == 0) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = ' ';
                    domesticAnimals.remove(domesticAnimal);
                }
            }
        }
    }

    public void BuyDog(Matcher matcher) {
        if (matcher.find()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Dog dog = new Dog("Dog", row, col, 100);
            map[row][col] = dog.DOG;
        }

    }

    public void walkDog() {
        int row = 0;
        int col = 0;
        ArrayList<Dog> dogs = Dog.getDogs();
        for (Dog dog : dogs) {
            row = dog.getRow();
            col = dog.getCol();
            map[row][col] = ' ';
            Random random = new Random();
            int x = random.nextInt(10) + 1;
            if (x % 1 == 0) {
                row++;
                dog.setRow(row);
                col++;
                dog.setCol(col);
            }
            if (x % 2 == 0) {
                row += 2;
                dog.setRow(row);
                col++;
                dog.setCol(col);
            }
            if (x % 3 == 0) {
                row--;
                dog.setRow(row);
                dog.setCol(col);
            }
            if (x % 5 == 0) {
                row++;
                dog.setRow(row);
                col += 2;
                dog.setCol(col);
            }
            map[row][col] = dog.DOG;

        }
        checkDeadDog();
    }

    public void checkDeadDog() {
        ArrayList<Dog> dogs = Dog.getDogs();
        ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
        for (Dog dog : new ArrayList<>(dogs)) {
            for (WildAnimal wildAnimal : new ArrayList<>(wildAnimals)) {
                if (wildAnimal.getRow() == dog.getRow() && wildAnimal.getCol() == dog.getCol()) {
                    map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
                    wildAnimals.remove(wildAnimal);
                    map[dog.getRow()][dog.getCol()] = ' ';
                    dogs.remove(dog);
                }
            }
        }

    }

    public void BuyCat(Matcher matcher) {
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        Cat cat = new Cat("Cat", row, col, 150);
        map[row][col] = cat.CAT;
    }

    public void walkCat() {
        int row = 0;
        int col = 0;
        ArrayList<Cat> cats = Cat.getCats();
        for (Cat cat : cats) {
            row = cat.getRow();
            col = cat.getCol();
            map[row][col] = ' ';
            Random random = new Random();
            int x = random.nextInt(10) + 1;
            if (x % 4 == 0) {
                row++;
                cat.setRow(row);
                col--;
                cat.setCol(col);
            }
            if (x % 1 == 0) {
                row += 2;
                cat.setRow(row);
                col++;
                cat.setCol(col);
            }
            if (x % 3 == 0) {
                cat.setRow(row);
                col++;
                cat.setCol(col);
            }
            if (x % 5 == 0) {
                row++;
                cat.setRow(row);
                col++;
                cat.setCol(col);
            }
            map[row][col] = cat.CAT;

        }
        checkDeadCat();
        taskCat();
    }

    public void checkDeadCat() {
        ArrayList<Cat> cats = Cat.getCats();
        ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
        for (Cat cat : new ArrayList<>(cats)) {
            for (WildAnimal wildAnimal : new ArrayList<>(wildAnimals)) {
                if (wildAnimal.getRow() == cat.getRow() && wildAnimal.getCol() == cat.getCol()) {
                    map[cat.getRow()][cat.getCol()] = ' ';
                    cats.remove(cat);
                }
            }
        }

    }

    public void taskCat() {
        ArrayList<Cat> cats = Cat.getCats();
        ArrayList<Product> products = Product.getProducts();
        for (Cat cat : cats) {
            for (Product product : products) {
                if (product.getRow() == cat.getRow() && product.getCol() == cat.getCol()) {
                    map[product.getRow()][product.getCol()] = ' ';
                }
            }


        }

    }

    public void takeDamage() {
        boolean increaseHealthOfDomesticAnimals = false;
        boolean eatgrass = false;
        int health = 0;
        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        for (DomesticAnimal domesticAnimal : new ArrayList<>(domesticAnimals)) {
            ArrayList<Grass> grasses = Grass.getGrasses();
            for (Grass grass : new ArrayList<>(grasses)) {
                if (grass.getRow() == domesticAnimal.getRow() && grass.getCol() == domesticAnimal.getCol()) {
                    if (domesticAnimal.getHealth() <= 50) {
                        grass.eatenGrass = true;
                        eatgrass = true;
                        domesticAnimal.generateProduct = true;
                    }
                } else if (grass.getRow() != domesticAnimal.getRow() && grass.getCol() != domesticAnimal.getCol() && eatgrass == false) {
                    increaseHealthOfDomesticAnimals = true;
                }
            }
            if (increaseHealthOfDomesticAnimals) {
                health = domesticAnimal.getHealth();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                health -= 10;
                domesticAnimal.setHealth(health);
                System.out.println(health);
                if (health == 0) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = ' ';
                    domesticAnimals.remove(domesticAnimal);
                }
            }
            if (eatgrass) {

                health = domesticAnimal.getHealth();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                health += 10;
                domesticAnimal.setHealth(health);
            }
        }
        eatingGrass();
    }

    public void generateProduct() {
        int row = 0;
        int col = 0;
        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            if (domesticAnimal.generateProduct) {
                row = domesticAnimal.getRow();
                col = domesticAnimal.getCol();
                if (domesticAnimal.getName().equalsIgnoreCase("hen")) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Product product = new Product("egg", 15, row + 1, col);
                    product.amountOfEgg++;
                    map[row + 1][col] = product.EGG;
                } else if (domesticAnimal.getName().equalsIgnoreCase("turkey")) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Product product = new Product("feather", 20, row + 1, col);
                    product.amountOfFeather++;
                    map[row + 1][col] = product.FEATHER;
                } else if (domesticAnimal.getName().equalsIgnoreCase("buffalo")) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Product product = new Product("milk", 25, row + 1, col);
                    product.amountOfMilk++;
                    map[row + 1][col] = product.MILK;
                }

            }
        }

    }

    public void getProduct(Matcher matcher) {
        if (matcher.find()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            ArrayList<Product> products = Product.getProducts();
            for (Product product : products) {
                if (row == product.getRow() && product.getCol() == col) {
                    map[product.getRow()][product.getCol()] = ' ';
                } else {
                    System.out.println(" this coordinate is empty of product");
                }
            }

        }
    }

    public void generateWildAnimals(Matcher matcher) {
        if (matcher.find()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int health = 100;
            WildAnimal wildAnimal = new WildAnimal("lion", health, row, col);
            if (DomesticAnimal.Sell) {
                checkDeadDomesticAnimals();
            } else {
                map[row][col] = LION;

            }
        }
    }


    public void checkDeadDomesticAnimals() {
        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
        if (DomesticAnimal.Sell) {
            for (WildAnimal wildAnimal : wildAnimals) {
                for (DomesticAnimal domesticAnimal : new ArrayList<>(domesticAnimals)) {
                    if (wildAnimal.getRow() == domesticAnimal.getRow() && wildAnimal.getCol() == domesticAnimal.getCol()) {
                        map[domesticAnimal.getRow()][domesticAnimal.getCol()] = ' ';
                        domesticAnimals.remove(domesticAnimal);
                        map[wildAnimal.getRow()][domesticAnimal.getCol()] = LION;
                    } else if ((wildAnimal.getRow() != domesticAnimal.getRow() && wildAnimal.row != domesticAnimal.getCol())) {
                        map[wildAnimal.getRow()][wildAnimal.getCol()] = LION;
                    }
                }

            }
        }

    }

    private void checkWaterWellDry(Matcher matcher) {
        if (matcher.find()) {
            WaterWell waterWell = new WaterWell();
            if (waterWell.dry) {
                System.out.println(" WaterWell id dried");
                String str = scanner.nextLine();
                if (str.equalsIgnoreCase("fill the WaterWell")) {
                    waterWell.fillTheBucket();
                }
            } else if (waterWell.full) {
                System.out.println("Bucket id filled");
                if (Grass.buyGrass)
                    waterWell.TimeOfDry();
            }

        }
    }

    private void walkWildAnimals() {
        int row = 0;
        int col = 0;
        ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
        for (WildAnimal wildAnimal : wildAnimals) {
            row = wildAnimal.getRow();
            col = wildAnimal.getCol();
            map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
            Random random = new Random();
            int x = random.nextInt(10) + 1;
            if (x % 2 == 0) {
                row++;
                col++;
                wildAnimal.setRow(row);
                wildAnimal.setCol(col);
            } else if (x % 3 == 0) {
                row += 2;
                wildAnimal.setRow(row);
                wildAnimal.setCol(col);
            } else if (x % 5 == 0) {
                row++;
                wildAnimal.setRow(row);
                col += 2;
                wildAnimal.setCol(col);
            } else if (x % 7 == 0) {
                row += 3;
                wildAnimal.setRow(row);
                col += 2;
                wildAnimal.setCol(col);
            }
            map[row][col] = LION;

        }
    }

    private void BuyGrass(Matcher matcher) {

        WaterWell waterWell = new WaterWell();
        if (matcher.find()) {
            //if (waterWell.full){
            for (int i = 0; i < 5; i++) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                Grass grass = new Grass(row, col);
                map[row][col] = 'g';

            }
        }
        Grass.buyGrass = true;
        //else if(WaterWell.)
        // }
    }

    public void eatingGrass() {
        ArrayList<Grass> grasses = Grass.getGrasses();
        for (Grass grass : new ArrayList<>(grasses)) {
            if (grass.eatenGrass) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                map[grass.getRow()][grass.getCol()] = ' ';
                grasses.remove(grass);
            }
        }
    }

    public void signup() {
        boolean isUsernameExists = false;
        System.out.println("please enter your username");
        String username = scanner.nextLine();
        Matcher matcher = getCommandMatcher(username, "[a-zA-Z0-9]+");
        if (matcher.find()) {
            ArrayList<User> users = User.getUsers();
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("this username is exists");
                    isUsernameExists = true;
                    break;
                }
            }
            if (!isUsernameExists) {
                System.out.println("please enter your password");
                String password = scanner.nextLine();
                Matcher matcher1 = getCommandMatcher(password, "[\\w]+");
                if (matcher1.find()) {
                    System.out.println("your welcome");
                    User user = new User(username, password);
                }
            }
        }
    }

    public void login() {
        System.out.println("please enter your username");
        String username = scanner.nextLine();
        if (User.getUserByUsername(username) == null) {
            System.out.println("user with this username not found");
        } else {
            System.out.println("please enter your password");
            String password = scanner.nextLine();
            User user = User.getUserByUsername(username);
            assert user != null;
            if (!user.getPassWord().equals(password)) {
                System.out.println("this username and password  not match");
            } else {

            }
        }
    }


    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(input);
        return matcher;

    }

}