abstract class Food{
    abstract void setName(String name);
    abstract void setPrice(double price);
    abstract void setInfo(String info);

    abstract String getName();
    abstract Double getPrice();
    abstract String getInfo();
}

public class MenuItem {
    public static class Burger extends Food{
        private String burgerName;
        private Double burgerPrice;
        private String burgerInfo;

        public Burger(String name, double price, String info){
            this.burgerName= name;
            this.burgerPrice= price;
            this.burgerInfo= info;
        }

        @Override
        public void setName(String name){ this.burgerName= name;}
        public void setPrice(double price){ this.burgerPrice= price;}
        public void setInfo(String info){ this.burgerInfo= info;}

        @Override
        public String getName(){ return this.burgerName;}
        public Double getPrice(){ return this.burgerPrice;}
        public String getInfo(){ return this.burgerInfo;}
    }

}
