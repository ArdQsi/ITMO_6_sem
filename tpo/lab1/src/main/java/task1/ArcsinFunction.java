package task1;


public class ArcsinFunction {
    public static double arcsinCalculate(double x) {
        if (Math.abs(x) <= 1){
            double res = 0;
            double tmp = 1;
            int n = 0;
            while(tmp > 0.001) {
                tmp = Math.pow(x, 2*n+1) * factorial(2*n) / (Math.pow(2, 2*n) * Math.pow(factorial(n), 2) * (2*n+1));
                res += tmp;
                n++;
            }
            return res;
        } else {
            return Double.NaN;
        }
    }

    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
