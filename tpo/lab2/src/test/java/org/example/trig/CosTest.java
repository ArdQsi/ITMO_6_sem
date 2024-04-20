package org.example.trig;

import org.example.trigonometric.Cos;
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

public class CosTest {
    private static final double accuracy = 0.01;
    private final Sin mockSin = Mockito.mock(Sin.class);
    private static Sin sin;
    private static Cos cos;

    @BeforeAll
    private static void init() {
        sin = new Sin();
        cos = new Cos();
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockSin.calculate(Mockito.eq(Math.PI), Mockito.eq(accuracy))).thenReturn((double) 0);
        Cos cos = new Cos(mockSin);
        double result = cos.calculate(Math.PI, accuracy);

        Mockito.verify(mockSin, Mockito.times(1)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertEquals(-1, result, accuracy);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cos.calculate(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Cos.csv")
    void cosTest(double numerator, double denominator, double trueResult) {
        String path = "src/test/resources/csvFiles/outputs/Cos.csv";
        double x = numerator * PI / denominator;
        double res = cos.writeResToCsv(x, accuracy, path);
        assertEquals(trueResult, res, accuracy);
    }
}