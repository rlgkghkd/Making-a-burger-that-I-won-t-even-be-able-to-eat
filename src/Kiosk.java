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
    public Kiosk(Menu m) throws InputMismatchException, ArrayIndexOutOfBoundsException{
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        List<Food> bag= new ArrayList<Food>(){};
        while (flag) {
            try {
                System.out.println("대충 메뉴판");
                System.out.println("1.\tBugger\n2.\tDrink\n3.\tDessert\n0.\tExit");
                if (!bag.isEmpty()) {
                    System.out.println("대충추가메뉴");
                    System.out.println("4.\t장바구니\n5.\t주문취소");
                }

                int categoryIndex = sc.nextInt();
                switch (categoryIndex) {
                    case (0) -> {
                        flag = false;
                        break;
                    }
                    case (4) -> {
                        if (bag.isEmpty()) {
                            System.out.println("잘못된 입력입니다.");
                        } else {
                            System.out.println("현재 주문");
                            double total = 0;
                            for (Food f : bag) {
                                System.out.println(f.getName() + "\t\t" + f.getPrice() + "\t\t" + f.getInfo() + "\t\t" + f.getAmount() + "개");
                                total += f.getPrice()*f.getAmount();
                            }
                            System.out.println("총 금액\t" + total);

                            System.out.println("1. 주문\t2.메뉴판");
                            int checkout = sc.nextInt();
                            boolean checkFlag = true;
                            while (checkFlag) {
                                switch (checkout) {
                                    case (1) -> {
                                        System.out.println("할인정보를 입력해주세요.");
                                        System.out.println("1.\tType1 : 10%");
                                        System.out.println("2.\tType2 : 5%");
                                        System.out.println("0.\t없음");
                                        int dis = sc.nextInt();
                                        switch (dis) {
                                            case (1) -> total -= total * Discount.TYPE1.getValue();
                                            case (2) -> total -= total * Discount.TYPE2.getValue();
                                        }
                                        System.out.println("주문 금액은" + total + "입니다.");
                                        for (Food f : bag) {
                                            f.setAmount(0);
                                        }
                                        bag.clear();
                                        checkFlag = false;
                                    }
                                    case (2) -> {
                                        checkFlag = false;
                                    }
                                }
                            }
                        }
                    }
                    case (5) -> {
                        if (bag.isEmpty()) {
                            System.out.println("잘못된 입력입니다.");
                        } else {
                            System.out.println("주문을 초기화합니다..");
                            for (Food f : bag) {
                                f.setAmount(0);
                            }
                            bag.clear();
                        }

                    }
                    default -> {
                        List<Food> chosenCategory = m.getCategory(categoryIndex - 1);
                        m.showList(chosenCategory);
                        System.out.println("0을 눌러 이전으로 돌아가세요");
                        int menuIndex = sc.nextInt();
                        switch (menuIndex) {
                            case (0) -> {
                                break;
                            }
                            default -> {
                                Food chosenFood = m.searchList(chosenCategory, menuIndex);
                                System.out.println("선택한 음식은\n" + chosenFood.getName() + "이며 가격은 " + chosenFood.getPrice() + "원입니다.");
                                System.out.println("장바구니에 추가하시겠습니까?\nY/N");
                                while (true) {
                                    char yn = sc.next().charAt(0);
                                    if (yn == 'Y' || yn == 'y') {
                                        System.out.println("수량을 입력해주세요.");
                                        int amount = sc.nextInt();
                                        chosenFood.setAmount(chosenFood.getAmount() + amount);

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
                                        System.out.println(chosenFood.getName() + "을/를 " + amount + "개 장바구니에 담았습니다.\n" +
                                                "현재 장바구니에 " + chosenFood.getName() + "이/가 " + chosenFood.getAmount() + "개 있습니다.");
                                        break;
                                    } else if (yn == 'N' || yn == 'n') {
                                        break;
                                    } else {
                                        System.out.println("잘못된 입력입니다.");
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (InputMismatchException | IndexOutOfBoundsException  a) {
                System.out.println("잘못된 입력입니다.");
                sc.nextLine();
            }
        }
    }
}