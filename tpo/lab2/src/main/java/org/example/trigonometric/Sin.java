package org.example.trigonometric;

import org.example.function.AbstractFunction;
import static java.lang.Math.PI;

public class Sin extends AbstractFunction {

    @Override
    public double calculate(double x, double eps) {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new IllegalArgumentException("Argument must be a number");
        }
        x = getPeriod(x);
        if (x == 0 || x == -PI) {
            return (double) 0;
        }
        int i = 1;
        double res = 0;
        while (Math.abs(Math.pow(x, 2 * i - 1) / getFactorial(i)) > eps) {
            res += Math.pow(-1, i - 1) * Math.pow(x, 2 * i - 1) / getFactorial(2 * i - 1);
            i++;
        }
        return res;
    }
}
