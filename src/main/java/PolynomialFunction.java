public class PolynomialFunction implements MathFunction {

    private double[] factors;

    public PolynomialFunction(double[] factors) {
        this.factors = factors;
    }

    @Override
    public double calculate( double x ) {
        double y = factors[factors.length - 1];

        for(int i = factors.length - 2; i >= 0; i--) {
            y += factors[i] * x;
        }

        return y;
    }
}
