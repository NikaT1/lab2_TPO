package tpo.lab.logarithms;

public class Ln implements LogarithmFunctions {
    public double calc(double x, double accuarcy) {
        if (Double.isNaN(x) || x < 0) {
            return Double.NaN;
        } else if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if (x == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        double y = (x - 1)/(x + 1);
        double numerator = y;
        double ans = 0;
        double prevAns = -1;
        int i = 1;
        while (Math.abs(ans - prevAns) > accuarcy) {
            prevAns = ans;
            ans += numerator / i;
            numerator *= y * y;
            i += 2;

        }
        return 2*ans;
    }
}
