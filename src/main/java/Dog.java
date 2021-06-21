import java.util.ArrayList;

public class Dog extends Animal {
    private static final ArrayList<Dog> dogs;

    static {
        dogs = new ArrayList<>();
    }

    public char DOG = 'd';
    public int counterForDistanceOfWalk = 0;
    int price;
    boolean ded;

    public Dog(String name, int row, int col, int price) {
        super(name, row, col);
        super.setName(name);
        this.row = row;
        this.col = col;
        this.price = price;
        dogs.add(this);
    }

    public static ArrayList<Dog> getDogs() {
        return dogs;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int getCol() {
        return super.getCol();
    }

    @Override
    public void setCol(int col) {
        super.setCol(col);
    }

    @Override
    public int getRow() {
        return super.getRow();
    }

    @Override
    public void setRow(int row) {
        super.setRow(row);
    }

    @Override
    public String getName() {
        return super.getName();
    }


}
