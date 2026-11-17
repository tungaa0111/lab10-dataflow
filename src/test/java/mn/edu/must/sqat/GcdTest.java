package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * gcd()-ийн all-DU-pairs тестүүд. Мөрийн дугаар нь DataFlowTargets.java файлын мөр:
 * 13 def(x,y) | 15 while(y != 0) | 16 tmp = x % y | 17 x = y | 18 y = tmp | 20 return x.
 * Хосын ID (G1..G13) нь README-ийн хүснэгттэй таарна.
 */
class GcdTest {

    @Test
    @DisplayName("G-T1 gcd(12,8)=4, 2 iterations: G1 G3 G4 G5 G6 G7 G8 G9 G10 G11 G12 G13")
    void gcd12and8TwoIterations() {
        // iter 1: x=12,y=8 -> tmp=4, x=8, y=4 ; iter 2: tmp=0, x=4, y=0 ; exit
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("G-T2 gcd(7,0)=7, loop skipped: x13->20 (G2) and y13->p15 false (G5b)")
    void gcd7and0LoopSkipped() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G-T3 gcd(0,5)=5, one iteration with tmp=0 (extra, same pairs as first iteration)")
    void gcd0and5OneIteration() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
