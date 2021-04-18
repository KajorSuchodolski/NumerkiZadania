public class TrygonometricFunction implements MathFunction {

    @Override
    public double calculate( double x ) {
        return Math.pow(Math.sin(x), 2) - Math.cos(x);
    }
}
