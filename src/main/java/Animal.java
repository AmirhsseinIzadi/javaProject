public class Animal {
    int row;
    int col;
    private String name;
    private int health = 100;

    public Animal(String name, int row, int col) {
        this.name = name;
        setCol(col);
        setRow(row);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void walk() {

    }


}
