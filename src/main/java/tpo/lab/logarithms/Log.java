package tpo.lab.logarithms;

public class Log implements LogarithmFunctions {

    private final Ln ln;
    private final Integer base;

    public Log(Integer base) {
        this.base = base;
        ln = new Ln();
    }

    public Log(Ln ln, Integer base) {
        this.ln = ln;
        this.base = base;
    }

    public double calc(double x, double accuracy) {
        return ln.calc(x, accuracy) / ln.calc(base, accuracy);
    }
}
