import java.util.ArrayList;

public class Cat {
    String name;
    int Price;
    int row;
    int col;
    char CAT='c';
    private static ArrayList<Cat>cats;
    static
    {
        cats=new ArrayList<>();
    }
    public Cat(String name , int row, int col,int price)
    {
        this.name=name;
        this.row=row;
        this.col=col;
        this.Price=price;
        cats.add(this);
    }
    public static ArrayList<Cat> getCats()
    {
        return cats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPrice() {
        return Price;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
