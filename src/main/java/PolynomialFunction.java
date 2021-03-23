
public class PolynomialFunction implements MathFunction{
    public double calculate(final double x) {
        double [] factors = {1, -2, -1, 1};
        double y = factors[0];

        for(int i = 1; i < factors.length; i++) {
            y = y * x + factors[i];
        }
        return y;
    }
}
