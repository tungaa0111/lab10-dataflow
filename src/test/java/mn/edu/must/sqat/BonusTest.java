package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * bonus()-ийн тестүүд. Мөрийн дугаар нь DataFlowTargets.java файлын мөр.
 * Шат 1: ALL-DEFS (allDefsA, allDefsB). (Шат 2 дараагийн commit-д нэмэгдэнэ.)
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
}