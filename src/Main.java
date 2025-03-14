import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {

        // 메뉴객체, 색성될 메뉴들을 담고있음
        // MenuItem에 추상클래스 Food와 이를 상속한 Burger, Drink, Dessert 클래스가 있으며
        // 이를 구현한 객체들을 Menu에 있는 burgerList, drinkList, dessertList에 담음
        // Menu객체를 Kiosk에 넘겨주면 Kiosk가 Menu를 가지고 동작함.
        Menu menu= new Menu();

        menu.addList(0, new MenuItem.Burger("버거 ", 5.5, "그냥버거", 0));
        menu.addList(0, new MenuItem.Burger("햄버거", 5.5, "그냥햄버거", 0));
        menu.addList(1, new MenuItem.Drink("사이다", 1.5, "그냥사이다", 0));
        menu.addList(1, new MenuItem.Drink("검은사이다", 1.5, "콜라", 0));
        menu.addList(2, new MenuItem.Dessert("감튀 ", 1.5, "탄수화물뭉치", 0));
        menu.addList(2, new MenuItem.Dessert("라지감튀", 2.0, "탄수화물덩어리", 0));

        Kiosk kiosk= new Kiosk(menu);
    }
}