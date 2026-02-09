// 1. extends Car: Car 클래스의 모든 데이터와 기능을 물려받습니다.
public class Truck extends Car {
    private int payload; // 트럭만의 새로운 필드: 적재 용량

    // 2. 생성자
    public Truck(String model, int fuel, int payload) {
        // super(): 부모 클래스의 생성자를 호출합니다. 
        // 부모(Car)가 먼저 만들어져야 자식(Truck)이 존재할 수 있기 때문입니다.
        super(model, fuel); 
        this.payload = payload;
    }

    // 3. 트럭만의 새로운 기능
    public void load() {
        System.out.println(getModel() + "에 짐을 실었습니다. (최대 적재량: " + payload + "kg)");
    }

    // 4. 메소드 오버라이딩 (Overriding): 부모의 기능을 재정의
    @Override
    public void accelerate() {
        System.out.println("무거운 트럭이 힘차게 출발합니다!");
        super.accelerate(); // 부모 클래스의 원래 가속 기능도 같이 사용
    }
}