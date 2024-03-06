package tpo.lab.trigonometry;

public class Cos implements TrigonometryFunctions {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        sin = new Sin();
    }

    public double calc(double x, double accuracy) {
        double ans;
        double cast = castVariable(x);
        if (Math.PI / 2 <= cast && 3 * Math.PI / 2 >= cast) {
            ans = -Math.sqrt(1 - Math.pow(sin.calc(x, accuracy), 2));
        } else {
            ans = Math.sqrt(1 - Math.pow(sin.calc(x, accuracy), 2));
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
