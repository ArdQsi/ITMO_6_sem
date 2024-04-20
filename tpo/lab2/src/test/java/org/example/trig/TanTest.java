package org.example.trig;

import org.example.trigonometric.Cos;
import org.example.trigonometric.Sin;
import org.example.trigonometric.Tan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TanTest {
    private static final double accuracy = 0.01;
    private final Sin mockSin = Mockito.mock(Sin.class);
    private final Cos mockCos = Mockito.mock(Cos.class);
    private static Sin sin;
    private static Cos cos;
    private static Tan tan;

    @BeforeAll
    private static void init() {
        sin = new Sin();
        cos = new Cos(sin);
        tan = new Tan(sin, cos);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockSin.calculate(Mockito.eq((double) 1), Mockito.eq(accuracy))).thenReturn(0.84147);
        Mockito.when(mockCos.calculate(Mockito.eq((double) 1), Mockito.eq(accuracy))).thenReturn(0.54030);

        Tan tan = new Tan(mockSin, mockCos);
        double result = tan.calculate(1, accuracy);

        Mockito.verify(mockSin, Mockito.times(1)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.verify(mockCos, Mockito.times(1)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertEquals(1.55740, result, 0.001);
    }

    @Test
    public void calculateWithMocksZeroDivision() {
        Mockito.when(mockCos.calculate(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn((double) 0);
        Tan tan = new Tan(mockSin, mockCos);
        Assertions.assertThrows(ArithmeticException.class, () -> tan.calculate(0, accuracy));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI / 2, Math.PI / 2,
            3 * Math.PI / 2, 3 * -Math.PI / 2
    })
    public void calculateZeroDivision(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> tan.calculate(x, accuracy));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tan.calculate(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Tan.csv")
    void tanTest(double numerator, double denominator, double trueResult) {
        try {
            String path = "src/test/resources/csvFiles/outputs/Tan.csv";
            double x = numerator * PI / denominator;
            double res = tan.writeResToCsv(x, accuracy, path);
            assertEquals(trueResult, res, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("zero division cannot be performed", e.getMessage());
        }
    }
}
