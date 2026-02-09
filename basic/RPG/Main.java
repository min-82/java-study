import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Job[] party = new Job[2];
        for (int i = 0; i < party.length; i++) {
            System.out.print("1번: 전사 / 2번: 마법사 \n원하시는 직업 번호를 입력하세요: ");
            int num = sc.nextInt();
            System.out.print((i + 1) + "번 캐릭터 이름: ");
            String name = sc.next();
            if(num == 1)
                party[i] = new Warrior(name);
            else if(num == 2)
                party[i] = new Mage(name);
        }

        System.out.println("-----Start Attack!!-----");

        for (Job j : party) {
            j.attack(); // 모든 직업의 공통 기능
            // "너 혹시 Mountable 자격증(인터페이스)이 있니?"라고 물어봄
            if (j instanceof Mountable) {
                // 있다면, 잠시 Mountable 타입으로 변신(형변환)해서 ride() 실행
                ((Mountable) j).ride();
            } else {
                System.out.println(j.getName() + "님은 탈것을 이용할 수 없습니다.");
            }
            System.out.println();
        }

        sc.close();
    }
}
