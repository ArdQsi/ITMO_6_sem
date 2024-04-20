package org.example.trig;

import org.example.trigonometric.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private static final double accuracy = 0.001;
    private static Sin sin;

    @BeforeAll
    private static void init() {
        sin = new Sin();
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sin.calculate(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Sin.csv")
    void sinTest(double numerator, double denominator, double trueResult) {
        Sin sin = new Sin();
        String path = "src/test/resources/csvFiles/outputs/Sin.csv";
        double x = numerator * PI / denominator;
        double res = sin.writeResToCsv(x, accuracy, path);
        assertEquals(trueResult, res, accuracy);
    }
}