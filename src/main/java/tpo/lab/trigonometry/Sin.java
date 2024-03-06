package tpo.lab.trigonometry;

public class Sin implements TrigonometryFunctions {
    public double calc(double x, double accuracy) {
        double ans = 0;
        double prev = 1;
        int i = 0;
        while (Math.abs(ans - prev) > accuracy) {
            prev = ans;
            ans += Math.pow(-1, i) / fact(2 * i + 1) * Math.pow(x, 2 * i + 1);
            i++;
        }
        return ans;
    }


    private double fact(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1 || n == 0) return 1;
        return n * fact(n - 1);
    }
}
