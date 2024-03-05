package tpo.lab.logarithms;

public class Ln {
    public double calc(double x, int n) {
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
        for (int i = 1; i <= n; i+=2) {
            ans += numerator / i;
            numerator *= y * y;
        }
        return 2*ans;
    }
}
