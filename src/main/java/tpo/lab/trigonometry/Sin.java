package tpo.lab.trigonometry;

public class Sin {
    public double calc(double x, int n) {
        if (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            return Double.NaN;
        }
        x = castVariable(x);
        double ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.pow(-1, i) / fact(2 * i + 1) * Math.pow(x, 2 * i + 1);
        }
        return ans;
    }

    private double castVariable(double x) {
        while (x > 0) {
            x -= 2 * Math.PI;
        }
        while (x < 0) {
            x += 2 * Math.PI;
        }
        return x;
    }

    private double fact(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1 || n == 0) return 1;
        return n * fact(n - 1);
    }
}
