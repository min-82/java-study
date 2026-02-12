package TextPokemon;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Skill {

    // --- 1. 기본 정보 ---
    private final String name;
    private final PokeType type;
    private final int power;
    private final int accuracy;
    private final String category; // "PHYSICAL", "SPECIAL", "STATUS"

    @Builder.Default
    private int pp = 15;

    @Builder.Default
    private int priority = 0;

    // --- 2. 랭크 변화 (버프/디버프) ---
    // [0:공, 1:방, 2:특공, 3:특방, 4:스핏, 5:명중, 6:회피]
    @Builder.Default
    private int[] myStatChanges = new int[7];    // 나에게 적용 (예: 칼춤, 오버히트 반동)

    @Builder.Default
    private int[] enemyStatChanges = new int[7]; // 적에게 적용 (예: 싫은소리, 원시의힘)

    // --- 3. 상태 이상 ---
    @Builder.Default
    private String statusEffect = "NONE";

    @Builder.Default
    private int statusChance = 0;

    // --- 4. [추가됨] 연타 및 급소 ---
    @Builder.Default
    private int minHits = 1;  // 최소 공격 횟수 (기본 1)

    @Builder.Default
    private int maxHits = 1;  // 최대 공격 횟수 (기본 1)
    // 예: 마구할퀴기는 min=2, max=5 설정

    @Builder.Default
    private int critStage = 0; // 급소 보정 (0: 기본, 1: 높음 등)

    // --- 5. 특수 기믹 (만능 주머니) ---
    // "RECOIL_25" (반동), "DRAIN_50" (흡수), "FLINCH" (풀죽음), "ALWAYS_HIT" (필중)
    @Builder.Default
    private List<String> specialFlags = new ArrayList<>();

    @Builder.Default
    private String description = "";

    // --- 편의 메소드 ---
    public boolean hasFlag(String flag) {
        return specialFlags.contains(flag);
    }
}