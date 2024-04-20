package org.example.logarithmic;
public class Ln extends LogFunction {
    @Override
    public double calculate(double x, double eps) {
        if (x <= 0 || Double.isNaN(x) || Double.isInfinite(x)){
            throw new IllegalArgumentException("X must be positive number");
        }

        double res = 0;
        double currentValue = 0;
        int i = 1;
        double base = ((x-1)/(x+1));
        do {
            currentValue = Math.pow(base, 2 * i - 1) / (2 * i - 1);
            res += currentValue;
            i++;
        } while(Math.abs(currentValue) > eps && i < maxIteration);

        return 2 * res;
    }
}
