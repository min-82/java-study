public abstract class Job {
    private String name;
    private int level;
    private int hp;

    // 이름만 받는 생성자
    public Job(String name) {
        // "이름, 레벨 1, 체력 100"을 들고 아래에 있는 생성자를 호출합니다.
        this(name, 1, 100); 
    }

    // 기존에 작성하신 모든 값을 받는 생성자
    public Job(String name, int level, int hp) {
        this.name = name;
        this.level = level;
        this.hp = hp;
    }

    public abstract void attack();

    public void rest() {
        this.hp += 10;
        if (this.hp > 100) { // 예: 최대 체력이 100일 때
            this.hp = 100;
        }
        System.out.println(getName() + "님이 휴식합니다. (현재 HP: " + hp + ")");
    }

    public String getName() {return name;}
    public int getLevel() {return level;}
    public int getHp() {return hp;}
}
