package TextPokemon;

import java.util.*;

public class Main {
        public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                Random random = new Random();

                // ==========================================
                // 1. 스킬 데이터 생성 (유명한 기술 배치)
                // ==========================================

                // [초염몽] 플레어드라이브 / 인파이트 / 마하펀치 / 번개펀치
                Skill flareBlitz = Skill.builder().name("플레어드라이브").type(PokeType.FIRE).power(120).accuracy(100)
                                .category("PHYSICAL").myStatChanges(new int[] { 0, 0, 0, 0, 0, 0, 0 }).build();
                Skill closeCombat = Skill.builder().name("인파이트").type(PokeType.FIGHTING).power(120).accuracy(100)
                                .category("PHYSICAL").myStatChanges(new int[] { 0, -1, 0, -1, 0, 0, 0 }).build();
                Skill machPunch = Skill.builder().name("마하펀치").type(PokeType.FIGHTING).power(40).accuracy(100)
                                .category("PHYSICAL").priority(1).build();
                Skill thunderPunch = Skill.builder().name("번개펀치").type(PokeType.ELECTRIC).power(75).accuracy(100)
                                .category("PHYSICAL").build();

                // [갸라도스] 폭포오르기 / 얼음정수리 / 지진 / 용의춤
                Skill waterfall = Skill.builder().name("폭포오르기").type(PokeType.WATER).power(80).accuracy(100)
                                .category("PHYSICAL").build();
                Skill iceFang = Skill.builder().name("얼음엄니").type(PokeType.ICE).power(65).accuracy(95)
                                .category("PHYSICAL").build();
                Skill earthquake = Skill.builder().name("지진").type(PokeType.GROUND).power(100).accuracy(100)
                                .category("PHYSICAL").build();
                Skill dragonDance = Skill.builder().name("용의춤").type(PokeType.DRAGON).accuracy(100).category("STATUS")
                                .myStatChanges(new int[] { 1, 0, 0, 0, 1, 0, 0 }).build();

                // [망나뇽] 신속 / 드래곤크루 / 지진 / 날개쉬기(회복은 구현 안됐으니 불꽃펀치로 대체)
                Skill extremeSpeed = Skill.builder().name("신속").type(PokeType.NORMAL).power(80).accuracy(100)
                                .category("PHYSICAL").priority(2).build();
                Skill dragonClaw = Skill.builder().name("드래곤크루").type(PokeType.DRAGON).power(80).accuracy(100)
                                .category("PHYSICAL").build();
                Skill firePunch = Skill.builder().name("불꽃펀치").type(PokeType.FIRE).power(75).accuracy(100)
                                .category("PHYSICAL").build();
                // earthquake 재사용

                // [가디안] 문포스 / 사이코키네시스 / 10만볼트 / 명상
                Skill moonblast = Skill.builder().name("문포스").type(PokeType.FAIRY).power(95).accuracy(100)
                                .category("SPECIAL").enemyStatChanges(new int[] { 0, 0, -1, 0, 0, 0, 0 }).build();
                Skill psychic = Skill.builder().name("사이코키네시스").type(PokeType.PSYCHIC).power(90).accuracy(100)
                                .category("SPECIAL").enemyStatChanges(new int[] { 0, 0, 0, -1, 0, 0, 0 }).build();
                Skill thunderbolt = Skill.builder().name("10만볼트").type(PokeType.ELECTRIC).power(90).accuracy(100)
                                .category("SPECIAL").build();
                Skill calmMind = Skill.builder().name("명상").type(PokeType.PSYCHIC).accuracy(100).category("STATUS")
                                .myStatChanges(new int[] { 0, 0, 1, 1, 0, 0, 0 }).build();

                // [팬텀] 섀도볼 / 오물폭탄 / 기합구슬 / 길동무(구현안됨 -> 10만볼트 대체)
                Skill shadowBall = Skill.builder().name("섀도볼").type(PokeType.GHOST).power(80).accuracy(100)
                                .category("SPECIAL").enemyStatChanges(new int[] { 0, 0, 0, -1, 0, 0, 0 }).build();
                Skill sludgeBomb = Skill.builder().name("오물폭탄").type(PokeType.POISON).power(90).accuracy(100)
                                .category("SPECIAL").build();
                Skill focusBlast = Skill.builder().name("기합구슬").type(PokeType.FIGHTING).power(120).accuracy(70)
                                .category("SPECIAL").build();
                // thunderbolt 재사용

