import java.util.Random;

public class Animal {
    private String name;
    private int health = 100;

    int row;
    int col;

    public Animal(String name, int row, int col) {
        this.name = name;
        setCol(col);
        setRow(row);
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void walk() {

    }


}
