// abstract 키워드를 붙이면 '미완성 설계도'가 됩니다.
public abstract class Car {
    private String model;
    private int fuel;

    public Car(String model, int fuel) {
        this.model = model;
        this.fuel = fuel;
    }

    // [추상 메소드]: "자동차라면 가속은 해야지! 근데 어떻게 할지는 자식들이 정해."
    // 몸통 { } 이 없고 세미콜론 ; 으로 끝납니다.
    public abstract void accelerate();

    // 일반 메소드: 모든 차에 공통인 기능은 미리 만들어둡니다.
    public void brake() {
        System.out.println("멈춥니다.");
    }

    // Getter들...
    public String getModel() { return model; }
    public int getFuel() { return fuel; }
}