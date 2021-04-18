public class PolynomialFunction implements MathFunction {

    @Override
    public double calculate( double x ) {

        double [] factors = {3, -2, 0, 1};
        double y = factors[0];

        for(int i = 1; i < factors.length; i++) {
            y = y * x + factors[i];
        }

        return y;
    }
}
