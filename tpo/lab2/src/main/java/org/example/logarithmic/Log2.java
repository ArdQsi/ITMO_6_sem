package org.example.logarithmic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Log2 extends LogFunction {
    private final Ln ln;
    public Log2(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x, double eps) {
        return ln.calculate(x, eps) / ln.calculate(2, eps);
    }

}
