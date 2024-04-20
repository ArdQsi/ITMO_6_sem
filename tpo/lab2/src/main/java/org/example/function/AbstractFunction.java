package org.example.function;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static java.lang.Double.*;

public abstract class AbstractFunction {
    public abstract double calculate(double x, double eps);

    public Double getFactorial(int n) {
        double ret;
        if (n == 0) {
            return 1.0;
        } else {
            ret = n * getFactorial(n - 1);
        }
        return ret;
    }

    public double getPeriod(double x) {
        if (x >= 0) {
            while (x > Math.PI) {
                x -= Math.PI * 2;
            }
        } else {
            while (x < -Math.PI) {
                x += Math.PI * 2;
            }
        }
        return x;
    }

    public double writeResToCsv(double x, double eps, String path) {
        double res = calculate(x, eps);
        try (PrintStream pr = new PrintStream(new FileOutputStream(path, true))) {
            pr.print(String.format("%s, %s \n", x, res));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
