package org.example.trigonometric;

import org.example.function.AbstractFunction;

import static java.lang.Double.isNaN;
import static java.lang.Math.PI;

public class Tan extends AbstractFunction {
    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    @Override
    public double calculate(double x, double eps) {
        double resSin = sin.calculate(x, eps);
        double resCos = cos.calculate(x, eps);

        if (Math.abs(resCos) < eps || Double.isInfinite(resSin / resCos)) {
            throw new ArithmeticException("zero division cannot be performed");
        }

        return resSin/resCos;
    }
}
