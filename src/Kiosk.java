import java.util.Arrays;
import java.util.List;

public class Kiosk {
    private MenuItem.Burger burger= new MenuItem.Burger("버거  ", 5.5, "버거");
    private MenuItem.Burger hamburger= new MenuItem.Burger("햄버거", 6.0, "햄버거");
    private MenuItem.Burger hamHamBurger= new MenuItem.Burger("햄햄버거", 6.5, "햄햄버거");
    private MenuItem.Burger hamboogi= new MenuItem.Burger("햄부기", 6.5, "햄부기");
    private List<Food> burgerList = Arrays.asList(burger, hamburger, hamHamBurger, hamboogi);

    public void showList(){
        int index=1;
        for (Food f: burgerList){
            System.out.println(index+".\t"+ f.getName()+"\t"+f.getPrice()+"\t"+f.getInfo());
            index++;
        }
        System.out.println("0.\texit");
    }

    public void searchList(int i){
        Food chosenBurger= burgerList.get(i-1);
        System.out.println("선택한 버거는 "+chosenBurger.getName());
        System.out.println("버거의 가격은 "+chosenBurger.getPrice());
        System.out.println("버거의 설명은 "+chosenBurger.getInfo()+ "입니다.");
    }
}

