package tpo.lab.trigonometry;

public class Tg implements TrigonometryFunctions {
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

    public double calc(double x, double accuracy) {
        return sin.calc(x, accuracy) / cos.calc(x, accuracy);
    }

}
