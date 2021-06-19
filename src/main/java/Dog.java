import java.util.ArrayList;

public class Dog extends Animal {
     int price;
     boolean ded;
     public char DOG='d';
     public  int counterForDistanceOfWalk=0;
     private  static  ArrayList<Dog>dogs;
     static
     {
         dogs=new ArrayList<>();
     }
    public Dog(String name, int row, int col, int price) {
        super(name, row, col);
        super.setName(name);
        this.row = row;
        this.col = col;
        this.price = price;
        dogs.add(this);
    }
   public static ArrayList<Dog> getDogs()
   {
       return dogs;
   }
    @Override
    public void setRow(int row) {
        super.setRow(row);
    }

    @Override
    public void setCol(int col) {
        super.setCol(col);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int getCol() {
        return super.getCol();
    }

    @Override
    public int getRow() {
        return super.getRow();
    }

    @Override
    public String getName() {
        return super.getName();
    }


}
