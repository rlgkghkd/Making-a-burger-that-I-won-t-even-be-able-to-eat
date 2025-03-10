import java.util.List;
import java.util.Scanner;

public class Kiosk {
    public Kiosk(Menu m){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("대충 메뉴판");
            System.out.println("1.\tBugger\n2.\tDrink\n3.\tDessert");

            int categoryIndex= sc.nextInt();
            switch (categoryIndex) {
                case (0) -> {flag= false; break;}
                default -> {
                    List <Food> chosenCategory = m.getCategory(categoryIndex-1);
                    m.showList(chosenCategory);

                    int menuIndex= sc.nextInt();
                    switch (menuIndex) {
                        case (0) -> {
                            flag = false;
                            break;
                        }
                        default -> {
                            m.searchList(chosenCategory, menuIndex);
                        }
                    }
                }
            }
        }
    }
}