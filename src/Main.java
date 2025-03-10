import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {

        Menu menu= new Menu();

        menu.addList(0, new MenuItem.Burger("버거", 5.5, "그냥버거", 0));
        menu.addList(0, new MenuItem.Burger("햄버거", 5.5, "그냥햄버거", 0));
        menu.addList(1, new MenuItem.Drink("사이다", 1.5, "그냥사이다", 0));
        menu.addList(1, new MenuItem.Drink("검은사이다", 1.5, "콜라", 0));
        menu.addList(2, new MenuItem.Dessert("감튀", 1.5, "탄수화물뭉치", 0));
        menu.addList(2, new MenuItem.Dessert("라지감튀", 2.0, "탄수화물덩어리", 0));

        Kiosk kiosk= new Kiosk(menu);
    }
}