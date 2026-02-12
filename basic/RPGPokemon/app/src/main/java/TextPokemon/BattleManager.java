package TextPokemon;

import java.util.Scanner;
import java.util.Random;

public class BattleManager {
    private final Scanner scanner;
    private final Random random;

    public BattleManager() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // 배틀 시작 메소드 (여기에 포켓몬 두 마리를 던져주면 알아서 싸움)
    public void startBattle(Pokemon player, Pokemon enemy) {
        System.out.println("\n배틀 시작!");
        System.out.println("야생의 [" + enemy.getName() + "]이(가) 나타났다!");
        System.out.println("가라! [" + player.getName() + "]!");

        int turn = 1;

        // 배틀 무한 루프
        while (true) {
            System.out.println("\n====================================");
            System.out.println("[ Turn " + turn + " ]");
            
            // 1. 플레이어 행동 선택
            int playerSkillIdx = getPlayerAction(player);
            if (playerSkillIdx == -1) {
                // -1이면 도망가기 선택한 것 (배틀 종료)
                System.out.println(player.getName() + "은(는) 도망쳤다!");
                break;
            }

            // 2. 적(AI) 행동 선택 (랜덤)
            int enemySkillIdx = random.nextInt(2); // 기술 2개라고 가정

            // 3. 스피드 비교 및 행동 실행 (진짜 포켓몬 룰!)
            // 스피드가 빠른 쪽이 먼저 공격 (동점이면 플레이어 우선)
            boolean playerFirst = player.getRealSpd() >= enemy.getRealSpd();

            if (playerFirst) {
                // [플레이어 선공]
                runTurn(player, enemy, playerSkillIdx);
                // 적이 안 죽었으면 반격
                if (!enemy.isFainted()) {
                    runTurn(enemy, player, enemySkillIdx);
                }
            } else {
                // [적 선공]
                runTurn(enemy, player, enemySkillIdx);
                // 플레이어가 안 죽었으면 반격
                if (!player.isFainted()) {
                    runTurn(player, enemy, playerSkillIdx);
                }
            }

            // 4. 배틀 종료 체크
            if (player.isFainted()) {
                System.out.println("\n" + player.getName() + "은(는) 쓰러졌다... (패배)");
                break;
            } else if (enemy.isFainted()) {
                System.out.println("\n" + enemy.getName() + "은(는) 쓰러졌다! (승리)");
                break;
            }

            turn++;
        }
    }

    // 플레이어 메뉴 입력 받기
    private int getPlayerAction(Pokemon player) {
        while (true) {
            System.out.println("\n[ 메뉴 선택 ]");
            System.out.println("1. 싸운다");
            System.out.println("2. 도망친다");
            System.out.print(">> ");
            
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                // 싸운다 -> 기술 목록 보여주기
                System.out.println("\n[ 기술 선택 ]");
                for (int i = 0; i < 4; i++) {
                    Skill s = player.getSkills()[i];
                    if (s != null) {
                        System.out.println((i + 1) + ". " + s.getName() + " (" + s.getType() + "/" + s.getPp() + ")");
                    }
                }
                System.out.print("기술 번호 >> ");
                int skillIdx = scanner.nextInt() - 1;
                
                if (skillIdx >= 0 && skillIdx < 4 && player.getSkills()[skillIdx] != null) {
                    return skillIdx; // 선택한 기술 인덱스 반환
                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            } else if (choice == 2) {
                return -1; // 도망 신호
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 실제 공격 실행 로직 (중복 제거용 함수)
    private void runTurn(Pokemon attacker, Pokemon defender, int skillIdx) {
        System.out.println("\n[" + attacker.getName() + "]의 행동!");
        attacker.attack(defender, skillIdx);
    }
}