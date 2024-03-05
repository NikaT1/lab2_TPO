package tpo.lab;

import tpo.lab.logarithms.Ln;
import tpo.lab.logarithms.Log;
import tpo.lab.trigonometry.Tg;

public class Func {
    private final Log log;
    private final Tg tg;
    private final Ln ln;

    public Func() {
        this.tg = new Tg();
        this.ln = new Ln();
        this.log = new Log(ln);
    }

    public Func(Tg tg, Ln ln, Log log) {
        this.tg = tg;
        this.log = log;
        this.ln = ln;
    }

    public double calc(double x, int n) {
        if (x <= 0) return tg.calc(x, n);
        else
            return (((Math.pow((log.calc(x, 10, n)), 3) / ln.calc(x, n)) * log.calc(x, 5, n)) + (log.calc(x, 10, n) / Math.pow(log.calc(x, 5, n), 3))) + (log.calc(x, 5, n) + log.calc(x, 5, n));
    }

}
