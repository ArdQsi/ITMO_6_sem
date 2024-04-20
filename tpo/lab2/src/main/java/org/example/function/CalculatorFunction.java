package org.example.function;

import org.example.logarithmic.*;
import org.example.trigonometric.*;

public class CalculatorFunction extends AbstractFunction {
    Ln ln;
    Log2 log2;
    Log5 log5;
    Log10 log10;
    Cos cos;
    Sec sec;
    Sin sin;
    Tan tan;

    public CalculatorFunction(Ln ln, Log2 log2, Log5 log5, Log10 log10, Cos cos, Sec sec, Sin sin, Tan tan){
        this.ln = ln;
        this.log2 = log2;
        this.log5 = log5;
        this.log10 = log10;
        this.cos = cos;
        this.sec = sec;
        this.sin = sin;
        this.tan = tan;
    }

    @Override
    public double calculate(double x, double eps) {
        if (x > 0) {
            return Math.pow(Math.pow(Math.pow(log5.calculate(x, eps) + log10.calculate(x, eps), 3), 2) + (log2.calculate(x, eps) - log10.calculate(x, eps)), 3);
        } else {
            double resSin = sin.calculate(x, eps);
            double resTan = tan.calculate(x, eps);
            double resSec = sec.calculate(x, eps);
            return Math.pow(Math.pow((((resSin * resTan) / resSec) / resSec), 2), 3);
        }
    }
}
