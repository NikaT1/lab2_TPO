package tpo.lab;

import tpo.lab.logarithms.Ln;
import tpo.lab.logarithms.Log;
import tpo.lab.trigonometry.Cos;
import tpo.lab.trigonometry.Sin;
import tpo.lab.trigonometry.Tg;

public class Main {
    public static void main(String[] args) {
        Ln ln = new Ln();
        Log log = new Log(ln);
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tg tg = new Tg(sin, cos);

        System.out.println(ln.calc(23, 80));
        System.out.println(log.calc(23, 10, 50));
        System.out.println(sin.calc(23, 20));
        System.out.println(cos.calc(23, 20));
        System.out.println(tg.calc(23, 20));
    }
}