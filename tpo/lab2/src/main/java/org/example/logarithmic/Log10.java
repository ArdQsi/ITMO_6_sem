package org.example.logarithmic;

public class Log10 extends LogFunction{
    private final Ln ln;

    public Log10(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x, double eps) {
        return ln.calculate(x, eps) / ln.calculate(10, eps);
    }
}
