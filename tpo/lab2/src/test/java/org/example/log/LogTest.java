package org.example.log;

import org.example.logarithmic.*;
import org.example.trigonometric.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {
    private final Ln mockLn = Mockito.mock(Ln.class);
    private static final double accuracy = 0.001;
    private final Ln ln;
    private final Log2 log2;
    private final Log5 log5;
    private final Log10 log10;

    public LogTest() {
        this.ln = new Ln();
        this.log2 = new Log2(ln);
        this.log5 = new Log5(ln);
        this.log10 = new Log10(ln);
    }

    @Test
    public void calculateLog2WithMocks() {
        Mockito.when(mockLn.calculate(Mockito.eq((double) 3), Mockito.eq(accuracy))).thenReturn(1.09861);
        Mockito.when(mockLn.calculate(Mockito.eq((double) 2), Mockito.eq(accuracy))).thenReturn(0.69314);

        Log2 log2 = new Log2(mockLn);

        double result = log2.calculate(3, accuracy);

        Mockito.verify(mockLn, Mockito.times(2)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertEquals(1.58496, result, accuracy);
    }

    @Test
    public void calculateLog5WithMocks() {
        Mockito.when(mockLn.calculate(Mockito.eq((double) 3), Mockito.eq(accuracy))).thenReturn(1.09861);
        Mockito.when(mockLn.calculate(Mockito.eq((double) 5), Mockito.eq(accuracy))).thenReturn(1.60943);

        Log5 log5 = new Log5(mockLn);

        double result = log5.calculate(3, accuracy);

        Mockito.verify(mockLn, Mockito.times(2)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertEquals(0.682606, result, accuracy);
    }

    @Test
    public void calculateLog10WithMocks() {
        Mockito.when(mockLn.calculate(Mockito.eq((double) 3), Mockito.eq(accuracy))).thenReturn(1.09861);
        Mockito.when(mockLn.calculate(Mockito.eq((double) 10), Mockito.eq(accuracy))).thenReturn(2.30258);

        Log10 log10 = new Log10(mockLn);

        double result = log10.calculate(3, accuracy);

        Mockito.verify(mockLn, Mockito.times(2)).calculate(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertEquals(0.477121, result, accuracy);
    }


    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            -1, 0
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> log2.calculate(x, accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Log2.csv")
    void log2Test(Double x, Double trueResult) {
        try {
            String path = "src/test/resources/csvFiles/outputs/Log2.csv";
            double result = log2.writeResToCsv(x, accuracy, path);
            assertEquals(trueResult, result, 0.01);
        } catch (IllegalArgumentException e) {
            assertEquals("X must be positive number", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Log5.csv")
    void log5Test(Double x, Double trueResult) {
        try {
            String path = "src/test/resources/csvFiles/outputs/Log5.csv";
            double result = log5.writeResToCsv(x, accuracy, path);
            assertEquals(trueResult, result, 0.01);
        } catch (IllegalArgumentException e) {
            assertEquals("X must be positive number", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/Log10.csv")
    void log10Test(Double x, Double trueResult) {
        try {
            String path = "src/test/resources/csvFiles/outputs/Log10.csv";
            double result = log10.writeResToCsv(x, accuracy, path);
            assertEquals(trueResult, result, accuracy);
        } catch (IllegalArgumentException e) {
            assertEquals("X must be positive number", e.getMessage());
        }
    }
}