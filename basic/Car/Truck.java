public class Truck extends Car {
    public Truck(String model, int fuel) {
        super(model, fuel);
    }

    // 부모가 "무조건 만들어!"라고 했던 메소드를 구현합니다.
    @Override
    public void accelerate() {
        System.out.println("🚛 트럭이 묵직하게 가속합니다.");
    }
}