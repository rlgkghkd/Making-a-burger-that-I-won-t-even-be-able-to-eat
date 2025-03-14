import java.util.*;

// 할인율 Enum
// 키, 밸류 형태.
enum Discount{TYPE1(0.1), TYPE2(0.05);
    private final double value;

    Discount(double value){
        this.value= value;
    }

    public double getValue(){
        return value;
    }
}


// 키오스크
// Menu객체를 받아 실행된다.
public class Kiosk {
    
    // 키오스크 운영에 필요한 지역변수들
    Scanner sc = new Scanner(System.in);
    // 장바구니 리스트.
    // 선택한 객체를 보관한다.
    // 객체를 새로 복제해서 담는것이 아니라, 기존 객체를 그대로 보관함에 유의
    List<Food> bag= new ArrayList<Food>(){};
    // 키오스크 무한반복문에 쓰는 플래스
    // false시 키오스크 동작 종료
    boolean flag = true;
    // 하위메뉴로 이동 확인 플래그
    // 해당 플래그가 true인 경우 키오스크는 상위메뉴 동작을 스킵하고 하위메뉴로만 이동 가능.
    boolean menuFlag= false;
    // 버거, 음료, 디저트 선택 인덱스.
    // menuList에 넣어 선택한 객체를 담은 리스트를 반환하는데 사용
    int menuIndex= 0;
    // 메뉴 선택 토큰
    // 키오스크 스위치문에서 사용
    // 어떤 메뉴 창으로 이동할지 선택
    int token= 0;
    double totalPrice=0.0;


    // 메뉴판 출력 메서드
    // 장바구니에 담긴 요소가 있을 경우 추가메뉴 출력
    public void showMenu(){
        System.out.println("메뉴판");
        System.out.println("1.\tBugger\n2.\tDrink\n3.\tDessert\n0.\tExit");
        if (!bag.isEmpty()) {
            System.out.println("장바구니 메뉴");
            System.out.println("4.\t장바구니\n5.\t주문취소");
        }
    }

    // 선택한 Food 객체 출력 메서드
    public double printFood(List<Food> fl){
        double total = 0;
        fl.stream().forEach(f-> System.out.println((fl.indexOf(f)+1) +". " + f.getName() + "\t\t" + f.getPrice() + "\t\t" + f.getInfo() + "\t\t" + f.getAmount() + "개"));
        boolean flag= false;
        return total;
    }

    // 장바구니 리셋 메서드
    // 장바구니에 담긴 요소는 기존에 생성된 Food 객체들의 원본이므로
    // 각 객체가 가진 amount 필드를 0으로 리셋하고
    // 리스트를 clear 한다.
    public void resetBag(List<Food> fl){
        fl.stream().forEach(food -> food.setAmount(0));
        fl.clear();
    }

    // 키오스크 클래스는 Menu 객체를 받아 해당 객체가 가지고 있는
    // List와 그 요소들을 활용해 동작한다.
    public Kiosk(Menu m){

        while (flag) {

            //그냥 동작 전체를 try-catch에 묶었다.
            try {

                // 하위메뉴로 이동하는 케이스가 아닌 경우에만 메뉴판을 출력한다.
                // menuFlag가 true인 경우 즉시 메뉴 선택 switch문으로 이동한다.
                if (menuFlag == false) {
                    showMenu();
                    token = sc.nextInt();
                }

                // token을 받아 메뉴를 선택한다.
                switch (token) {

                    // 키오스크 종류 케이스
                    case (0) -> {
                        if (menuFlag==false) {flag = false;}
                    }

                    // 장바구니 확인 케이스
                    // 장바구니는 bag리스트에 요소가 있을 때만 접근 가능해야 하므로
                    // if문에서 bag의 비어있음을 확인한다.
                    case (4) -> {
                        if (bag.isEmpty()) {
                            System.out.println("잘못된 입력입니다.");
                        } else {
                            System.out.println("현재 주문");
                            totalPrice = printFood(bag);
                            System.out.println("총 금액\t" + totalPrice);

                            // 장바구니의 하위메뉴 결재, 메뉴판 복귀, 메뉴 삭제 케이스
                            // checkout 입력을 받아 해당 값을 10을 곱해 token에 더한다.
                            System.out.println("1. 결재\t2.메뉴판\t3.메뉴 삭제");
                            int checkout = sc.nextInt();
                            if (checkout <= 3) {
                                token += checkout * 10;
                            }
                            menuFlag= true;
                        }
                    }
                    
                    // 장바구니-> 결재
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
                    
                    // 장바구니-> 메뉴판
                    case (24) ->{
                        if (menuFlag== false){
                            System.out.println("잘못된 입력입니다.");
                        }
                        menuFlag= false;
                        sc.nextLine();
                    }

                    // 장바구니-> 메뉴 삭제
                    case (34) ->{
                        if (menuFlag== false){
                            System.out.println("잘못된 입력입니다.");
                        }
                        printFood(bag);
                        System.out.println("삭제할 메뉴 번호를 고르시오.");
                        // 입력한 값이 잘못된 경우 자동으로 try-catch가 처리한다.
                        // 예외처리 이후 token과 menuFlag는 그대로이므로 다시 해당 메뉴로 복귀하게 된다.
                        int delNum= sc.nextInt();
                        System.out.println(bag.get(delNum-1).getName()+ "을/를 삭제합니다.");
                        bag.get(delNum-1).setAmount(0);
                        bag= m.removeListItem(bag, bag.get(delNum-1));
                        menuFlag= false;
                    }
                    
                    // 장바구니 초기화
                    case (5) -> {
                        if (bag.isEmpty()) {
                            System.out.println("잘못된 입력입니다.");
                        } else {
                            System.out.println("주문을 초기화합니다..");
                            resetBag(bag);
                        }
                    }

                    // 음식 선택 케이스
                    default -> {
                        // 음식 카테고리 선택
                        List<Food> chosenCategory = m.getCategory(token - 1);
                        m.showList(chosenCategory);
                        //선택한 카테고리 출력
                        System.out.println("0을 눌러 이전으로 돌아가세요");

                        // 카테고리에서 음식 선택
                        menuIndex = sc.nextInt();
                        if (menuIndex==0){break;}
                        
                        Food chosenFood = m.searchList(chosenCategory, menuIndex);
                        System.out.println("선택한 음식은\n" + chosenFood.getName() + "이며 가격은 " + chosenFood.getPrice() + "원입니다.");
                        
                        //선택한 음식을 장바구니에 추가 여부 확인
                        //받은 입력은 대문자로 처리
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

                        // 수량 입력
                        // 수량은 음식 객체의 amout 필드를 수정해 저장
                        // 기존 amount는 0으로 초기화, 기존 amount에 입력값을 추가함
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

                        //장바구니에 선택한 음식이 이미 있는지 확인
                        //없는 경우에만 추가
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