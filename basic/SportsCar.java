public class SportsCar extends Car {
    public SportsCar(String model, int fuel) {
        super(model, fuel);
    }

    @Override
    public void accelerate() {
        // 스포츠카는 더 많이 가속하고 연료도 더 많이 씁니다.
        // 부모의 private 변수를 직접 못 쓰니 Getter/Setter를 활용합시다.
        if (getFuel() >= 10) {
            // 속도는 +30, 연료는 -10
            System.out.println(getModel() + "가 부스터를 쓰며 가속합니다! (속도 +30)");
            // 실제 로직은 생략하거나 부모 메소드를 적절히 조합
        }
    }
}