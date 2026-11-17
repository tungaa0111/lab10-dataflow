package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * bonus()-ийн тестүүд. Мөрийн дугаар нь DataFlowTargets.java файлын мөр.
 * Шат 1: ALL-DEFS (allDefsA, allDefsB). Шат 2: ALL-DU-PAIRS (бусад тестүүд).
 * Хосын ID (D1..D35) нь README-ийн DU хүснэгттэй таарна.
 */
class BonusTest {

    // ---------- Stage 1: ALL-DEFS ----------

    @Test
    @DisplayName("B-T1 allDefsA: present>=thr and best>=5 -> score 51->54->56 (8)")
    void allDefsA() {
        // 5 present in a row, a break, one more present: present=6, best=5, score=5+3
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 3));
    }

    @Test
    @DisplayName("B-T2 allDefsB: empty array, thr=1 -> score 49->56 (0)")
    void allDefsB() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {}, 1));
    }

    // ---------- Stage 2: ALL-DU-PAIRS (added on top of stage 1) ----------

    @Test
    @DisplayName("B-T3 nullAttendance: attendance@31 -> p-use 32 TRUE (exception)")
    void nullAttendanceThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("B-T4 negativeThreshold: threshold@31 -> p-use 32 TRUE (exception)")
    void negativeThresholdThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("B-T5 emptyThresholdZero: present@35 -> p-use 50 TRUE, score 49->51->56 (5)")
    void emptyArrayThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("B-T6 singleBelowThreshold: present@40 -> p50 FALSE, best@43 -> p53 FALSE, score 49->56 (0)")
    void singlePresentBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {1}, 2));
    }

    @Test
    @DisplayName("B-T7 longStreakBelowThreshold: score 49->54 (3): present<thr but best>=5")
    void longStreakBelowThreshold() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("B-T8 shortStreakMeetsThreshold: score 51->56 (5): present>=thr, best<5")
    void shortStreakMeetsThreshold() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1}, 1));
    }
}