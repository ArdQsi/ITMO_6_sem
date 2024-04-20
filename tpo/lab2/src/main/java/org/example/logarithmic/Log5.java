package org.example.logarithmic;

public class Log5 extends LogFunction {
    private final Ln ln;
    public Log5(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x, double eps) {
        return ln.calculate(x, eps) / ln.calculate(5, eps);
    }
}
