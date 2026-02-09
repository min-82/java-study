// SportsCar를 상속받음 (Car -> SportsCar -> Lamborghini)
public class Lamborghini extends SportsCar {
    
    public Lamborghini(String model, int fuel) {
        // 부모인 SportsCar의 생성자를 호출
        super(model, fuel); 
    }

    // 람보르기니만의 특수 기능
    public void wingDoor() {
        System.out.println("🚀 " + getModel() + "의 걸윙 도어가 멋지게 열립니다.");
    }

    // 부모나 할아버지의 기능을 입맛에 맞게 또 바꿀 수 있음 (오버라이딩)
    @Override
    public void accelerate() {
        System.out.println("⚡ 제로백 2.8초! 시트에 몸이 파묻힙니다.");
        super.accelerate(); // SportsCar의 가속 로직 실행
    }
}