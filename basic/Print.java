// 1. 외부 도구(Scanner)를 가져옵니다. (클래스 바깥)
import java.util.Scanner;

// 2. 클래스(설계도)를 만듭니다. 파일명과 동일해야 합니다 (Main.java).
public class Print {

    // 3. 메인 메소드(시작점)를 만듭니다. 프로그램은 여기서부터 시작합니다.
    public static void main(String[] args) {
        
        // 4. 입력받기 위한 도구(Scanner)를 생성합니다.
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 더하기 프로그램 ===");

        // 5. 첫 번째 정수 입력받기
        System.out.print("첫 번째 숫자를 입력하세요: ");
        int num1 = sc.nextInt(); // C언어의 scanf("%d", &num1)과 비슷합니다.

        // 6. 두 번째 정수 입력받기
        System.out.print("두 번째 숫자를 입력하세요: ");
        int num2 = sc.nextInt();

        // 7. 계산하기
        int sum = num1 + num2;

        // 8. 출력하기
        // 문자열 뒤에 +를 붙이면 다른 변수나 숫자를 이어 붙일 수 있습니다.
        System.out.println("두 수의 합은 " + sum + "입니다.");

        // 9. 도구 사용이 끝났으므로 닫아줍니다. (생략 가능하지만 권장됨)
        sc.close();
    }
}