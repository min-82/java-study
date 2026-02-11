import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--- 배틀 테스트 시작 ---");

        Pokemon Test1 = new Pokemon("초염몽", PokeType.FIRE, 76, 104, 71, 104, 71, 108);

        Test1.setSkill(0, new Skill("플레어드라이브", PokeType.FIRE, 120, "PHYSICAL"));
        // [1]번 슬롯: 인파이트 (격투 타입, 물리)
        Test1.setSkill(1, new Skill("인파이트", PokeType.FIGHTING, 120, "PHYSICAL"));
        // [2]번 슬롯: 화염방사 (불꽃 타입, 특수)
        Test1.setSkill(2, new Skill("째려보기", PokeType.NORMAL, 1, "CHANGE"));

        Pokemon Test2 = new Pokemon("타부자고", PokeType.STEEL, 87,60, 95, 133, 91, 84);

        Test2.setSkill(0, new Skill("골드러시", PokeType.STEEL, 120, "SPECIAL"));
        Test2.setSkill(1, new Skill("나쁜음모", PokeType.DARK, 2, "CHANGE"));
        Test2.setSkill(2, new Skill("섀도볼", PokeType.GHOST, 80, "SPECIAL"));

        System.out.println("나는 " + Test1.getName() + "을 출전시켰다!");
        System.out.println("상대는 " + Test2.getName() + "를 출전시켰다!");

        System.out.println();
        System.out.println("나는 무엇을 할까??");
        System.out.println("스킬1: " + Test1.getSkill(0).getName() + " / 타입: " + Test1.getSkill(0).getType() + " / 위력: " + Test1.getSkill(0).getPower() + " / 분류: " + Test1.getSkill(0).getCategory() + "\n강력한 불을 일으켜 적을 블태워버린다");
        System.out.println("스킬2: " + Test1.getSkill(1).getName() + " / 타입: " + Test1.getSkill(0).getType() + " / 위력: " + Test1.getSkill(1).getPower() + " / 분류: " + Test1.getSkill(1).getCategory() + "\n기를 모인 연타로 적을 뭉개버린다");
        System.out.println("스킬3: " + Test1.getSkill(2).getName() + " / 타입: " + Test1.getSkill(2).getType() + " / 위력: " + Test1.getSkill(2).getPower() + " / 분류: " + Test1.getSkill(2).getCategory() + "\n아주 강하게 노려봐 상대의 방어를 조금 떨어뜨린다");

        System.out.println();
        System.out.print("사용할 스킬 번호를 입력하세요: ");
        int num = sc.nextInt();

        Test2.attack(Test1, num-1);

        System.out.println("--- 테스트 종료 ---");

        sc.close();
    }
}