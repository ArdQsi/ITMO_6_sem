package org.example.trigonometric;

import org.example.function.AbstractFunction;

import static java.lang.Double.isNaN;
import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class Cos extends AbstractFunction {

    private final Sin sin;
    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        this.sin = new Sin();
    }

    @Override
    public double calculate(double x, double eps) {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new IllegalArgumentException("Argument must be a number");
        }

        x = getPeriod(x);

        if (x == -PI/2 || x == -3 * PI/2){
            return (double) 0;
        }

        double resSin = sin.calculate(x, eps);
        double resCos = Math.sqrt(1 - Math.pow(resSin, 2));

        if (((x > Math.PI / 2) && (x < 3 * Math.PI / 2)) || ((x < -Math.PI / 2) && (x > -3 * Math.PI / 2))) {
            resCos *= -1;
        }

        return resCos;
    }
}
