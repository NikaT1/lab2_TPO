package tpo.lab.trigonometry;

public class Tg {
    private final Sin sin;
    private final Cos cos;

    public Tg(){
        sin = new Sin();
        cos = new Cos(sin);
    }

    public Tg(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    public double calc(double x, int n) {
        if (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            return Double.NaN;
        }
        x = castVariable(x);
        if (x == Math.PI/2 || x == 3 * Math.PI/2) {
            return Double.NaN;
        }
        return sin.calc(x, n)/cos.calc(x, n);
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
