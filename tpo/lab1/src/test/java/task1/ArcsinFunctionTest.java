package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



public class ArcsinFunctionTest {
    double eps = 0.1;

    @ParameterizedTest
    @ValueSource(doubles = {-1.001, -1, -0.999, -0.5, -0.001, 0, 0.001, 0.5, 0.999,  1, 1.001})
    public void checkDots(double value) {
        Assertions.assertEquals(Math.asin(value), ArcsinFunction.arcsinCalculate(value), eps);
    }
}
