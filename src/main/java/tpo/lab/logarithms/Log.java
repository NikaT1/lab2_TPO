package tpo.lab.logarithms;

public class Log {

    private final Ln ln;

    public Log() {
        ln = new Ln();
    }

    public Log(Ln ln) {
        this.ln = ln;
    }

    public double calc(double x, int base, int n) {
        return ln.calc(x, n) / ln.calc(base, n);
    }
}
