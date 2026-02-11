public class Skill {
    String name;        // 스킬 이름 (예: "몸통박치기")
    PokeType type;      // 스킬 속성 (예: NORMAL)
    int power;          // 위력 (예: 40)
    String category;    // "물리", "특수", "변화"

    public Skill(String name, PokeType type, int power, String category) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.category = category;
    }

    // Getter들 (정보 확인용)
    public String getName() { return name; }
    public PokeType getType() {return type;}
    public int getPower() {return power;}
    public String getCategory() {return category;}
}