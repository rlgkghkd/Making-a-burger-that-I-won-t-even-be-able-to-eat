import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private List<MenuItem.Burger> burgerList= new ArrayList<MenuItem.Burger>();
    private List<MenuItem.Drink> drinkList= new ArrayList<MenuItem.Drink>();
    private List<MenuItem.Dessert> dessertList= new ArrayList<MenuItem.Dessert>();
    private List<List> menuItems= Arrays.asList(burgerList, drinkList, dessertList);

    public void addList(int index, Food food){
        menuItems.get(index).add(food);
    }

    public List getCategory (int index) {
        return menuItems.get(index);
    }

    public void showList(List<Food> foodList){
        int index=1;
        for (Food f: foodList){
            System.out.println(index+".\t"+ f.getName()+"\t\t"+f.getPrice()+"\t\t"+f.getInfo());
            index++;
        }
        System.out.println("0.\t이전메뉴");
    }

    public Food searchList(List<Food> foodList, int i){
        Food chosenFood= foodList.get(i-1);
        return chosenFood;
    }
}
