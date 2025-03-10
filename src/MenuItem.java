abstract class Food{
    private String name;
    private Double price;
    private String info;
    private int amount;

    public Food(String name, double price, String info, int amount){
        setName(name);
        setPrice(price);
        setInfo(info);
        setAmount(amount);
    }

    void setName(String name){ this.name= name;}
    void setPrice(double price){ this.price= price;}
    void setInfo(String info){ this.info= info;}
    void setAmount(int amount){ this.amount= amount;}

    String getName(){ return this.name;}
    Double getPrice(){ return this.price;}
    String getInfo(){ return this.info;}
    int getAmount(){ return this.amount;}
}

public class MenuItem {

    public static class Burger extends Food{
        public Burger(String name, double price, String info, int amount) {
            super(name, price, info, amount);
        }
    }

    public static class Drink extends Food{
        public Drink(String name, double price, String info, int amount) {
            super(name, price, info, amount);
        }
    }

    public static class Dessert extends Food{
        public Dessert(String name, double price, String info, int amount) {
            super(name, price, info, amount);
        }
    }
}
