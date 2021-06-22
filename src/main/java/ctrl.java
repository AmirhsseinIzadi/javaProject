import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctrl implements View {

    public static boolean inWareHouse = false;
    static char[][] map;
    final char HEN = 'h';
    final char TURKEY = 't';
    final char BUFFALO = 'b';
    final char LION = 'l';
    final char TIGER = 't';
    final char BEAR = 'b';
    public int counterOfTurn = 0;
    public boolean turn = false;
    Scanner scanner = new Scanner(System.in);
    boolean millWasMade = false;
    boolean texTileWasMade = false;
    boolean milkPackagingWadMade = false;
    boolean BakeryWasMade = false;
    boolean iceCreamFactoryWasMade = false;
    boolean tailoringWasMade = false;
    boolean startLevel1 = false;
    boolean startLevel2 = false;
    boolean startLevel3=false ;
    boolean startLevel4=false ;
    boolean startLevel5=false ;
    int Profit = 0;
    boolean quite = false;
    boolean startLevel = false;
    boolean upgradeMill=false ;
    boolean upgradeTexTile=false ;
    boolean upgradeMilkPackaging=false ;
    boolean upgradeBakery =false ;
    boolean upgradeIceCreamFactory=false ;
    boolean upgradeTailoring=false ;



    ctrl() {
        map = new char[6][30];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 30; j++) {
                map[i][j] = ' ';
            }
        }

    }

    void start() {
        System.out.println("Please Enter The Option You Want: 1-login  2-signup ");
        while (!quite) {
            String input1 = scanner.nextLine();
            if (input1.equals("login")) {
                login();

            } else if (input1.equals("signup")) {
                signup();
                //quite=true;
            } else if (input1.equalsIgnoreCase("exit")) {
                return;
            } else {
                print("Error: please enter valid command");

            }
        }
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                return;
            } else {
                if (startLevel) {
                    checkWaterWellDry(getCommandMatcher(input, "check WaterWell"));
                    BuyGrass(getCommandMatcher(input, "plant"));
                    BuyDomesticAnimal(getCommandMatcher(input, "buy ([A-Za-z ]+)$"));
                    getProduct(getCommandMatcher(input, "pickup"));
                    BuyDog(getCommandMatcher(input, "Buy a Dog"));
                    BuyCat(getCommandMatcher(input, "Buy a Cat"));
                    BuildMill(getCommandMatcher(input, "build mill"));
                    BuildTexTile(getCommandMatcher(input, "build textile"));
                    BuildBakery(getCommandMatcher(input, " build bakery"));
                    BuildMilkPackaging(getCommandMatcher(input, "build milkPackaging"));
                    BuildIceCreamFactory(getCommandMatcher(input, "build iceCreamFactory"));
                    BuildTailoring(getCommandMatcher(input, "build tailoring"));
                    convertEggToFlour(getCommandMatcher(input, "work mill"));
                    convertFeatherToCloth(getCommandMatcher(input, "work textile"));
                    convertMilkToMilkPackage(getCommandMatcher(input, "work milkPackaging"));
                    convertFlourToBread(getCommandMatcher(input, "work bakery"));
                    convertClothToDress(getCommandMatcher(input, "work tailoring"));
                    TruckLoad(getCommandMatcher(input, "truckload"));
                    TruckGo(getCommandMatcher(input, "truck go"));
                    TruckUnLoad(getCommandMatcher(input, "truck unload"));
                    convertMilkPackageToIceCream(getCommandMatcher(input, "convert milkPackage to iceCream"));
                    Turn(getCommandMatcher(input, "turn (\\d+)$"));
                    CatchWildAnimal(getCommandMatcher(input, "cage (\\d+) (\\d+)$"));
                    inquiry(getCommandMatcher(input, "inquiry"));
                    UpgradeFactory(getCommandMatcher(input,"upgrade (\\w+)$"));

                } else {
                    print("Error: The Level is locked !");

                }
            }
        }
    }


    public void showMap() {
        walkDomesticAnimals();
        generateWildAnimals();
        walkWildAnimals();
        generateProduct();
        walkDog();
        walkCat();
        ShowInformation();

        int i, j;

        for (j = 0; j < 37; j++)
            System.out.print('-');

        System.out.println("  ");

        for (i = 0; i < 6; i++) {
            System.out.print(":");
            for (j = 0; j < 30; j++)
                System.out.print(map[i][j]);

            System.out.println(" :");


        }

        for (j = 0; j < 37; j++)
            System.out.print('-');

        System.out.println("   ");

    }

    public void UpgradeFactory(Matcher matcher)
    {
        if(matcher.find())
        {
            if(User.loggedInUser.amountOfCoin>=100) {
                if (matcher.group(1).equalsIgnoreCase("mill")) {
                    if (!upgradeMill) {
                        upgradeMill = true;
                        reduceCoin(100);
                    }
                } else if (matcher.group(1).equalsIgnoreCase("textile")) {
                    if (!upgradeTexTile) {
                        upgradeTexTile = true;
                        reduceCoin(100);
                    }
                } else if (matcher.group(1).equalsIgnoreCase("milkPackaging")) {
                    if (!upgradeMilkPackaging) {
                        upgradeMilkPackaging = true;
                        reduceCoin(100);
                    }
                } else if (matcher.group(1).equalsIgnoreCase("bakery")) {
                    if (!upgradeBakery) {
                        upgradeBakery = true;
                        reduceCoin(100);
                    }
                } else if (matcher.group(1).equalsIgnoreCase("iceCreamFactory")) {
                    if (!upgradeIceCreamFactory) {
                        upgradeIceCreamFactory = true;
                        reduceCoin(100);
                    }
                } else if (matcher.group(1).equalsIgnoreCase("tailoring")) {
                    if (!upgradeTailoring) {
                        upgradeTailoring = true;
                        reduceCoin(100);
                    }
                }
            }
            else
            {
                print("Error: you dont have enough money");
            }
        }
    }
    public void inquiry(Matcher matcher) {
        if (matcher.find()) {
            ShowInformation();
        }
    }

    public void BuildMill(Matcher matcher) {
        if (matcher.find()) {
            if (User.loggedInUser.amountOfCoin >= 150) {
                Mill mill = new Mill();
                millWasMade = true;
                reduceCoin(mill.priceOfBuild);
            } else {
                print("Error: you dont have enough money");
            }
        }

    }

    public void countTimeForProductFlour() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase("flour")) {
                if (upgradeMill){
                    productOfFactory.counterForConvertEggToFlour+=2;
                }
                else {
                    productOfFactory.counterForConvertEggToFlour++;
                }
                if (productOfFactory.counterForConvertEggToFlour >= 4) {
                    productOfFactory.showFlour = true;
                }
            }
        }
    }

    public void convertEggToFlour(Matcher matcher) {
        if (matcher.find()) {
            if (millWasMade) {
                Mill.MillWork();
            } else {
                print("Error: please in the first build factory");
            }
        }
    }

    public void ShowFlour() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.showFlour) {
                map[productOfFactory.getRow()][productOfFactory.getCol()] = 'f';
            }

        }
    }

    public void destroyFlourByTime() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
            if (productOfFactory.showFlour) {
                productOfFactory.counterForDestroyFlour++;
                if (productOfFactory.counterForDestroyFlour >= 5) {
                    productOfFactories.remove(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                }
            }
        }
    }

    public void BuildTexTile(Matcher matcher) {
        if (matcher.find()) {
            if (User.loggedInUser.amountOfCoin >= 250) {
                TexTile texTile = new TexTile();
                texTileWasMade = true;
                reduceCoin(texTile.priceOfBuild);
            }
        }
    }

    public void countTimeForProductCloth() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase("cloth")) {
                if (upgradeTexTile){
                    productOfFactory.counterForConvertFeatherToCloth+=2;
                }
                else {
                    productOfFactory.counterForConvertFeatherToCloth++;
                }
                if (productOfFactory.counterForConvertFeatherToCloth >= 5) {
                    productOfFactory.showCloth = true;
                }
            }
        }
    }

    public void convertFeatherToCloth(Matcher matcher) {
        if (matcher.find()) {
            if (texTileWasMade) {
                TexTile.TexTileWork();
            } else {
                print("Error: please in the first build factory");
            }
        }
    }

    public void ShowCloth() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.showCloth) {
                map[productOfFactory.getRow()][productOfFactory.getCol()] = 'c';
            }

        }

    }

    public void destroyClothByTime() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
            if (productOfFactory.showCloth) {
                productOfFactory.counterForDestroyCloth++;
                if (productOfFactory.counterForDestroyCloth >= 5) {
                    productOfFactories.remove(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                }
            }
        }
    }

    public void BuildMilkPackaging(Matcher matcher) {
        if (matcher.find()) {
            if (User.loggedInUser.amountOfCoin >= 400) {
                MilkPackaging milkPackaging = new MilkPackaging();
                milkPackagingWadMade = true;
                reduceCoin(milkPackaging.priceOfBuild);
            }
        }
    }

    public void countTimeForProductMilkPackage() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase("milkPackage")) {
                if (upgradeMilkPackaging){
                    productOfFactory.countForConvertMilkToMilkPackage+=2;
                }
                else {
                    productOfFactory.countForConvertMilkToMilkPackage++;
                }
                if (productOfFactory.countForConvertMilkToMilkPackage >= 6) {
                    productOfFactory.showMilkPackage = true;
                }
            }
        }
    }

    public void convertMilkToMilkPackage(Matcher matcher) {
        if (matcher.find()) {
            if (milkPackagingWadMade) {
                MilkPackaging.MilkPackagingWork();
            } else {
                print("Error: please in the first build factory");
            }
        }
    }

    public void ShowMilkPackage() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.showMilkPackage) {
                map[productOfFactory.getRow()][productOfFactory.getCol()] = 'M';
            }

        }
    }

    public void destroyMilkPackageByTime() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
            if (productOfFactory.showMilkPackage) {
                productOfFactory.counterForDestroyMilkPackage++;
                if (productOfFactory.counterForDestroyMilkPackage >= 5) {
                    productOfFactories.remove(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                }
            }
        }
    }

    public void BuildBakery(Matcher matcher) {
        if (matcher.find()) {
            if (User.loggedInUser.amountOfCoin >= 250) {
                Bakery bakery = new Bakery();
                BakeryWasMade = true;
                reduceCoin(bakery.priceOfBuild);
            } else {
                print("Error: you dont have enough money");
            }

        }
    }

    public void countTimeForProductBread() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase("bread")) {
                if (upgradeBakery){
                    productOfFactory.countForConvertFlourToBread+=2;
                }
                else {
                    productOfFactory.countForConvertFlourToBread++;
                }
                if (productOfFactory.countForConvertFlourToBread >= 5) {
                    productOfFactory.showBread = true;
                }
            }
        }
    }

    public void convertFlourToBread(Matcher matcher) {
        if (matcher.find()) {
            if (BakeryWasMade) {
                Bakery.BakeryWork();
            } else {
                print("Error: please in the first build factory");
            }
        }
    }

    public void ShowBread() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.showBread) {
                map[productOfFactory.getRow()][productOfFactory.getCol()] = 'B';
            }

        }
    }

    public void destroyBreadByTime() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
            if (productOfFactory.showBread) {
                productOfFactory.counterForDestroyBread++;
                if (productOfFactory.counterForDestroyBread >= 6) {
                    productOfFactories.remove(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                }
            }
        }
    }

    public void BuildIceCreamFactory(Matcher matcher) {
        if (matcher.find()) {
            if (User.loggedInUser.amountOfCoin >= 550) {
                IceCreamFactory iceCreamFactory = new IceCreamFactory();
                iceCreamFactoryWasMade = true;
                reduceCoin(iceCreamFactory.priceOfBuild);
            } else {
                print("Error: you dont have enough money");
            }

        }
    }

    public void countTimeForProductIceCream() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase("iceCream")) {
                if (upgradeIceCreamFactory){
                    productOfFactory.countForConvertMilkPackageToIceCream+=2;
                }
                else {
                    productOfFactory.countForConvertMilkPackageToIceCream++;
                }
                if (productOfFactory.countForConvertMilkPackageToIceCream >= 7) {
                    productOfFactory.showIceCream = true;
                }
            }
        }
    }

    public void convertMilkPackageToIceCream(Matcher matcher) {
        if (matcher.find()) {
            if (iceCreamFactoryWasMade) {
                IceCreamFactory.iceCreamWork();
            } else {
                print("Error: please in the first build factory");
            }
        }
    }

    public void ShowIceCream() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.showIceCream) {
                map[productOfFactory.getRow()][productOfFactory.getCol()] = 'i';
            }

        }
    }

    public void destroyIceCreamByTime() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
            if (productOfFactory.showIceCream) {
                productOfFactory.counterForDestroyIceCream++;
                if (productOfFactory.counterForDestroyFlour >= 6) {
                    productOfFactories.remove(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                }
            }
        }
    }

    public void BuildTailoring(Matcher matcher) {
        if (matcher.find()) {
            if (User.loggedInUser.amountOfCoin >= 400) {
                Tailoring tailoring = new Tailoring();
                tailoringWasMade = true;
                reduceCoin(tailoring.priceOfBuild);
            } else {
                print("Error: you dont have enough money");
            }
        }
    }

    public void countTimeForProductDress() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.getName().equalsIgnoreCase("dress")) {
                if (upgradeTailoring){
                    productOfFactory.countForConvertClothToDress+=2;
                }
                else {
                    productOfFactory.countForConvertClothToDress++;
                }
                if (productOfFactory.countForConvertClothToDress >= 6) {
                    productOfFactory.showDress = true;
                }
            }
        }
    }

    public void convertClothToDress(Matcher matcher) {
        if (matcher.find()) {
            if (tailoringWasMade) {
                Tailoring.TailoringWork();
            } else {
                print("Error: please in the first build factory");
            }
        }
    }

    public void ShowDress() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            if (productOfFactory.showDress) {
                map[productOfFactory.getRow()][productOfFactory.getCol()] = 'd';
            }

        }
    }

    public void destroyDressByTime() {
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
            if (productOfFactory.showDress) {
                productOfFactory.counterForDestroyDress++;
                if (productOfFactory.counterForDestroyFlour >= 6) {
                    productOfFactories.remove(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                }
            }
        }
    }

    public void destroyProductByTime() {
        ArrayList<Product> products = Product.getProducts();
        for (Product product : new ArrayList<>(products)) {
            if (product.showProduct) {
                product.counterForDestroy++;
                if (product.counterForDestroy >= 4) {
                    products.remove(product);
                    map[product.getRow()][product.getCol()] = ' ';

                }

            }
        }
    }

    public void BuyDomesticAnimal(Matcher matcher) {

        if (matcher.find()) {

            String str = matcher.group(1);
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int price = 0;
            if (str.equalsIgnoreCase("hen")) {
                price = 100;
                if (User.loggedInUser.amountOfCoin >= 100) {
                    reduceCoin(price);

                    DomesticAnimal domesticAnimal = new DomesticAnimal(str, row, col, price);
                    DomesticAnimal.amountOfHens++;
                    domesticAnimal.setName(str);
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = HEN;

                    DomesticAnimal.Sell = true;
                } else {
                    print("Error: you dont have enough money");
                }
            } else if (str.equalsIgnoreCase("turkey")) {
                price = 200;
                if (User.loggedInUser.amountOfCoin >= 200) {
                    reduceCoin(price);
                    DomesticAnimal domesticAnimal = new DomesticAnimal(str, row, col, price);
                    DomesticAnimal.amountOFTurkey++;
                    domesticAnimal.setName(str);
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = TURKEY;
                    DomesticAnimal.Sell = true;
                } else {
                    print("Error: you dont have enough money");
                }
            } else if (str.equalsIgnoreCase("buffalo")) {
                price = 400;
                if (User.loggedInUser.amountOfCoin >= 400) {
                    reduceCoin(price);
                    DomesticAnimal domesticAnimal = new DomesticAnimal(str, row, col, price);
                    DomesticAnimal.amountOfBuffalo++;
                    domesticAnimal.setName(str);
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = BUFFALO;
                    DomesticAnimal.Sell = true;
                } else {
                    print("Error: you dont have enough money");
                }
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
            for (int i = 0; i < domesticAnimal.counterForDistanceOfWalk; i++) {
                Random random = new Random();
                int rand = random.nextInt(3);
                if (Grass.getGrasses().size() == 0) {
                    if (rand == 0) {
                        col++;
                        if (col <= 0) {
                            col++;
                        } else if (col >= 29) {
                            col--;
                        }

                    } else if (rand == 1) {
                        col--;
                        if (col <= 0) {
                            col++;
                        } else if (col >= 29) {
                            col--;
                        }
                    } else if (rand == 2) {
                        row++;
                        if (row >= 6) {
                            row--;
                        } else if (row <= 0) {
                            row++;

                        }
                    } else if (rand == 3) {
                        row--;
                        if (row >= 6) {
                            row--;
                        } else if (row <= 0) {
                            row++;

                        }
                    }
                } else {
                    Grass grass = getNearestGrass(domesticAnimal);
                    if (domesticAnimal.row < grass.row) {
                        row++;
                        if (row >= 5) {
                            row--;
                        } else if (row <= 0) {
                            row++;

                        }
                    } else if (domesticAnimal.row > grass.row) {
                        row--;
                        if (row >= 5) {
                            row--;
                        } else if (row <= 0) {
                            row++;

                        }
                    } else if (domesticAnimal.col < grass.col) {
                        col++;
                        if (col <= 0) {
                            col++;
                        } else if (col >= 29) {
                            col--;
                        }
                    } else if (domesticAnimal.col > grass.col) {
                        col--;
                        if (col <= 0) {
                            col++;
                        } else if (col >= 29) {
                            col--;
                        }
                    }


                }


            }

            domesticAnimal.setCol(col);
            domesticAnimal.setRow(row);
            domesticAnimal.counterForDistanceOfWalk = 0;
            if (domesticAnimal.getName().equalsIgnoreCase("hen")) {
                map[row][col] = HEN;
            } else if (domesticAnimal.getName().equalsIgnoreCase("Turkey")) {
                map[row][col] = TURKEY;
            } else if (domesticAnimal.getName().equalsIgnoreCase("Buffalo")) {
                map[row][col] = BUFFALO;
            }
            //       System.out.println("**********************************");

        }

        if (Grass.buyGrass) {
            takeDamage();
        } else {

            int health = 0;
            boolean TimeDamage = false;
            for (DomesticAnimal domesticAnimal : new ArrayList<>(domesticAnimals)) {

                health = domesticAnimal.getHealth();
                if (turn) {
                    health -= domesticAnimal.counterForIncreaseHealth * 10;
                    domesticAnimal.counterForIncreaseHealth *= 0;
                    domesticAnimal.setHealth(health);
                    if (health <= 0) {
                        domesticAnimals.remove(domesticAnimal);
                        map[domesticAnimal.getRow()][domesticAnimal.getCol()] = ' ';
                    }
                }
            }

        }
    }


    public void BuyDog(Matcher matcher) {
        if (matcher.find()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (User.loggedInUser.amountOfCoin >= 100) {
                Dog dog = new Dog("Dog", row, col, 100);
                reduceCoin(dog.price);
                map[row][col] = dog.DOG;
            } else {
                print("Error: You dont have enough money");
            }
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
            for (int i = 0; i < dog.counterForDistanceOfWalk; i++) {
                Random random = new Random();
                int rand = random.nextInt(10) + 1;

                if (rand % 2 == 0) {
                    col++;
                    if (col <= 0) {
                        col = 1;
                    } else if (col >= 29) {
                        col = 28;
                    }
                } else if (rand % 5 == 0) {
                    col += 2;
                    row++;
                    if (col <= 0) {
                        col = 1;
                    } else if (col >= 29) {
                        col = 28;
                    } else if (row >= 5) {
                        row = 4;
                    } else if (row <= 0) {
                        row = 1;

                    }
                } else if (rand % 3 == 0) {
                    col += 2;
                    row--;
                    if (col <= 0) {
                        col = 1;
                    } else if (col >= 29) {
                        col = 28;
                    } else if (row >= 5) {
                        row = 4;
                    } else if (row <= 0) {
                        row = 1;

                    }
                } else if (rand % 7 == 0) {
                    col--;
                    if (col <= 0) {
                        col = 1;
                    } else if (col >= 29) {
                        col = 28;
                    } else if (row >= 5) {
                        row = 4;
                    } else if (row <= 0) {
                        row = 1;

                    }

                } else if (rand % 3 == 1) {
                    col -= 2;
                    row++;
                    if (col <= 0) {
                        col = 1;
                    } else if (col >= 29) {
                        col = 28;
                    } else if (row >= 5) {
                        row = 4;
                    } else if (row <= 0) {
                        row = 1;

                    }
                } else if (rand > 8) {
                    col -= 2;
                    row -= 2;
                    if (col <= 0) {
                        col = 1;
                    } else if (col >= 29) {
                        col = 28;
                    } else if (row >= 5) {
                        row = 4;
                    } else if (row <= 0) {
                        row = 1;

                    }

                }
                dog.setRow(row);
                dog.setCol(col);
                dog.counterForDistanceOfWalk = 0;


            }

            map[row][col] = dog.DOG;


            checkDeadDog();
        }

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
        if (matcher.find()) {
            //           System.out.println("ll");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (User.loggedInUser.amountOfCoin >= 150) {
                Cat cat = new Cat("cat", row, col, 150);
                reduceCoin(cat.Price);
                map[cat.getRow()][cat.getCol()] = cat.CAT;

            } else {
                print("Error: you dont have enough money");
            }

        }

    }

    public void walkCat() {
        int row = 0;
        int col = 0;
        ArrayList<Cat> cats = Cat.getCats();
        for (Cat cat : cats) {
            row = cat.getRow();
            col = cat.getCol();

            map[row][col] = ' ';
            for (int i = 0; i < cat.counterForDistanceOfWalk; i++) {
                Random random = new Random();
                int rand = random.nextInt(3);
                if (Product.getProducts().size() == 0) {
                    if (rand == 0) {
                        col++;
                        if (col <= 0) {
                            col++;
                        } else if (col >= 29) {
                            col--;
                        }

                    } else if (rand == 1) {
                        col--;
                        if (col <= 0) {
                            col++;
                        } else if (col >= 29) {
                            col--;
                        }
                    } else if (rand == 2) {
                        row++;
                        if (row >= 6) {
                            row--;
                        } else if (row <= 0) {
                            row++;

                        }
                    } else if (rand == 3) {
                        row--;
                        if (row >= 6) {
                            row--;
                        } else if (row <= 0) {
                            row++;

                        }
                    }
                }
                    else {
                        Product product = getNearestProduct(cat);
                        if (cat.row < product.row) {
                            row++;
                            if (row >= 5) {
                                row = 4;
                            } else if (row <= 0) {
                                row = 1;

                            }
                        } else if (cat.row > product.row) {
                            row--;
                            if (row >= 5) {
                                row = 4;
                            } else if (row <= 0) {
                                row = 1;

                            }
                        } else if (cat.col < product.col) {
                            col++;
                            if (col <= 0) {
                                col = 1;
                            } else if (col >= 29) {
                                col = 28;
                            }
                        } else if (cat.col > product.col) {
                            col--;
                            if (col <= 0) {
                                col = 1;
                            } else if (col >= 29) {
                                col = 28;
                            }
                        }
                    }


                cat.setRow(row);
                cat.setCol(col);
                cat.counterForDistanceOfWalk = 0;

            }
            map[row][col] = cat.CAT;
            checkDeadCat();
            taskCat();
        }
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
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        for (Cat cat : cats) {
            for (Product product : new ArrayList<>(products)) {
                if (product.getRow() == cat.getRow() && product.getCol() == cat.getCol()) {
                    warehouses.add(product);
                    products.remove(product);
                    map[product.getRow()][product.getCol()] = ' ';

                }
            }
        }
        for (Cat cat : cats) {
            for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
                if (productOfFactory.getRow() == cat.getRow() && productOfFactory.getCol() == productOfFactory.getCol()) {
                    warehouses.add(productOfFactory);
                    map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';
                    productOfFactories.remove(productOfFactory);

                }
            }
        }
    }

    public void takeDamage() {
        int health = 0;
        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        for (DomesticAnimal domesticAnimal : new ArrayList<>(domesticAnimals)) {
            ArrayList<Grass> grasses = Grass.getGrasses();
            for (Grass grass : new ArrayList<>(grasses)) {
                if (grass.getRow() == domesticAnimal.getRow() && grass.getCol() == domesticAnimal.getCol()) {
                    if (domesticAnimal.getHealth() <= 50) {
                        grass.eatenGrass = true;
                        domesticAnimal.eatGrass = true;
                        domesticAnimal.generateProduct = true;
                        health = domesticAnimal.getHealth();
                        health = 100;
                        domesticAnimal.setHealth(health);
                    } else if (domesticAnimal.getHealth() > 50) {
                        domesticAnimal.increaseHealthOfDomesticAnimals = true;
                    }
                } else if (grass.getRow() != domesticAnimal.getRow() && grass.getCol() != domesticAnimal.getCol() && domesticAnimal.eatGrass == false) {
                    domesticAnimal.increaseHealthOfDomesticAnimals = true;
                }
            }
            if (domesticAnimal.increaseHealthOfDomesticAnimals) {
                health = domesticAnimal.getHealth();
                health -= domesticAnimal.counterForIncreaseHealth * 10;

                domesticAnimal.counterForIncreaseHealth *= 0;
                domesticAnimal.setHealth(health);
                if (health <= 0) {
                    domesticAnimals.remove(domesticAnimal);
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = ' ';
                }

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
                    if (domesticAnimal.getCounterOfTurnForGenerateProduct >= 2 && domesticAnimal.getCounterOfTurnForGenerateProduct % 2 == 0) {
                        Random random = new Random();
                        int x = random.nextInt(row + 2) + 1;
                        int y = random.nextInt(col + 2) + 1;
                        if (row + 1 >= 5) {
                            row = 4;
                        }
                        if (row + 1 <= 0) {
                            row = 1;
                        }
                        if (col + 1 >= 29) {
                            col = 28;
                        }
                        if (col + 1 <= 0) {
                            col = 1;
                        }
                        Product product = new Product("egg", 15, row + 1, col + 1);
                        //       System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        product.showProduct = true;
                        domesticAnimal.getCounterOfTurnForGenerateProduct = 0;

                    }

                } else if (domesticAnimal.getName().equalsIgnoreCase("turkey")) {
                    System.out.println(domesticAnimal.getCounterOfTurnForGenerateProduct);
                    if (domesticAnimal.getCounterOfTurnForGenerateProduct >= 3 && domesticAnimal.getCounterOfTurnForGenerateProduct % 3 == 0) {
                        Random random = new Random();
                        int x1 = random.nextInt(row + 2) + 2;
                        int y = random.nextInt(col + 1);
                        if (row + 1 >= 5) {
                            row = 4;
                        }
                        if (row + 1 <= 0) {
                            row = 1;
                        }
                        if (col + 1 >= 29) {
                            col = 28;
                        }
                        if (col + 1 <= 0) {
                            col = 1;
                        }
                        Product product = new Product("feather", 20, row + 1, col + 1);
                        product.showProduct = true;
                        domesticAnimal.getCounterOfTurnForGenerateProduct = 0;
                    }
                } else if (domesticAnimal.getName().equalsIgnoreCase("buffalo")) {
                    if (domesticAnimal.getCounterOfTurnForGenerateProduct >= 5 && domesticAnimal.getCounterOfTurnForGenerateProduct % 5 == 0) {
                        Random random = new Random();
                        int x2 = random.nextInt(row + 1) + 1;
                        int y = random.nextInt(col) + 1;
                        if (row + 1 >= 5) {
                            row = 4;
                        }
                        if (row + 1 <= 0) {
                            row = 1;
                        }
                        if (col + 1 >= 29) {
                            col = 28;
                        }
                        if (col + 1 <= 0) {
                            col = 1;
                        }
                        Product product = new Product("milk", 25, row + 1, col + 1);
                        product.showProduct = true;
                        domesticAnimal.getCounterOfTurnForGenerateProduct = 0;
                    }
                }
            }
        }
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            if (domesticAnimal.generateProduct) {
                if (domesticAnimal.getName().equalsIgnoreCase("hen")) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = 'h';
                } else if (domesticAnimal.getName().equalsIgnoreCase("turkey")) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = 't';
                } else if (domesticAnimal.getName().equalsIgnoreCase("buffalo")) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = 'b';
                }
            }
        }
        ShowProduct();
    }

    public void ShowProduct() {
        ArrayList<Product> products = Product.getProducts();
        for (Product product : products) {
            if (product.showProduct) {
                if (product.getName().equalsIgnoreCase("egg")) {
                    map[product.getRow()][product.getCol()] = product.EGG;

                    //     System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
                } else if (product.getName().equalsIgnoreCase("feather")) {
                    map[product.getRow()][product.getCol()] = product.FEATHER;
                } else if (product.getName().equalsIgnoreCase("milk")) {
                    map[product.getRow()][product.getCol()] = product.MILK;
                }
            }

        }
        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            if (domesticAnimal.generateProduct) {
                if (domesticAnimal.getName().equalsIgnoreCase("hen")) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = 'h';
                } else if (domesticAnimal.getName().equalsIgnoreCase("turkey")) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = 't';
                } else if (domesticAnimal.getName().equalsIgnoreCase("buffalo")) {
                    map[domesticAnimal.getRow()][domesticAnimal.getCol()] = 'b';
                }
            }
        }

    }

    public void getProduct(Matcher matcher) {
        if (matcher.find()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!Warehouse.full_Capacity) {
                ArrayList<Product> products = Product.getProducts();
                ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
                ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
                for (Product product : new ArrayList<>(products)) {
                    if (row == product.getRow() && product.getCol() == col) {
                        warehouses.add(product);
                        products.remove(product);
                        inWareHouse = true;
                        map[product.getRow()][product.getCol()] = ' ';
                        Warehouse.counter_Capacity++;
                        print("Info: " + product.getName() + " " + "added in WareHouse");
                    } else {
                        print("Error: this coordinate is empty of product");
                    }
                }
                for (ProductOfFactory productOfFactory : new ArrayList<>(productOfFactories)) {
                    if (row == productOfFactory.getRow() && col == productOfFactory.getCol()) {
                        if (productOfFactory.getName().equalsIgnoreCase("flour") || productOfFactory.getName().equalsIgnoreCase("cloth") || productOfFactory.getName().equalsIgnoreCase("milkPackage")) {
                            Warehouse.counter_Capacity += 2;
                            print("Info: " + productOfFactory.getName() + " " + "added in WareHouse");
                        } else if (productOfFactory.getName().equalsIgnoreCase("bread") || productOfFactory.getName().equalsIgnoreCase("dress") || productOfFactory.getName().equalsIgnoreCase("iceCream")) {
                            Warehouse.counter_Capacity += 4;
                            print("Info: " + productOfFactory.getName() + " " + "added in WareHouse");
                        }
                        warehouses.add(productOfFactory);
                        productOfFactories.remove(productOfFactory);
                        map[productOfFactory.getRow()][productOfFactory.getCol()] = ' ';


                    } else {
                        print("Error: this coordinate is empty of product");
                    }
                }

            }
        }
        checkFullCapacityOfWarehouse(Warehouse.counter_Capacity);
    }

    public void generateWildAnimals() {
        System.out.println(counterOfTurn);
        if (turn) {
            if (counterOfTurn == 2) {

                Random random = new Random();
                int row = random.nextInt(1) + 1;
                int col = random.nextInt(4) + 1;
                WildAnimal wildAnimal = new WildAnimal("lion", 100, row, col);
                WildAnimal wildAnimal1 = new WildAnimal("lion", 100, row + 1, col + 2);
                if (DomesticAnimal.Sell) {
                    checkDeadDomesticAnimals();
                } else {
                    map[wildAnimal.getRow()][wildAnimal.getCol()] = LION;
                    map[wildAnimal.getRow()][wildAnimal.getCol() + 1] = LION;
                }
            } else if (counterOfTurn == 4) {
                Random random = new Random();
                int row = random.nextInt(2) + 1;
                int col = random.nextInt(3) + 1;
                WildAnimal wildAnimal = new WildAnimal("bear", 100, row, col);
                if (DomesticAnimal.Sell) {
                    checkDeadDomesticAnimals();
                } else if (!DomesticAnimal.Sell) {
                    map[wildAnimal.getRow()][wildAnimal.getCol()] = BEAR;
                }
            } else if (counterOfTurn == 8) {
                Random random = new Random();
                int row = random.nextInt(2) + 1;
                int col = random.nextInt(6) + 1;
                WildAnimal wildAnimal = new WildAnimal("tiger", 100, row, col);
                if (DomesticAnimal.Sell) {
                    checkDeadDomesticAnimals();
                } else if (!(DomesticAnimal.Sell)) {
                    map[wildAnimal.getRow()][wildAnimal.getCol()] = TIGER;
                }
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
                        if (wildAnimal.getName().equalsIgnoreCase("lion")) {
                            map[wildAnimal.getRow()][domesticAnimal.getCol()] = LION;
                        } else if (wildAnimal.getName().equalsIgnoreCase("bear")) {
                            map[wildAnimal.getRow()][domesticAnimal.getCol()] = BEAR;
                        } else if (wildAnimal.getName().equalsIgnoreCase("tiger")) {
                            map[wildAnimal.getRow()][domesticAnimal.getCol()] = TIGER;
                        }
                    }
                }

            }
        }
    }


    public void checkFullCapacityOfWarehouse(int counter) {
        if (counter == Warehouse.Capacity) {
            print("Info: Warehouse Capacity is full");
            Warehouse.full_Capacity = true;
        }
    }

    public void TruckLoad(Matcher matcher) {
        if (matcher.find()) {
            String str = scanner.nextLine();
            ArrayList<String> bars = Truck.getBars();
            String[] line = str.split(" ");
            for (int i = 0; i < line.length; i++) {
                bars.add(line[i]);
                if (i >= 9)
                    break;
            }
            print("Info: truck loaded successfully");
        }

    }

    public void TruckUnLoad(Matcher matcher) {
        if (matcher.find()) {
            ArrayList<String> bars = Truck.getBars();
            String str = scanner.nextLine();
            String[] line = str.split(" ");
            for (int i = 0; i < line.length; i++) {
                if (bars.get(i).equalsIgnoreCase(line[i])) {
                    bars.remove(i);

                    if (i >= 9)
                        break;
                }
            }
        }
    }

    public void getProfitCalculation(ArrayList<String> bars) {
        ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
        ArrayList<WildAnimal> WildAnimalsInCage = WildAnimal.getWildAnimalInCage();
        if (!(Warehouse.getWarehouseObject(bars) == null)) {

            Warehouse warehouse = Warehouse.getWarehouseObject(bars);
            Warehouse.getWarehouseObject(bars);
            Profit += warehouse.getPriceOfSell();
            warehouses.remove(warehouse);
            print("Info: your profit is:" + Profit);
        }

        if (!(WildAnimal.getWildAnimalsInCageObject(bars) == null)) {
            WildAnimal wildAnimal = WildAnimal.getWildAnimalsInCageObject(bars);
            if (wildAnimal.getName().equalsIgnoreCase("lion")) {
                Profit += 300;
                WildAnimalsInCage.remove(wildAnimal);
            } else if (wildAnimal.getName().equalsIgnoreCase("bear")) {
                Profit += 400;
                WildAnimalsInCage.remove(wildAnimal);
            } else if (wildAnimal.getName().equalsIgnoreCase("tiger")) {
                Profit += 500;
                WildAnimalsInCage.remove(wildAnimal);
            }
        }


    }

    public void increaseCoin(int profit) {
        User.loggedInUser.amountOfCoin += profit;
        profit = 0;
    }

    public void TruckGo(Matcher matcher) {
        if (matcher.find()) {
            ArrayList<String> bars = Truck.getBars();
            getProfitCalculation(bars);
            bars.clear();
            Truck.travelOfTruck = true;
            //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        }


    }

    public void countTimeForTravelingTruck() {
        if (Truck.travelOfTruck) {
            Truck.counterForTimeOfTraveling++;
            if (Truck.counterForTimeOfTraveling >= 10) {
                increaseCoin(Profit);

            }

        }
    }

    private void walkWildAnimals() {
        int row = 0;
        int col = 0;
        ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
        for (WildAnimal wildAnimal : new ArrayList<>(wildAnimals)) {
            row = wildAnimal.getRow();
            col = wildAnimal.getCol();
            if (!wildAnimal.inCage) {
                if (wildAnimal.getName().equalsIgnoreCase("lion")) {
                    map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
                    for (int i = 0; i < wildAnimal.distanceOfWalk; i++) {
                        col++;
                        if (col >= 29) {
                            col = 29;
                            wildAnimals.remove(wildAnimal);
                            //  System.out.println("kk");
                            return;
                        }

                    }
                    wildAnimal.setRow(row);
                    wildAnimal.setCol(col);
                    wildAnimal.distanceOfWalk = 0;
                    map[row][col] = LION;


                } else if (wildAnimal.getName().equalsIgnoreCase("bear")) {
                    map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
                    for (int i = 0; i < wildAnimal.distanceOfWalk; i++) {
                        col++;
                        if (col >= 29) {
                            col = 29;
                            wildAnimals.remove(wildAnimal);
                            return;
                        }
                    }
                    wildAnimal.setRow(row);
                    wildAnimal.setCol(col);
                    wildAnimal.distanceOfWalk = 0;
                    map[row][col] = BEAR;


                } else if (wildAnimal.getName().equalsIgnoreCase("tiger")) {
                    if (turn) {
                        map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
                        for (int i = 0; i < wildAnimal.distanceOfWalk; i++) {
                            col += 2;
                            if (col >= 29) {
                                col = 29;
                                wildAnimals.remove(wildAnimal);
                                return;
                            }
                        }
                        wildAnimal.setRow(row);
                        wildAnimal.setCol(col);
                        wildAnimal.distanceOfWalk = 0;
                        map[row][col] = TIGER;
                    }
                }
            }
        }
    }

    private void checkWaterWellDry(Matcher matcher) {
        if (matcher.find()) {

            if (WaterWell.dry) {
                print("Info: WaterWell is dried");
                String str = scanner.nextLine();
                if (str.equalsIgnoreCase("well")) {
                    WaterWell.requestForFilling = true;
                    //    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                }
            } else {
                print("Info: WaterWELL is full");
            }
        }
    }

    private void BuyGrass(Matcher matcher) {
        if (matcher.find()) {
            if (WaterWell.full) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Grass grass = new Grass(row, col);
                Grass.buyGrass = true;
                map[row][col] = 'g';
                WaterWell.counter++;
                System.out.println(WaterWell.counter);
                if (WaterWell.counter % 5 == 0 && WaterWell.counter >= 5) {
                    WaterWell.dry = true;
                    WaterWell.full = false;

                }

            } else {
                print("Info: WaterWell is dried");
            }
        }

    }

    public void eatingGrass() {
        ArrayList<Grass> grasses = Grass.getGrasses();
        for (Grass grass : new ArrayList<>(grasses)) {
            if (grass.eatenGrass) {

                grass.counterForDisAppear++;
                if (grass.counterForDisAppear >= 2) {
                    grasses.remove(grass);
                    map[grass.getRow()][grass.getCol()] = ' ';
                }
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
                    print("Info: this username already exists");
                    isUsernameExists = true;
                    break;
                }
            }
            if (!isUsernameExists) {
                System.out.println("please enter your password");
                String password = scanner.nextLine();
                Matcher matcher1 = getCommandMatcher(password, "[\\w]+");
                if (matcher1.find()) {
                    User user = new User(username, password);
                    User.loggedInUser=user ;
                    System.out.println("please enter ths option you want  1-start 1   2-logout   3-setting");
                    String str = scanner.nextLine();
                    if (str.equalsIgnoreCase("start 1")) {
                        System.out.println(" Welcome !");
                        print("Info: You entered the game !");
                        startLevel = true;
                        startLevel1 = true;
                        quite = true;
                    } else if (str.equalsIgnoreCase("logout")) {
                        print("Info: You have logout !");
                        return;
                    } else if (str.equalsIgnoreCase("setting")) {

                    } else {
                        print("Error: please enter valid command");
                    }
                }
            }
        }
    }

    public void login() {
        System.out.println("please enter your username");
        String username = scanner.nextLine();
        if (User.getUserByUsername(username) == null) {
            print("Error: user with this username not found");
        } else {
            System.out.println("please enter your password");
            String password = scanner.nextLine();
            User user = User.getUserByUsername(username);
            assert user != null;
            if (!user.getPassWord().equals(password)) {
                print("Error: this username and password  not match");
            } else {
                User.loggedInUser = user;
                System.out.println("please enter the option you want  1-start 1  2-start 2  3-start 3  4-start 4  5-start 5  6-logout   7-setting");
                String str = scanner.nextLine();
                if (str.equalsIgnoreCase("start 1")) {
                    startLevel1 = true;
                    startLevel = true;
                    System.out.println(" Welcome !");
                    print("Info: You entered the game !");
                    quite = true;

                } else if (str.equalsIgnoreCase("start 2")) {
                   // System.out.println("*********************************");
                    if (User.loggedInUser.StageWasCompleted = false) {
                        startLevel2 = true;
                        User.loggedInUser.TheSecondStageWasCompleted = true;
                        System.out.println(" Welcome !");
                        print("Info: You entered the game !");
                        quite = true;
                    }
                    else {
                        print("Error: The Level is locked !") ;

                    }


                    }
                    else if (str.equalsIgnoreCase("start 3")){
                        if (User.loggedInUser.StageWasCompleted = false) {
                            startLevel3 = true;
                            User.loggedInUser.TheThirdStageWasCompleted = true;
                            System.out.println(" Welcome !");
                            print("Info: You entered the game !");
                            quite = true;
                        }
                        else {
                            print("Error: The Level is locked !") ;

                        }
                }
                else if (str.equalsIgnoreCase("start 4")){
                    if (User.loggedInUser.StageWasCompleted = false) {
                        startLevel4 = true;
                        User.loggedInUser.TheFourthStageWasCompleted = true;
                        System.out.println(" Welcome !");
                        print("Info: You entered the game !");
                        quite = true;
                    }
                    else {
                        print("Error: The Level is locked !") ;

                    }
                }
                else if (str.equalsIgnoreCase("start 5")){
                    if (User.loggedInUser.StageWasCompleted = false) {
                        startLevel5 = true;
                        User.loggedInUser.TheFifthStageWasCompleted = true;
                        System.out.println(" Welcome !");
                        print("Info: You entered the game !");
                        quite = true;
                    }
                    else {
                        print("Error: The Level is locked !") ;

                    }
                }
                else if (str.equalsIgnoreCase("logout")) {
                    print("Info: You have logout !");
                    return;
                }


            }
        }
    }

    public void CatchWildAnimal(Matcher matcher) {
        if (matcher.find()) {
            int row = Integer.parseInt(matcher.group(1));
            int col = Integer.parseInt(matcher.group(2));
            ArrayList<Warehouse> warehouses = Warehouse.getWarehouses();
            ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
            ArrayList<WildAnimal> wildAnimalsInCage = WildAnimal.getWildAnimalInCage();

            for (WildAnimal wildAnimal : WildAnimal.getWildAnimals()) {
                if (wildAnimal.getRow() == row && wildAnimal.getCol() == col) {
                    wildAnimal.inCage = true;
                    wildAnimal.countForDie++;
                    if (wildAnimal.getName().equalsIgnoreCase("lion") && wildAnimal.countForDie == 3) {
                        wildAnimalsInCage.add(wildAnimal);
                        wildAnimals.remove(wildAnimal);
                        map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
                    } else if (wildAnimal.getName().equalsIgnoreCase("bear") && wildAnimal.countForDie == 4) {
                        wildAnimalsInCage.add(wildAnimal);
                        wildAnimals.remove(wildAnimal);
                        map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';

                    } else if (wildAnimal.getName().equalsIgnoreCase("tiger") && wildAnimal.countForDie == 5) {
                        wildAnimalsInCage.add(wildAnimal);
                        wildAnimals.remove(wildAnimal);
                        map[wildAnimal.getRow()][wildAnimal.getCol()] = ' ';
                    }
                }

            }

        }
    }

    public void Turn(Matcher matcher) {
        if (matcher.find()) {

            int counter = Integer.parseInt(matcher.group(1));
            for (int i = 0; i < counter; i++) {
                counterOfTurn++;
                ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
                for (DomesticAnimal domesticAnimal : domesticAnimals) {
                    domesticAnimal.counterForIncreaseHealth++;
                    domesticAnimal.counterForDistanceOfWalk++;
                    if (domesticAnimal.generateProduct) {
                        domesticAnimal.getCounterOfTurnForGenerateProduct++;
                    }
                }
                ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
                for (WildAnimal wildAnimal : wildAnimals) {
                    wildAnimal.distanceOfWalk++;
                    if (wildAnimal.countForDie > 0) {
                        wildAnimal.countForDie--;
                    }
                }


                ArrayList<Dog> dogs = Dog.getDogs();
                for (Dog dog : dogs) {
                    dog.counterForDistanceOfWalk++;
                }
                ArrayList<Cat> cats = Cat.getCats();
                for (Cat cat : cats) {
                    cat.counterForDistanceOfWalk++;
                }
                if (WaterWell.requestForFilling) {
                    WaterWell.countTurnForFilling++;
                }
                if (WaterWell.requestForFilling) {
                    WaterWell.countTurnForFilling++;
                    if (WaterWell.countTurnForFilling >= 3 && WaterWell.countTurnForFilling % 3 == 0) {
                        WaterWell.full = true;
                    }
                }
                countTimeForProductFlour();
                countTimeForProductCloth();
                countTimeForProductMilkPackage();
                countTimeForProductBread();
                countTimeForProductIceCream();
                countTimeForProductDress();
                countTimeForTravelingTruck();
                destroyFlourByTime();
                destroyClothByTime();
                destroyProductByTime();
                destroyMilkPackageByTime();
                destroyBreadByTime();
                destroyIceCreamByTime();
                destroyDressByTime();
            }
            ShowFlour();
            ShowCloth();
            ShowMilkPackage();
            ShowBread();
            ShowIceCream();
            ShowDress();
            ShowProduct();
            showMap();
            turn = true;
        } else if (!matcher.find()) {
            turn = false;
        }
    }

    public void ShowInformation() {

        ArrayList<DomesticAnimal> domesticAnimals = DomesticAnimal.getDomesticAnimals();
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            System.out.println("name:" + domesticAnimal.getName() + " " + "row:" + domesticAnimal.getRow() + " " + "col:" + domesticAnimal.getCol() + " " + "health:" + domesticAnimal.getHealth());
        }
        ArrayList<WildAnimal> wildAnimals = WildAnimal.getWildAnimals();
        for (WildAnimal wildAnimal : wildAnimals) {
            System.out.println("name:" + wildAnimal.getName() + " " + "row:" + wildAnimal.getRow() + " " + "col:" + wildAnimal.getCol() + " " + "number of cage for stop:" + wildAnimal.countForDie);
        }
        ArrayList<Product> products = Product.getProducts();
        for (Product product : products) {
            System.out.println("name:" + product.getName() + " " + "row:" + product.getRow() + " " + "col:" + product.getCol());

        }
        ArrayList<ProductOfFactory> productOfFactories = ProductOfFactory.getProductOfFactories();
        for (ProductOfFactory productOfFactory : productOfFactories) {
            System.out.println("name:" + productOfFactory.getName() + " " + "row:" + productOfFactory.getRow() + "" + "col:" + productOfFactory.getCol());
        }
        ArrayList<Cat> cats = Cat.getCats();
        for (Cat cat : cats) {
            System.out.println("name:" + cat.getName() + " " + "row:" + cat.getRow() + " " + " col:" + cat.getCol());
        }
        ArrayList<Dog> dogs = Dog.getDogs();

        for (Dog dog : dogs) {
            System.out.println("name:" + dog.getName() + " " + "row:" + dog.getRow() + " " + " col:" + dog.getCol());
        }
        ArrayList<Grass> grasses = Grass.getGrasses();
        for (Grass grass : grasses) {
            System.out.println("coordinate of grass "+"row:" + grass.getRow() + " " + "col:" + grass.getCol());
        }

        System.out.println("time elapsed of game :" + counterOfTurn);
        if (startLevel1) {
            TaskOfLevel1();
        } else if (startLevel2) {
            TaskOfLevel2();
        }
        else if (startLevel3){
            TaskOfLevel3();
        }
        else if (startLevel4){
            TaskOfLevel4();
        }
        else if (startLevel5){
            TaskOfLevel5();
        }


    }

    public void reduceCoin(int price) {
        User.loggedInUser.amountOfCoin -= price;
//        try {
//            DataBase.saveData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public Product getNearestProduct(Cat cat) {
        double a = 100000000;
        int rowOfProduct = 1000, colOfProduct = 1000;
        for (Product product : Product.getProducts()) {
            if (Math.pow((cat.row - product.row), 2) + Math.pow((cat.col - product.col), 2) <= a) {
                a = Math.pow((cat.row - product.row), 2) + Math.pow((cat.col - product.col), 2);
                rowOfProduct = product.row;
                colOfProduct = product.col;
            }
        }
        return Product.getProductByRowAndColumn(rowOfProduct, colOfProduct);

    }

    public Grass getNearestGrass(DomesticAnimal domesticAnimal) {
        double a = 100000000;
        int rowOfGrass = 1000, colOfGrass = 1000;
        for (Grass grass : Grass.getGrasses()) {
            if (Math.pow((domesticAnimal.row - grass.row), 2) + Math.pow((domesticAnimal.col - grass.col), 2) <= a) {
                a = Math.pow((domesticAnimal.row - grass.row), 2) + Math.pow((domesticAnimal.col - grass.col), 2);
                rowOfGrass = grass.row;
                colOfGrass = grass.col;
            }
        }
        return Grass.getGrassByRowAndColumn(rowOfGrass, colOfGrass);

    }

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;

    }

    public void TaskOfLevel1() {
        System.out.println("you should perform the following tasks");
        System.out.println("Coins you have to earn : " + User.loggedInUser.amountOfCoin + "/" + Level.amountOfCoinsForLevel1);
        System.out.println("Eggs you have to earn : " + Warehouse.amountOfEggInWareHouse() + "/" + Level.amountOfEggsForLevel1);
        System.out.println("Number of hens : " + DomesticAnimal.amountOfHens + "/" + Level.amountOfHensForLevel1);
        if (User.loggedInUser.amountOfCoin >= Level.amountOfCoinsForLevel1 && Warehouse.amountOfEggInWareHouse() >= Level.amountOfEggsForLevel1 && DomesticAnimal.amountOfHens >= Level.amountOfHensForLevel1) {
            print("Info: You won !");
            Level.TheFirstStageWasCompleted = true;
            User.loggedInUser.StageWasCompleted = true;
            rewardLeve1();
        }
    }

    public void TaskOfLevel2() {
        if (Level.TheFirstStageWasCompleted) {
            System.out.println("Number of turkeys : " + DomesticAnimal.amountOFTurkey + "/" + Level.amountOfTurkeyForLevel);
            System.out.println("Amount of feather you have to earn : " + Warehouse.amountOfFeatherInWareHouse() + "/" + Level.amountOfFeatherForLevel2);
            System.out.println("Amount of flour you have to earn : " + Warehouse.amountOfFlourInWarehouse() + "/" + Level.amountOfFlourForLevel2);
            if (DomesticAnimal.amountOFTurkey >= Level.amountOfTurkeyForLevel && Warehouse.amountOfFeather >= Level.amountOfFeatherForLevel2 && Warehouse.amountOfFlour >= Level.amountOfFlourForLevel2) {
                print("Info: You won !");
                Level.TheSecondStageWasCompleted = true;
                User.loggedInUser.StageWasCompleted = true;
                rewardLevel2();
            }
        } else {
            print("Error: Sorry you have not completed the first step");

        }

    }
    public void TaskOfLevel3(){
        if (Level.TheSecondStageWasCompleted){
            System.out.println(DomesticAnimal.amountOFTurkey+ "/"+ Level.amountOfTurkeyForLevel3);
            System.out.println(Warehouse.amountOfEggInWareHouse()+"/"+Level.amountOfEggsForLevel3) ;
            System.out.println(Warehouse.amountOfMilkINWareHouse()+"/"+Level.amountOfMilkForLevel3);
            if (DomesticAnimal.amountOFTurkey>=Level.amountOfTurkeyForLevel3 && Warehouse.amountOfMilk>=Level.amountOfMilkForLevel3 && Warehouse.amountOfMilkINWareHouse()>=Level.amountOfEggsForLevel3){
                print("Info: You won !");
                Level.TheThirdStageWasCompleted = true;
                User.loggedInUser.StageWasCompleted = true;
                rewardLevel3();
            }
        } else {
            print("Error: Sorry you have not completed the second step");

        }

    }
    public void TaskOfLevel4(){
        if (Level.TheThirdStageWasCompleted){
            System.out.println(DomesticAnimal.amountOfBuffalo+ "/" + Level.amountOfBuffaloForLevel4);
            System.out.println(Warehouse.amountOfClothesINWareHouse() + "/" + Level.amountOfClothsForLevel4);
            System.out.println(Warehouse.amountOfFeatherInWareHouse()+ "/" + Level.amountOfFeatherForLevel4) ;
            if (DomesticAnimal.amountOfBuffalo>=Level.amountOfBuffaloForLevel4 && Warehouse.amountOfCloth>=Level.amountOfClothsForLevel4 && Warehouse.amountOfFeatherInWareHouse()>=Level.amountOfFeatherForLevel4){
                print("Info: You won !");
                Level.TheFourthStageWasCompleted = true;
                User.loggedInUser.StageWasCompleted = true;
                rewardLevel4();
            }
        } else {
            print("Error: Sorry you have not completed the third step");

        }

    }
    public void TaskOfLevel5(){
        if (Level.TheFourthStageWasCompleted){
            System.out.println(DomesticAnimal.amountOfBuffalo+ "/" + Level.amountOfBuffaloForLevel5);
            System.out.println(User.loggedInUser.amountOfCoin+ "/" + Level.amountOfCoinsForLevel5);
            System.out.println(DomesticAnimal.amountOfHens+ "/" + Level.amountOfHenForLevel5) ;
            if (DomesticAnimal.amountOfBuffalo>=Level.amountOfBuffaloForLevel5 && User.loggedInUser.amountOfCoin>=Level.amountOfCoinsForLevel5 && DomesticAnimal.amountOfHens>=Level.amountOfHenForLevel5){
                print("Info: You won !");
                Level.TheFifthStageWasCompleted = true;
                User.loggedInUser.StageWasCompleted = true;
                rewardLevel5();
            }
        } else {
            print("Error: Sorry you have not completed the third step");

        }

    }




    public void rewardLeve1() {
        if (Level.TheFirstStageWasCompleted) {
            if (counterOfTurn <= 90) {
                User.loggedInUser.amountOfCoin += 400;
            }
        }
    }

    public void rewardLevel2() {
        if (Level.TheSecondStageWasCompleted) {
            if (counterOfTurn <= 120) {
                User.loggedInUser.amountOfCoin += 500;
            }
        }
    }
    public void rewardLevel3() {
        if (Level.TheThirdStageWasCompleted) {
            if (counterOfTurn <= 150) {
                User.loggedInUser.amountOfCoin += 600;
            }
        }
    }
    public void rewardLevel4() {
        if (Level.TheFourthStageWasCompleted) {
            if (counterOfTurn <= 180) {
                User.loggedInUser.amountOfCoin += 700;
            }
        }
    }
    public void rewardLevel5() {
        if (Level.TheFifthStageWasCompleted) {
            if (counterOfTurn <= 210) {
                User.loggedInUser.amountOfCoin += 800;
            }
        }
    }

}