package org.example.log;

import org.example.logarithmic.Ln;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {
    private final Ln ln = new Ln();
    private final double accuracy = 0.001;

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            -1, 0
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ln.calculate(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Ln.csv")
    void lnTest(Double x, Double trueResult) {
        try {
            String path = "src/test/resources/csvFiles/outputs/Ln.csv";
            double result = ln.writeResToCsv(x, accuracy, path);
            assertEquals(trueResult, result, 0.01);
        } catch (IllegalArgumentException e) {
            assertEquals("X must be positive number", e.getMessage());
        }
    }
}
