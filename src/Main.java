import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Kiosk kiosk= new Kiosk();
        boolean flag= true;

        while (flag==true) {
            System.out.println("대충 메뉴판");
            kiosk.showList();

            // 키오스트에 input 전달
            int input = sc.nextInt();
            switch (input) {
                case 0 -> { flag= false;}
                default ->{kiosk.searchList(input);}
            }
        }
    }
}