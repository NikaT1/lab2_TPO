package tpo.lab.trigonometry;

public class Cos {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        sin = new Sin();
    }

    public double calc(double x, int n) {
        if (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            return Double.NaN;
        }
        double ans = 0;
        x = castVariable(x);
        if (Math.PI / 2 <= x && 3 * Math.PI / 2 >= x) {
            ans = -Math.sqrt(1 - Math.pow(sin.calc(x, n), 2));
        } else {
            ans = Math.sqrt(1 - Math.pow(sin.calc(x, n), 2));
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
}
