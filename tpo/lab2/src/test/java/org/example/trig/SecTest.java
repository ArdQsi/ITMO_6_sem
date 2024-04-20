package org.example.trig;

import org.example.trigonometric.Cos;
import org.example.trigonometric.Sec;
import org.example.trigonometric.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecTest {
    private static final double accuracy = 0.001;
    private final Cos mockCos = Mockito.mock(Cos.class);
    private static Sin sin;
    private static Cos cos;
    private static Sec sec;

    @BeforeAll
    private static void init() {
        sin = new Sin();
        cos = new Cos(sin);
        sec = new Sec(cos);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockCos.calculate(Mockito.eq((double) 0), Mockito.eq(accuracy))).thenReturn((double) 1);
        Sec sec = new Sec(mockCos);
        double result = sec.calculate(0, accuracy);

        Mockito.verify(mockCos, Mockito.times(1)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertEquals(1, result);
    }

    @Test
    public void calculateWithMocksZeroDivision() {
        Mockito.when(mockCos.calculate(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn((double) 0);
        Sec sec = new Sec(mockCos);

        Assertions.assertThrows(ArithmeticException.class, () -> sec.calculate(0, accuracy));
    }


    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI / 2, Math.PI / 2,
            3 * Math.PI / 2, 3 * -Math.PI / 2
    })
    public void calculateZeroDivision(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> sec.calculate(x, accuracy));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sec.calculate(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Sec.csv")
    void secTest(double numerator, double denominator, double trueResult) {
        try {
            String path = "src/test/resources/csvFiles/outputs/Sec.csv";
            double x = numerator * PI / denominator;
            double res = sec.writeResToCsv(x, accuracy, path);
            assertEquals(trueResult, res, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("zero division cannot be performed", e.getMessage());
        }
    }
}
