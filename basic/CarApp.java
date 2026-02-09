public class CarApp {
    public static void main(String[] args) {
        // 1. 다형성: 부모(Car) 타입 하나로 자식들을 다 묶을 수 있습니다.
        Car car1 = new Truck("봉고", 100, 1000);
        Car car2 = new SportsCar("페라리", 100);

        // 2. 다형성의 위력: 배열 하나에 서로 다른 종류의 차를 담습니다.
        Car[] garage = { car1, car2 };

        System.out.println("=== 전차종 가속 테스트 ===");
        for (Car c : garage) {
            // 3. 여기서 중요! c는 Car 타입이지만, 
            // 실제 객체가 Truck이면 Truck의 accelerate()가,
            // SportsCar면 SportsCar의 accelerate()가 실행됩니다.
            c.accelerate(); 
        }
    }
}