                // [한카리아스] 지진 / 역린 / 스톤샤워 / 칼춤
                Skill outrage = Skill.builder().name("역린").type(PokeType.DRAGON).power(120).accuracy(100)
                                .category("PHYSICAL").build();
                Skill rockSlide = Skill.builder().name("스톤샤워").type(PokeType.ROCK).power(75).accuracy(90)
                                .category("PHYSICAL").build();
                Skill swordsDance = Skill.builder().name("칼춤").type(PokeType.NORMAL).accuracy(100).category("STATUS")
                                .myStatChanges(new int[] { 2, 0, 0, 0, 0, 0, 0 }).build();
                // earthquake 재사용

                // ==========================================
                // 2. 포켓몬 풀 생성 (6마리)
                // ==========================================
                List<Pokemon> allPokemon = new ArrayList<>();

                // 1. 초염몽
                Pokemon p1 = new Pokemon("초염몽", PokeType.FIRE, PokeType.FIGHTING, 76, 104, 71, 104, 71, 108);
                p1.setSkill(0, flareBlitz);
                p1.setSkill(1, closeCombat);
                p1.setSkill(2, machPunch);
                p1.setSkill(3, thunderPunch);
                allPokemon.add(p1);

                // 2. 갸라도스
                Pokemon p2 = new Pokemon("갸라도스", PokeType.WATER, PokeType.FLYING, 95, 125, 79, 60, 100, 81);
                p2.setSkill(0, waterfall);
                p2.setSkill(1, iceFang);
                p2.setSkill(2, earthquake);
                p2.setSkill(3, dragonDance);
                allPokemon.add(p2);

                // 3. 망나뇽 (멀티스케일 특성은 아직 없지만 튼튼함)
                Pokemon p3 = new Pokemon("망나뇽", PokeType.DRAGON, PokeType.FLYING, 91, 134, 95, 100, 100, 80);
                p3.setSkill(0, extremeSpeed);
                p3.setSkill(1, dragonClaw);
                p3.setSkill(2, earthquake);
                p3.setSkill(3, firePunch);
                allPokemon.add(p3);

                // 4. 가디안
                Pokemon p4 = new Pokemon("가디안", PokeType.PSYCHIC, PokeType.FAIRY, 68, 65, 65, 125, 115, 80);
                p4.setSkill(0, moonblast);
                p4.setSkill(1, psychic);
                p4.setSkill(2, thunderbolt);
                p4.setSkill(3, calmMind);
                allPokemon.add(p4);

                // 5. 팬텀
                Pokemon p5 = new Pokemon("팬텀", PokeType.GHOST, PokeType.POISON, 60, 65, 60, 130, 75, 110);
                p5.setSkill(0, shadowBall);
                p5.setSkill(1, sludgeBomb);
                p5.setSkill(2, focusBlast);
                p5.setSkill(3, thunderbolt);
                allPokemon.add(p5);

                // 6. 한카리아스
                Pokemon p6 = new Pokemon("한카리아스", PokeType.DRAGON, PokeType.GROUND, 108, 130, 95, 80, 85, 102);
                p6.setSkill(0, earthquake);
                p6.setSkill(1, outrage);
                p6.setSkill(2, rockSlide);
                p6.setSkill(3, swordsDance);
                allPokemon.add(p6);

                // ==========================================
                // 3. 엔트리 선택 (3마리 고르기)
                // ==========================================
                List<Pokemon> myEntry = new ArrayList<>();
                List<Pokemon> enemyEntry = new ArrayList<>();
                Set<Integer> selectedIndices = new HashSet<>(); // 중복 선택 방지용

                System.out.println("==========================================");
                System.out.println("      TEXT POKEMON BATTLE : 3 vs 3      ");
                System.out.println("==========================================");

