package TextPokemon;

import lombok.*;
import java.util.Random;

@Getter
@Setter
public class Pokemon {
    // --- 1. 기본 정보 ---
    private String name;
    private PokeType type1;
    private PokeType type2;

    // --- 2. 능력치 ---
    private int baseHp, baseAtk, baseDef, baseSpAtk, baseSpDef, baseSpd; // 불변
    private int currentHp;
    private int[] statRanks = new int[7]; // 가변 (랭크)

    // --- 3. 스킬 ---
    private Skill[] skills = new Skill[4];
    private static final Random random = new Random();

    // --- 생성자 ---
    public Pokemon(String name, PokeType type1, PokeType type2, int hp, int atk, int def, int spAtk, int spDef, int spd) {
        this.name = name;
        this.type1 = type1;
        this.type2 = (type2 == null) ? PokeType.NONE : type2;

        // 능력치 보정
        this.baseHp = hp * 2 + 110;
        this.baseAtk = atk * 2 + 5;
        this.baseDef = def * 2 + 5;
        this.baseSpAtk = spAtk * 2 + 5;
        this.baseSpDef = spDef * 2 + 5;
        this.baseSpd = spd * 2 + 5;

        this.currentHp = baseHp;
        resetBattleStats();
    }

    public Pokemon(String name, PokeType type1, int hp, int atk, int def, int spAtk, int spDef, int spd) {
        this(name, type1, PokeType.NONE, hp, atk, def, spAtk, spDef, spd);
    }

    // --- 전투 로직 ---

    public void resetBattleStats() {
        this.statRanks = new int[]{0, 0, 0, 0, 0, 0, 0};
    }

    private double getMultiplier(int rankIndex) {
        int rank = statRanks[rankIndex];
        if (rank >= 0) return (2.0 + rank) / 2.0;
        else return 2.0 / (2.0 + Math.abs(rank));
    }

    public int getRealAtk() { return (int) (baseAtk * getMultiplier(0)); }
    public int getRealDef() { return (int) (baseDef * getMultiplier(1)); }
    public int getRealSpAtk() { return (int) (baseSpAtk * getMultiplier(2)); }
    public int getRealSpDef() { return (int) (baseSpDef * getMultiplier(3)); }
    public int getRealSpd() { return (int) (baseSpd * getMultiplier(4)); }

    // --- 핵심: 공격 행동 ---
    public void attack(Pokemon enemy, int skillIndex) {
        Skill skill = skills[skillIndex];
        
        // 1. 명중률 체크
        if (!checkHit(skill, enemy)) {
            System.out.println(this.name + "의 공격이 빗나갔다!");
            return;
        }

        System.out.println(this.name + "의 " + skill.getName() + "!");

        // 2. 연타 및 데미지 계산
        
        if (skill.getCategory().equals("STATUS")){}
        else {
            // 연타 횟수 결정
            int hits = skill.getMinHits() + random.nextInt(skill.getMaxHits() - skill.getMinHits() + 1);
            
            for (int i = 0; i < hits; i++) {
                int damage = calculateDamage(skill, enemy);
                enemy.hit(damage); // 적의 체력만 깎음 (출력 X)
                
                // 연타 메시지 (선택 사항)
                if (hits > 1) System.out.print("."); // 때릴 때마다 점 찍기 효과
                
                if (enemy.isFainted()) break; 
            }
            if (hits > 1) System.out.println(); // 줄바꿈
            if (hits > 1) System.out.println(hits + "번 맞았다!");
        }
        
        // 3. 부가 효과 적용
        if (!enemy.isFainted() || skill.getCategory().equals("STATUS")) {
            applyStatChanges(this, skill.getMyStatChanges());
            applyStatChanges(enemy, skill.getEnemyStatChanges());
        }

        // 4. 결과 출력 (체력바는 여기서 딱 한 번만 보여줌)
        // 말씀하신 대로 baseHp와 currentHp로 퍼센트 계산
        enemy.printHpStatus(); 

    }

    // 데미지 계산
    private int calculateDamage(Skill skill, Pokemon enemy) {
        int A, D;
        if (skill.getCategory().equals("PHYSICAL")) {
            A = this.getRealAtk();
            D = enemy.getRealDef();
        } else {
            A = this.getRealSpAtk();
            D = enemy.getRealSpDef();
        }

        double damage = ((2.0 * 50 / 5 + 2) * skill.getPower() * A / D) / 50 + 2;

        double multiplier = PokeType.getEffectiveness(skill.getType(), enemy.type1);
        if (enemy.type2 != PokeType.NONE) {
            multiplier *= PokeType.getEffectiveness(skill.getType(), enemy.type2);
        }
        damage *= multiplier;

        if (this.type1 == skill.getType() || (this.type2 != PokeType.NONE && this.type2 == skill.getType())) {
            damage *= 1.5;
        }

        boolean isCritical = random.nextInt(16) < 1; 
        if (isCritical) {
            damage *= 1.5;
            System.out.println("급소에 맞았다!");
        }

        damage *= (random.nextDouble() * 0.15 + 0.85);

        if (multiplier > 1.0) System.out.println("효과가 굉장했다!"); // 배율 숫자는 뺌 (깔끔하게)
        else if (multiplier == 0.0) System.out.println("효과가 없는 것 같다...");
        else if (multiplier < 1.0) System.out.println("효과가 별로인 듯하다...");

        return (int) Math.max(1, damage);
    }

    private void applyStatChanges(Pokemon target, int[] changes) {
        String[] statNames = {"공격", "방어", "특수공격", "특수방어", "스피드", "명중률", "회피율"};
        for (int i = 0; i < 7; i++) {
            int change = changes[i];
            if (change == 0) continue;

            target.statRanks[i] += change;
            if (target.statRanks[i] > 6) target.statRanks[i] = 6;
            if (target.statRanks[i] < -6) target.statRanks[i] = -6;

            String vector = (change > 0) ? "올라갔다!" : "떨어졌다!";
            System.out.println(target.getName() + "의 " + statNames[i] + "이(가) " + vector);
        }
    }

    private boolean checkHit(Skill skill, Pokemon enemy) {
        if (skill.getAccuracy() < 0) return true; 
        int hitChance = skill.getAccuracy(); 
        return random.nextInt(100) < hitChance;
    }

    // --- [변경됨] 맞기 로직 ---
    public void hit(int damage) {
        this.currentHp -= damage;
        if (this.currentHp < 0) this.currentHp = 0;
        // 여기서 출력하지 않음! (연타 때 로그 도배 방지)
    }

    // --- [추가됨] 현재 상태 출력 로직 ---
    // 공격이 다 끝난 뒤에 이 함수를 호출해서 보여줌
    public void printHpStatus() {
        int percent = (int)((double)currentHp / baseHp * 100);
        
        // 체력바 그리기 (선택사항: 텍스트로 게이지 표현)
        // 예: [■■■■■□□□□□]
        StringBuilder bar = new StringBuilder("[");
        int barCount = percent / 10; // 10%당 칸 1개
        for(int i=0; i<10; i++) {
            if(i < barCount) bar.append("■");
            else bar.append("□");
        }
        bar.append("]");

        System.out.println("남은 체력: " + currentHp + "/" + baseHp + " " + bar + " (" + percent + "%)");
    }

    public boolean isFainted() {
        return currentHp <= 0;
    }
    
    public void setSkill(int index, Skill skill) {
        if(index >= 0 && index < 4) {
            this.skills[index] = skill;
        }
    }
}