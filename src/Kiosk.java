import java.util.*;
enum Discount{TYPE1(0.1), TYPE2(0.05);
    private final double value;

    Discount(double value){
        this.value= value;
    }

    public double getValue(){
        return value;
    }
}



public class Kiosk {
    Scanner sc = new Scanner(System.in);
    List<Food> bag= new ArrayList<Food>(){};
    boolean flag = true;
    boolean menuFlag= false;
    int menuIndex= 0;
    int token= 0;
    double totalPrice=0.0;


    public void showMenu(){
        System.out.println("대충 메뉴판");
        System.out.println("1.\tBugger\n2.\tDrink\n3.\tDessert\n0.\tExit");
        if (!bag.isEmpty()) {
            System.out.println("대충추가메뉴");
            System.out.println("4.\t장바구니\n5.\t주문취소");
        }
    }

    public double printFood(List<Food> fl){
        double total = 0;
        for (Food f : fl) {
            System.out.println(f.getName() + "\t\t" + f.getPrice() + "\t\t" + f.getInfo() + "\t\t" + f.getAmount() + "개");
            total += f.getPrice()*f.getAmount();
        }
        boolean flag= false;
        return total;
    }

    public void resetBag(List<Food> fl){
        for (Food f : fl) {
            f.setAmount(0);
        }
        fl.clear();
    }

    public Kiosk(Menu m) throws InputMismatchException, ArrayIndexOutOfBoundsException{

        while (flag) {
            try {

                if (menuFlag == false) {
                    showMenu();
                    token = sc.nextInt();
                }

                switch (token) {
                    case (0) -> {
                        if (menuFlag==false) {flag = false;}
                    }

                    // 장바구니
                    case (4) -> {
                        if (bag.isEmpty()) {
                            System.out.println("잘못된 입력입니다.");
                        } else {
                            System.out.println("현재 주문");
                            totalPrice = printFood(bag);
                            System.out.println("총 금액\t" + totalPrice);

                            System.out.println("1. 결재\t2.메뉴판");
                            int checkout = sc.nextInt();
                            token += checkout * 10;
                            menuFlag= true;
                        }
                    }
                    
                    //장바구니-> 결재
                    case (14) -> {
                        if (menuFlag== false){
                            System.out.println("잘못된 입력입니다.");
                            break;
                        }
                        System.out.println("할인정보를 입력해주세요.");
                        System.out.println("1.\tType1 : 10%");
                        System.out.println("2.\tType2 : 5%");
                        System.out.println("0.\t없음");
                                    
                        int dis = sc.nextInt();
                        switch (dis) {
                            case (1) -> totalPrice -= totalPrice * Discount.TYPE1.getValue();
                            case (2) -> totalPrice -= totalPrice * Discount.TYPE2.getValue();
                        }

                        System.out.println("주문 금액은" + totalPrice + "입니다.");
                        resetBag(bag);
                        menuFlag= false;
                    }
                    
                    //장바구니-> 메뉴판
                    case (24) ->{
                        if (menuFlag== false){
                            System.out.println("잘못된 입력입니다.");
                        }
                        menuFlag= false;
                        sc.nextLine();
                    }
                    
                    //장바구니 초기화
                    case (5) -> {
                        if (bag.isEmpty()) {
                            System.out.println("잘못된 입력입니다.");
                        } else {
                            System.out.println("주문을 초기화합니다..");
                            resetBag(bag);
                        }
                    }
                    
                    default -> {
                        List<Food> chosenCategory = m.getCategory(token - 1);
                        m.showList(chosenCategory);
                        menuFlag=true;
                        System.out.println("0을 눌러 이전으로 돌아가세요");

                        menuIndex = sc.nextInt();
                        if (menuIndex==0){break;}

                        Food chosenFood = m.searchList(chosenCategory, menuIndex);
                        System.out.println("선택한 음식은\n" + chosenFood.getName() + "이며 가격은 " + chosenFood.getPrice() + "원입니다.");
                        System.out.println("장바구니에 추가하시겠습니까?\nY/N");

                        char yn = sc.next().charAt(0);
                        yn= Character.toUpperCase(yn);
                        while (yn != 'Y' && yn != 'N'){
                            System.out.println("잘못된 입력입니다.");
                            yn = sc.next().charAt(0);
                            yn= Character.toUpperCase(yn);
                        }
                        if (yn == 'N'){
                            menuFlag= false;
                            break;
                        }

                        System.out.println("수량을 입력해주세요.");
                        while (true) {
                            try {
                                int amount = sc.nextInt();
                                chosenFood.setAmount(chosenFood.getAmount() + amount);
                                break;
                            } catch (InputMismatchException i) {
                                System.out.println("잘못된 입력입니다.");
                            }
                        }

                        boolean didYouBagIt = false;
                        if (!bag.isEmpty()) {
                            for (Food f : bag) {
                                if (chosenFood == f) {
                                    didYouBagIt = true;
                                    break;
                                }
                            }
                        }
                        if (!didYouBagIt) {
                            bag.add(chosenFood);
                        }
                        menuFlag= false;
                        System.out.println("현재 장바구니에 " + chosenFood.getName() + "이/가 " + chosenFood.getAmount() + "개 있습니다.");
                    }
                }
            } catch (InputMismatchException | IndexOutOfBoundsException  a) {
                System.out.println("잘못된 입력입니다.");
                sc.nextLine();
            }
        }
    }
}