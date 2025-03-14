import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {

    // 각 객체들을 담을 리스트.
    // 와 리스트를 담을 리스트.
    // 리스트에 접근은 메서드를 통해서 가능하며, burgerList, drinkList, dessertList를 접근하기 위해서는
    // menuItems에서 해당 리스트를 탐색해서 반환받아야 함.
    private List<MenuItem.Burger> burgerList= new ArrayList<MenuItem.Burger>();
    private List<MenuItem.Drink> drinkList= new ArrayList<MenuItem.Drink>();
    private List<MenuItem.Dessert> dessertList= new ArrayList<MenuItem.Dessert>();
    private List<List> menuItems= Arrays.asList(burgerList, drinkList, dessertList);

    public void addList(int index, Food food){
        menuItems.get(index).add(food);
    }

    // menuItem 리스트 접근 메서드.
    // 이 메서드를 통해서만 burgerList, drinkList, dessertList에 접근 가능.
    public List getCategory (int index) {
        return menuItems.get(index);
    }

    // 리스트 출력 메서드.
    public void showList(List<Food> fl){
        int index=1;
        fl.stream().forEach(f-> System.out.println((fl.indexOf(f)+1) +". " + f.getName() + "\t\t" + f.getPrice() + "\t\t" + f.getInfo() + "\t\t" + f.getAmount() + "개"));
        System.out.println("0.\t이전메뉴");
    }

    // 리스트 탐색 메서드
    public Food searchList(List<Food> foodList, int i){
        Food chosenFood= foodList.get(i-1);
        return chosenFood;
    }

    // 리스트에서 요소 삭제 메서드.
    public List<Food> removeListItem(List<Food> foodList, Food f){
        List<Food> removing= foodList.stream().filter(n-> n != f).collect(Collectors.toList());
        return removing;
    }
}
