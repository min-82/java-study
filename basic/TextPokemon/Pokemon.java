public class Pokemon {
    // 1. 기본 정보
    private String name;
    private PokeType type; // 우리가 만든 Enum 사용

    // 2. 능력치 (스탯)
    // base: 태어날 때 정해진 값 (파일에서 읽어옴, 불변)
    private int baseHp, baseAtk, baseDef, baseSpAtk, baseSpDef, baseSpd;
    
    // current: 배틀 중에 변하는 값 (버프/디버프 반영, 가변)
    private int currentHp; 
    private int atkRank, defRank, spAtkRank, spDefRank, spdRank;

    public void resetBattleStats() {
        atkRank = defRank = spAtkRank = spDefRank = spdRank = 0;
    }

    private double getMultiplier(int rank) {
        if (rank >= 0) return (2.0 + rank) / 2.0;
        else return 2.0 / (2.0 + Math.abs(rank));
    }

    public int getRealAtk() {
        return (int) (baseAtk * getMultiplier(atkRank));
    }
    public int getRealDef() {
        return (int) (baseDef * getMultiplier(defRank));
    }
    public int getRealSpAtk() {
        return (int) (baseSpAtk * getMultiplier(spAtkRank));
    }
    public int getRealSpDef() {
        return (int) (baseSpDef * getMultiplier(spDefRank));
    }
    public int getRealSpd() {
        return (int) (baseSpd * getMultiplier(spdRank));
    }

    // 3. 스킬 (String[] 대신 Skill 객체 배열 사용)
    private Skill[] skills = new Skill[4]; 

    // 생성자: 파일에서 읽어온 데이터를 넣는 곳
    public Pokemon(String name, PokeType type, int hp, int atk, int def, int spAtk, int spDef, int spd) {
        this.name = name;
        this.type = type;
        
        // 베이스 스탯 저장
        this.baseHp = hp + 75;
        this.baseAtk = atk + 20;
        this.baseDef = def + 20;
        this.baseSpAtk = spAtk + 20;
        this.baseSpDef = spDef + 20;
        this.baseSpd = spd + 20;

        // 배틀 시작 시 현재 상태는 베이스 상태와 같음
        this.currentHp = baseHp;
        resetBattleStats(); // 배틀 스탯 초기화
    }

    // --- 핵심 행동 메소드 ---

    // 1. 공격하기: 누구를(enemy), 무엇으로(skill) 때릴지 받아야 함
    public void attack(Pokemon enemy, int skillIndex) {
        Skill skill = skills[skillIndex];
        System.out.println(this.name + "의 " + skill.name + "!");
        
        // 1. 공격력(A)과 방어력(D) 결정
        // 스킬이 '물리(Physical)'면 공격/방어, '특수(Special)'면 특공/특방 사용
        int A, D;
        if (skill.getCategory().equals("PHYSICAL")) { // Skill 클래스에 category 필요
            A = this.getRealAtk();
            D = enemy.getRealDef();
        } else {
            A = this.getRealSpAtk();
            D = enemy.getRealSpDef();
        }

        // 2. 데미지 기본 공식 (레벨 50 고정 가정)
        double damageCalc = ( ( (2.0 * 50 / 5 + 2) * skill.getPower() * A / D ) / 50 ) + 2;

        // 3. 보정치 적용 (자속, 상성, 랜덤)

        double typeMultiplier = PokeType.getEffectiveness(skill.getType(), enemy.getType());

        damageCalc *= typeMultiplier;
    
        // [자속 보정] 내 타입과 스킬 타입이 같으면 1.5배
        if (this.type == skill.getType()) {
            damageCalc *= 1.5;
        }

        // (선택) 메시지 출력
        if (typeMultiplier > 1.0) System.out.println("효과가 굉장했다!");
        else if (typeMultiplier == 0.0) System.out.println("효과가 없는 것 같다...");
        else if (typeMultiplier < 1.0) System.out.println("효과가 별로인 듯하다...");

        // [랜덤 보정] 0.85 ~ 1.00
        damageCalc *= (Math.random() * 0.15 + 0.85);

        int finalDamage = (int) damageCalc;
        enemy.hit(finalDamage);
    }

    // 2. 맞기: 데미지를 받아서 내 체력을 깎음
    public void hit(int damage) {
        this.currentHp -= damage;
        if (this.currentHp < 0) this.currentHp = 0;
        System.out.println(this.name + "의 체력이 " + (100 * this.currentHp / this.baseHp) + "% 남았습니다.");
    }
    
    // 스킬 배우기 (파일 로딩할 때 씀)
    public void setSkill(int index, Skill skill) {
        this.skills[index] = skill;
    }

    // Getter들 (정보 확인용)
    public String getName() { return name; }
    public PokeType getType() {return type;}
    public Skill getSkill(int index) {return skills[index];}
    public int getCurrentHp() { return currentHp; }
    public boolean isFainted() { return currentHp <= 0; } // 기절했는지 확인
}