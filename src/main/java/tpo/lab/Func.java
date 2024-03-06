package tpo.lab;

import tpo.lab.logarithms.Ln;
import tpo.lab.logarithms.Log;
import tpo.lab.trigonometry.Tg;

public class Func implements Functions {
    private final Log log5;
    private final Log log10;
    private final Tg tg;
    private final Ln ln;

    public Func() {
        this.tg = new Tg();
        this.ln = new Ln();
        this.log5 = new Log(5);
        this.log10 = new Log(10);
    }

    public Func(Log log5, Log log10, Tg tg) {
        this.log5 = log5;
        this.log10 = log10;
        this.tg = tg;
        this.ln = new Ln();
    }

    public double calc(double x, double accuracy) {
        if (x <= 0) return tg.calc(x, accuracy);
        else
            return (((Math.pow(log10.calc(x, accuracy), 3) / ln.calc(x, accuracy)) * log5.calc(x, accuracy)) + (log10.calc(x, accuracy) / Math.pow(log5.calc(x, accuracy), 3))) + (log5.calc(x, accuracy) + log5.calc(x, accuracy));
    }

}
