abstract class Food{
    String name;
    Double price;
    String info;

    abstract void setName(String name);
    abstract void setPrice(double price);
    abstract void setInfo(String info);

    abstract String getName();
    abstract Double getPrice();
    abstract String getInfo();
}

public class MenuItem {
    public static class Burger extends Food{

        public Burger(String name, double price, String info){
            this.name= name;
            this.price= price;
            this.info= info;
        }

        @Override
        public void setName(String name){ this.name= name;}
        public void setPrice(double price){ this.price= price;}
        public void setInfo(String info){ this.info= info;}

        @Override
        public String getName(){ return this.name;}
        public Double getPrice(){ return this.price;}
        public String getInfo(){ return this.info;}
    }

}
