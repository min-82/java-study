import java.util.Scanner; 

public class CarApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car myCar = new Car("Tesla", 100);

        System.out.println("주행을 시작합니다.");

        for (int i = 1; i <= 25; i++) {
            System.out.print(i + "번째 시도: ");
            myCar.accelerate();

            // 1. 직접 접근(myCar.fuel) 대신 Getter(getFuel())를 사용합니다.
            // 이제 외부에서는 이 통로를 통해서만 연료 상태를 확인할 수 있습니다.
            if (myCar.getFuel() <= 5) { 
                System.out.println("⚠️ 연료가 바닥났습니다. 주유를 실행합니다.");
                System.out.print("주유할 연료량: ");
                
                int amount = sc.nextInt();
                
                // 2. 값 변경도 직접 하지 않고 refuel()이라는 검증된 통로를 이용합니다.
                myCar.refuel(amount); 
                System.out.println(""); // 줄바꿈용
            }
        }
        
        myCar.showStatus();
        sc.close();
    }
}