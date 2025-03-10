abstract class Food{
    private String name;
    private Double price;
    private String info;

    public Food(String name, double price, String info){
        setName(name);
        setPrice(price);
        setInfo(info);
    }

    void setName(String name){ this.name= name;};
    void setPrice(double price){ this.price= price;};
    void setInfo(String info){ this.info= info;};

    String getName(){ return this.name;};
    Double getPrice(){ return this.price;};
    String getInfo(){ return this.info;};
}

public class MenuItem {

    public static class Burger extends Food{
        public Burger(String name, double price, String info) {
            super(name, price, info);
        }
    }

    public static class Drink extends Food{
        public Drink(String name, double price, String info) {
            super(name, price, info);
        }
    }

    public static class Dessert extends Food{
        public Dessert(String name, double price, String info) {
            super(name, price, info);
        }
    }
}
