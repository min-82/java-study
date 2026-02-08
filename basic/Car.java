public class Car {
    // 1. 모든 필드를 private으로 숨겨서 외부 직접 접근을 차단합니다.
    private String model;
    private int speed;
    private int fuel;

    // 생성자: 객체 생성 시 데이터를 안전하게 초기화
    public Car(String model) {
        this(model, 100); 
    }

    public Car(String model, int fuel) {
        this.model = model;
        // 연료 설정 시 검증 로직을 거치도록 합니다.
        if (fuel < 0) this.fuel = 0;
        else this.fuel = fuel;
    }

    // 2. 가속 기능 (내부에서 fuel과 speed를 안전하게 변경)
    public void accelerate() {
        if (fuel >= 5) {
            speed += 10;
            fuel -= 5; 
            System.out.println(model + " 가속! 속도: " + speed + "km/h, 남은 연료: " + fuel);
        } else {
            System.out.println("⚠️ " + model + "의 연료가 부족합니다!");
        }
    }

    public void brake() {
        speed -= 10;
        if (speed < 0) speed = 0;
        System.out.println(model + " 감속. 현재 속도: " + speed + "km/h");
    }

    // 3. 연료 주입 (Setter 역할: 잘못된 값이 들어오는 것을 방지)
    public void refuel(int amount) {
        if (amount < 0) {
            System.out.println("⚠️ 오류: 음수 양의 연료는 주입할 수 없습니다.");
            return;
        }
        this.fuel += amount;
        if (this.fuel > 100) this.fuel = 100; // 최대 연료 제한
        System.out.println("연료 주입 완료!! / 현재 연료: " + this.fuel);
    }

    // 4. Getter 메소드: 외부에서 값을 읽고 싶을 때 사용
    public String getModel() { return model; }
    public int getSpeed() { return speed; }
    public int getFuel() { return fuel; }

    public void showStatus() {
        System.out.println("--- [" + model + "] 현재 연료: " + fuel + " / 현재 속도: " + speed + " ---");
    }
}