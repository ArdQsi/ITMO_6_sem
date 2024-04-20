package org.example;

import org.example.function.CalculatorFunction;
import org.example.logarithmic.*;
import org.example.trigonometric.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class CalcTest {
    private static final double accuracy = 0.0001;
    private static final Ln mockLn = mock(Ln.class);
    private static final Log2 mockLog2 = mock(Log2.class);
    private static final Log5 mockLog5 = mock(Log5.class);
    private static final Log10 mockLog10 = mock(Log10.class);
    public static final Cos mockCos = mock(Cos.class);
    public static final Sec mockSec = mock(Sec.class);
    public static final Sin mockSin = mock(Sin.class);
    public static final Tan mockTan = mock(Tan.class);
    private static Sin sin;
    private static Cos cos;
    private static Sec sec;
    private static Tan tan;
    private static Ln ln;
    private static Log2 log2;
    private static Log5 log5;
    private static Log10 log10;
    private static CalculatorFunction calculatorFunction;
    private static CalculatorFunction mockCalculatorFunction;


    @BeforeAll
    private static void init() {
        mockCalculatorFunction = new CalculatorFunction(mockLn, mockLog2, mockLog5, mockLog10, mockCos, mockSec, mockSin, mockTan);

        sin = new Sin();
        cos = new Cos(sin);
        sec = new Sec(cos);
        tan = new Tan(sin, cos);

        ln = new Ln();
        log2 = new Log2(ln);
        log5 = new Log5(ln);
        log10 = new Log10(ln);

        calculatorFunction = new CalculatorFunction(ln, log2, log5, log10, cos, sec, sin, tan);
    }

    @Test
    public void calculateTrigonometryExecutionFlow() {
        double x = -1;
        Mockito.when(mockSin.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(-0.841471);
        Mockito.when(mockCos.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(0.54030);
        Mockito.when(mockTan.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(-1.55740);
        Mockito.when(mockSec.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(1.8508157);
        Assertions.assertEquals(0.0031354, calculatorFunction.calculate(x, accuracy), accuracy);
    }

    @Test
    public void calculateLogarithmExecutionFlow() {
        double x = 2;
        Mockito.when(mockLn.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(0.69314);
        Mockito.when(mockLog2.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(1.0);
        Mockito.when(mockLog5.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(0.43067);
        Mockito.when(mockLog10.calculate(Mockito.eq(x), Mockito.anyDouble())).thenReturn(0.30102);
        Assertions.assertEquals(0.6194275, calculatorFunction.calculate(x, accuracy), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/TrigData.csv")
    public void calculateTrigonometryExecutionFlow(double x, double result) {
        String path = "src/test/resources/csvFiles/outputs/TrigData.csv";
        Assertions.assertEquals(result, calculatorFunction.writeResToCsv(x, accuracy, path), result*0.1);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/LogData.csv")
    public void calculateLogarithmExecutionFlow(double x, double result) {
        String path = "src/test/resources/csvFiles/outputs/LogData.csv";
        Assertions.assertEquals(result, calculatorFunction.writeResToCsv(x, accuracy, path),result*0.1);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/csvFiles/inputs/CalcData.csv")
    public void calculateExecutionFlow(double x, double result) {
        String path = "src/test/resources/csvFiles/outputs/CalcData.csv";
        Assertions.assertEquals(result, calculatorFunction.writeResToCsv(x, accuracy, path), result*0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculatorFunction.calculate(x, accuracy));
    }
}