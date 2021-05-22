package MathFunctions;

import MathFunctions.MathFunction;

public class PolynomialFunction implements MathFunction {

    private double[] factors;

    public PolynomialFunction(double[] factors) {
        this.factors = factors;
    }

    @Override
    public double calculate( double x ) {

        double y = factors[0];

        for(int i = 1; i < factors.length; i++) {
            y = y * x + factors[i];
        }

        return y;
    }
}