                // 내 포켓몬 3마리 선택
                for (int i = 0; i < 3; i++) {
                        while (true) {
                                System.out.println("\n[" + (i + 1) + "번째 멤버를 선택하세요]");
                                for (int j = 0; j < allPokemon.size(); j++) {
                                        String marker = selectedIndices.contains(j) ? "[선택됨] " : "";
                                        System.out.println((j + 1) + ". " + marker + allPokemon.get(j).getName());
                                }
                                System.out.print("선택 >> ");

                                try {
                                        int choice = sc.nextInt() - 1;
                                        if (choice >= 0 && choice < allPokemon.size()
                                                        && !selectedIndices.contains(choice)) {
                                                myEntry.add(allPokemon.get(choice));
                                                selectedIndices.add(choice);
                                                System.out.println(
                                                                ">> " + allPokemon.get(choice).getName() + " 선택 완료!");
                                                break;
                                        } else {
                                                System.out.println("[!] 이미 선택했거나 잘못된 번호입니다.");
                                        }
                                } catch (Exception e) {
                                        System.out.println("[!] 숫자를 입력해주세요.");
                                        sc.nextLine(); // 버퍼 비우기
                                }
                        }
                }

                // 남은 포켓몬 중 3마리를 적에게 배정 (랜덤 순서)
                List<Pokemon> remainingPokemon = new ArrayList<>();
                for (int i = 0; i < allPokemon.size(); i++) {
                        if (!selectedIndices.contains(i)) {
                                remainingPokemon.add(allPokemon.get(i));
                        }
                }
                Collections.shuffle(remainingPokemon); // 순서 섞기
                for (int i = 0; i < 3; i++) {
                        enemyEntry.add(remainingPokemon.get(i));
                }

                System.out.println("\n[시스템] 상대의 엔트리가 결정되었습니다.");
                System.out.print("[시스템] 상대 라인업: ");
                for (Pokemon p : enemyEntry)
                        System.out.print(p.getName() + " ");
                System.out.println("\n");

                // ==========================================
                // 4. 3대3 배틀 시작 (BattleManager 호출)
                // ==========================================
                BattleManager bm = new BattleManager();

                // 3vs3 배틀은 '누가 이길 때까지 계속 붙이는' 방식이므로
                // BattleManager에 리스트를 통째로 넘기는 게 좋지만,
                // 현재 구조상 하나씩 꺼내서 붙이는 방식으로 메인에서 루프를 돌리겠습니다.

                int myIndex = 0;
                int enemyIndex = 0;

                System.out.println("============== BATTLE START ==============");

                while (myIndex < 3 && enemyIndex < 3) {
                        Pokemon myPokemon = myEntry.get(myIndex);
                        Pokemon enemyPokemon = enemyEntry.get(enemyIndex);

                        // 1대1 배틀 시작 (이전에 만든 BattleManager 재사용)
                        // 주의: BattleManager는 한 쪽이 죽으면 끝나는 구조임
                        bm.startBattle(myPokemon, enemyPokemon);

                        // 배틀이 끝났음 -> 누군가는 기절했음
                        if (myPokemon.isFainted()) {
                                System.out.println("\n[시스템] " + myPokemon.getName() + " 기절!");
                                myIndex++; // 다음 포켓몬
                                if (myIndex < 3) {
                                        System.out.println(">> 가라! " + myEntry.get(myIndex).getName() + "!");
                                }
                        }

                        if (enemyPokemon.isFainted()) {
                                System.out.println("\n[시스템] 적 " + enemyPokemon.getName() + " 기절!");
                                enemyIndex++; // 적 다음 포켓몬
                                if (enemyIndex < 3) {
                                        System.out.println(">> 상대는 " + enemyEntry.get(enemyIndex).getName()
                                                        + "을(를) 내보냈다!");
                                }
                        }
                }

                // ==========================================
                // 5. 최종 결과
                // ==========================================
                System.out.println("\n==========================================");
                if (myIndex >= 3) {
                        System.out.println("패배했습니다... 눈앞이 캄캄해집니다...");
                } else {
                        System.out.println("승리했습니다! 상금 10,000원을 획득했다!");
                }

                sc.close();
        }
}