package TextPokemon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Skill flareBlitz = Skill.builder()
                .name("플레어드라이브")
                .type(PokeType.FIRE)
                .power(120)
                .accuracy(100)
                .category("PHYSICAL")
                .myStatChanges(new int[]{0, 0, 0, 0, 0, 0, 0}) 
                .build();

        Skill closeCombat = Skill.builder()
                .name("인파이트")
                .type(PokeType.FIGHTING)
                .power(120)
                .accuracy(100)
                .category("PHYSICAL")
                .myStatChanges(new int[]{0, -1, 0, -1, 0, 0, 0}) 
                .build();

        Skill waterfall = Skill.builder()
                .name("폭포오르기")
                .type(PokeType.WATER)
                .power(80)
                .accuracy(100)
                .category("PHYSICAL")
                .build();

        Skill dragonDance = Skill.builder()
                .name("용의춤")
                .type(PokeType.DRAGON)
                .accuracy(100) 
                .category("STATUS") 
                .myStatChanges(new int[]{1, 0, 0, 0, 1, 0, 0}) 
                .build();

        // ==========================================
        // 2. 포켓몬 생성
        // ==========================================
        Pokemon infernape = new Pokemon("초염몽", PokeType.FIRE, PokeType.FIGHTING, 76, 104, 71, 104, 71, 108);
        Pokemon gyarados = new Pokemon("갸라도스", PokeType.WATER, PokeType.FLYING, 95, 125, 79, 60, 100, 81);

        // 기술 배우기
        infernape.setSkill(0, flareBlitz);
        infernape.setSkill(1, closeCombat);

        gyarados.setSkill(0, waterfall);
        gyarados.setSkill(1, dragonDance);

        // 2. 캐릭터 선택
        Pokemon player = null;
        Pokemon enemy = null;
        
        System.out.println("1. 초염몽 vs 2. 갸라도스");
        int choice = sc.nextInt();
        if (choice == 1) { player = infernape; enemy = gyarados; }
        else { player = gyarados; enemy = infernape; }

        // ==========================================
        // 3. 배틀 매니저 호출 (핵심!)
        // ==========================================
        BattleManager bm = new BattleManager();
        bm.startBattle(player, enemy); // "배틀 시작해!" 한 마디면 끝

        sc.close();
    }
}