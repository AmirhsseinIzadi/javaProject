import java.util.ArrayList;

public class Cat {
    private static final ArrayList<Cat> cats;

    static {
        cats = new ArrayList<>();
    }

    public int counterForDistanceOfWalk = 0;
    String name;
    int Price;
    int row;
    int col;
    char CAT = 'c';

    public Cat(String name, int row, int col, int price) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.Price = price;
        cats.add(this);
    }

    public static ArrayList<Cat> getCats() {
        return cats;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
