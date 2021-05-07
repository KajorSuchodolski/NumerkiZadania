public class TrygonometricFunction implements MathFunction {

    private double a, b, c;

    public TrygonometricFunction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculate( double x ) {
        return a * Math.sin( b * x + c);
    }
}