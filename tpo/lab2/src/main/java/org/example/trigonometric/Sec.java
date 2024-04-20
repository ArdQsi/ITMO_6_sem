package org.example.trigonometric;

import org.example.function.AbstractFunction;

import static java.lang.Double.isNaN;
import static java.lang.Math.PI;

public class Sec extends AbstractFunction {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public Sec() {
        this.cos = new Cos();
    }

    @Override
    public double calculate(double x, double eps) {
        double resCos = cos.calculate(x, eps);

        if (Math.abs(resCos) < eps || Double.isInfinite(1.0 / resCos)) {
            throw new ArithmeticException("zero division cannot be performed");
        }
        double res = 1.0 / resCos;

        return res;
    }
}
