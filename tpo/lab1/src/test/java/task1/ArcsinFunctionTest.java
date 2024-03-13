package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.PI;


public class ArcsinFunctionTest {
    double eps = 0.58;

    @ParameterizedTest
    @ValueSource(doubles = {-1.001, -1, -0.999, -0.5, -0.001, 0, 0.001, 0.5, 0.999,  1, 1.001})
    void checkDots(double value) {
        Assertions.assertEquals(Math.asin(value), ArcsinFunction.arcsinCalculate(value), eps);
    }
//    @Test
//    void checkBetweenDotsMinusOneAndOne() {
//        Assertions.assertEquals(Double.NaN, ArcsinFunction.arcsinCalculate(-5), eps);
//        Assertions.assertEquals(Double.NaN, ArcsinFunction.arcsinCalculate(-1.001), eps);
//        Assertions.assertEquals(-PI/2, ArcsinFunction.arcsinCalculate(-1), eps);
//        Assertions.assertEquals(-PI/6, ArcsinFunction.arcsinCalculate(-0.5), eps);
//        Assertions.assertEquals(0.0, ArcsinFunction.arcsinCalculate(0), eps);
//        Assertions.assertEquals(PI/6, ArcsinFunction.arcsinCalculate(0.5), eps);
//        Assertions.assertEquals(PI/2, ArcsinFunction.arcsinCalculate(1), eps);
//        Assertions.assertEquals(Double.NaN, ArcsinFunction.arcsinCalculate(1.001), eps);
//        Assertions.assertEquals(Double.NaN, ArcsinFunction.arcsinCalculate(5), eps);
//    }
}
